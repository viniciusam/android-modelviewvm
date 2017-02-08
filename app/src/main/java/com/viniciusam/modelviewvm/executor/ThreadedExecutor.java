package com.viniciusam.modelviewvm.executor;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Vinicius on 04/02/2017.
 */
public class ThreadedExecutor implements Executor {

    private volatile Handler mMainHandler;
    private volatile ExecutorService mExecutorService;

    public ThreadedExecutor() {
        mMainHandler = new Handler(Looper.getMainLooper());
        mExecutorService = Executors.newCachedThreadPool();
    }

    @Override
    public <E> void execute(final UseCase<E> useCase) {
        mExecutorService.execute(() -> {
            try {
                final E e = useCase.run();
                mMainHandler.post(() -> useCase.callOnSuccess(e));
            } catch (final Exception e) {
                mMainHandler.post(() -> useCase.callOnError(e));
            }
        });
    }

    @Override
    public void quit() {
        mExecutorService.shutdown();
    }

}
