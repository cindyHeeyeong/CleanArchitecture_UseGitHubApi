package com.example.cleanarchitecture_toyproject.domain.executor;

import android.database.sqlite.SQLiteDiskIOException;
import androidx.annotation.UiThread;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

import java.util.concurrent.Executor;

public class UIThread implements PostExecutionThread{

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
