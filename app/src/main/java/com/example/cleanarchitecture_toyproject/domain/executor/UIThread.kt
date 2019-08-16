package com.example.cleanarchitecture_toyproject.domain.executor

import android.database.sqlite.SQLiteDiskIOException
import androidx.annotation.UiThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

import java.util.concurrent.Executor

class UIThread : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
