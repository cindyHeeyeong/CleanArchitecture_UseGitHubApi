package com.example.cleanarchitecture_toyproject.domain.usecase;

import com.example.cleanarchitecture_toyproject.domain.executor.PostExecutionThread;
import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor;
import io.reactivex.Completable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//completable을 사용하기 위한 usecase
public abstract class CompletableUseCase<Request> {

    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mExecutionThread;
    private final CompositeDisposable mCompositeDisposable;

    public CompletableUseCase(@NonNull ThreadExecutor threadExecutor,
                              @NonNull PostExecutionThread executionThread) {
        mThreadExecutor = threadExecutor;
        mExecutionThread = executionThread;
        mCompositeDisposable = new CompositeDisposable();
    }

    protected abstract Completable buildUseCase(Request request);


    //execute
    public void subscribe(Request request) {
        mCompositeDisposable.clear();
        Disposable disposable = buildUseCase(request)
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mExecutionThread.getScheduler())
                .subscribe();
        mCompositeDisposable.add(disposable);
    }

    public void unsubscribe() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }
}