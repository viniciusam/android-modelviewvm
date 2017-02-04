package com.viniciusam.modelviewvm.executor;

/**
 * Created by Vinicius on 04/02/2017.
 */
public abstract class UseCase<E> {

    private OnSuccessCallback mOnSuccess;
    private OnErrorCallback mOnError;

    public abstract E run() throws Exception;

    public UseCase<E> onSuccess(OnSuccessCallback<E> onSuccessCallback) {
        mOnSuccess = onSuccessCallback;
        return this;
    }

    public UseCase<E> onError(OnErrorCallback onErrorCallback) {
        mOnError = onErrorCallback;
        return this;
    }

    public void execute(Executor executor) {
        executor.execute(this);
    }

    public void callOnSuccess(E e) {
        if (mOnSuccess != null)
            mOnSuccess.onSuccess(e);
    }

    public void callOnError(Exception e) {
        if (mOnError != null)
            mOnError.onError(e);
    }

    public interface OnSuccessCallback<E> {
        void onSuccess(E t);
    }

    public interface OnErrorCallback {
        void onError(Exception e);
    }

}
