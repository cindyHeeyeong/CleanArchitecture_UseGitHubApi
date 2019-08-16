package com.example.cleanarchitecture_toyproject.domain.usecase

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.util.Preconditions
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params> internal constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {
    private val disposables: CompositeDisposable

    init {
        this.disposables = CompositeDisposable()
    }

    internal abstract fun buildUseCaseObservable(params: Params?): Observable<T>

    @SuppressLint("RestrictedApi")
    fun execute(observer: DisposableObserver<T>, params:Params?) {
        Log.d("observer", observer.toString())
        Preconditions.checkNotNull(observer)
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    //observable 메모리 해제
    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        Preconditions.checkNotNull(disposables)
        disposables.add(disposable)
    }
}
