package com.example.cleanarchitecture_toyproject.domain.usecase;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.core.util.Preconditions;
import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread;
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, Params> {

    private final ThreadExecutor threadExecutor;
    private final PostExecutionThread postExecutionThread;
    private final CompositeDisposable disposables;

    UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    abstract Observable<T> buildUseCaseObservable(Params params);

    @SuppressLint("RestrictedApi")
    public void execute(DisposableObserver<T> observer, Params params) {
        Log.d("observer", String.valueOf(observer));
        Preconditions.checkNotNull(observer);
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    //observable 메모리 해제
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    @SuppressLint("RestrictedApi")
    private void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}
