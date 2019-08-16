package com.example.cleanarchitecture_toyproject.domain.usecase

import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor
import io.reactivex.Completable
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

//completable을 사용하기 위한 usecase
abstract class CompletableUseCase<Request>(
    @param:NonNull private val mThreadExecutor: ThreadExecutor,
    @param:NonNull private val mExecutionThread: PostExecutionThread
) {
    private val mCompositeDisposable: CompositeDisposable

    init {
        mCompositeDisposable = CompositeDisposable()
    }

    protected abstract fun buildUseCase(request: Request): Completable


    //execute
    fun subscribe(request: Request) {
        mCompositeDisposable.clear()
        val disposable = buildUseCase(request)
            .subscribeOn(Schedulers.from(mThreadExecutor))
            .observeOn(mExecutionThread.scheduler)
            .subscribe()
        mCompositeDisposable.add(disposable)
    }

    fun unsubscribe() {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
    }
}