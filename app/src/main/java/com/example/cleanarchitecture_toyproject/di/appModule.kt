package com.example.cleanarchitecture_toyproject.di

import androidx.room.Room
import com.example.cleanarchitecture_toyproject.data.cache.database.AppDatabase
import com.example.cleanarchitecture_toyproject.data.constant.ApiConstants
import com.example.cleanarchitecture_toyproject.data.executor.JobExecutor
import com.example.cleanarchitecture_toyproject.data.mapper.UserEntityMapper
import com.example.cleanarchitecture_toyproject.data.net.RestApiService
import com.example.cleanarchitecture_toyproject.data.repository.UserRemoteRepositoryImpl
import com.example.cleanarchitecture_toyproject.data.repository.UserRepositoryImpl
import com.example.cleanarchitecture_toyproject.data.user.*
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor
import com.example.cleanarchitecture_toyproject.domain.executor.UIThread
import com.example.cleanarchitecture_toyproject.domain.repository.UserRemoteRepository
import com.example.cleanarchitecture_toyproject.domain.repository.UserRepository
import com.example.cleanarchitecture_toyproject.domain.usecase.DeleteUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.GetUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.SelectUserListUseCase
import com.example.cleanarchitecture_toyproject.domain.usecase.SetUserListUseCase
import com.example.cleanarchitecture_toyproject.presentation.mapper.UserModelMapper
import com.example.cleanarchitecture_toyproject.presentation.presenter.FavoriteUserPresenter
import com.example.cleanarchitecture_toyproject.presentation.presenter.UserListPresenter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val roomModule = module {
    single{
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "UserModel_database.db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
}

//파란색 글씨 : 빌더 패턴 (get()할 때 굳이 적어주지 않아도 됨
val remoteApiModule = module {
    single { createOkHttpClient() }
    single { createWebService<RestApiService>(get(), ApiConstants.url) }
}

val remoteSourceModule = module {
    single<UserRemote> { UserRemotImpl(restApi = get()) }
}

val cacheSourceModule = module {
    //TODO appdatabase module 만들어야 함
    single {get<AppDatabase>().userDao()}
    single<UserEntityCache> {UserEntityCacheImpl(database = get())}
}

val dataSourceModule = module {
    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(userRemote = get(), userEntityCache = get()) }
    single<UserCacheDataSource> {UserCacheDataSourceImpl(userEntityCache = get())}
}

val mapperModule = module {
    factory { UserModelMapper() } //두개가 각각 다른 레이어의 mapper이다
    factory { UserEntityMapper() }
}

val presenterModule = module {
    factory { UserListPresenter(getUserListUseCase = get(), setUserListUserCaseUseCase = get(), deleteUserListUseCase = get(),userModelMapper = get()) }
    factory { FavoriteUserPresenter(selectUserListUsecase = get(), deleteUserListUseCase = get(),userModelMapper = get())}
}

val threadModule = module {
    factory<PostExecutionThread> { UIThread() }
    factory<ThreadExecutor> { JobExecutor() }
}

val repositoryModule = module {
    single<UserRemoteRepository> { UserRemoteRepositoryImpl(userRemoteDataSource = get(), userEntityMapper = get()) }
    single<UserRepository> {UserRepositoryImpl(userCacheDataSource = get(), userEntityMapper = get(), userModelMapper = get())}
}

val usecaseModule = module {
    factory { GetUserListUseCase(userRemoteRepository = get(), threadExecutor = get(), postExecutionThread = get()) }
    factory { SetUserListUseCase(userRepository = get(), threadExecutor = get(), postExecutionThread = get())}
    factory { DeleteUserListUseCase(userRepository = get(), threadExecutor = get(), postExecutionThread = get())}
    factory { SelectUserListUseCase(userRepository = get(), threadExecutor = get(), postExecutionThread = get())}
}

val myModule = listOf(
    roomModule,
    remoteApiModule,
    cacheSourceModule,
    remoteSourceModule,
    dataSourceModule,
    mapperModule,
    presenterModule,
    threadModule,
    repositoryModule,
    usecaseModule
)

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}