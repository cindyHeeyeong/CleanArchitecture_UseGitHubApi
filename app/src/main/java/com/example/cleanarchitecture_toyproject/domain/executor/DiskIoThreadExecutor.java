package com.example.cleanarchitecture_toyproject.domain.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskIoThreadExecutor implements Executor {
    private final Executor DiskIO;

    public DiskIoThreadExecutor() {
        this.DiskIO = Executors.newSingleThreadExecutor();
    }
    @Override
    public void execute(Runnable runnable) {
        this.DiskIO.execute(runnable);
    }
}
