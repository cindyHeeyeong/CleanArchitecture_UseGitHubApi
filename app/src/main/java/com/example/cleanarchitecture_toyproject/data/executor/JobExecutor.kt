package com.example.cleanarchitecture_toyproject.data.executor

import com.example.cleanarchitecture_toyproject.domain.executor.ThreadExecutor

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.ThreadPoolExecutor as ThreadPoolExecutor1

class JobExecutor : ThreadExecutor {
    private val threadPoolExecutor: ThreadPoolExecutor1

    init {
        this.threadPoolExecutor = ThreadPoolExecutor1(
            3, 5, 10, TimeUnit.SECONDS,
            LinkedBlockingQueue(), JobThreadFactory()
        )
    }

    override fun execute(runnable: Runnable) {
        this.threadPoolExecutor.execute(runnable)
    }

    private class JobThreadFactory : ThreadFactory {
        private var counter = 0

        override fun newThread(runnable: Runnable): Thread {
            return Thread(runnable, "android_" + counter++)
        }
    }
}