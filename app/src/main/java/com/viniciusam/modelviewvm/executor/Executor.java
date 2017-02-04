package com.viniciusam.modelviewvm.executor;

/**
 * Created by Vinicius on 04/02/2017.
 */
public interface Executor {

    <E> void execute(UseCase<E> useCase);

    void quit();

}
