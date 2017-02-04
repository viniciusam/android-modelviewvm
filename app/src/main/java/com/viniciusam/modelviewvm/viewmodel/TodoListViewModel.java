package com.viniciusam.modelviewvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.viniciusam.modelviewvm.executor.Executor;
import com.viniciusam.modelviewvm.executor.ThreadedExecutor;
import com.viniciusam.modelviewvm.executor.UseCase;
import com.viniciusam.modelviewvm.model.Todo;
import com.viniciusam.modelviewvm.usecase.todo.GetAllTodoUC;
import com.viniciusam.modelviewvm.usecase.todo.InsertTodoUC;
import com.viniciusam.modelviewvm.view.adapter.TodoListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinicius on 01/02/2017.
 */
public class TodoListViewModel extends BaseObservable {

    private Context mContext;
    private Executor mExecutor;
    private TodoListAdapter mAdapter;

    public TodoListViewModel(Context context) {
        mContext = context;
        mExecutor = new ThreadedExecutor();
        mAdapter = new TodoListAdapter(mContext);
        mAdapter.setItems(new ArrayList<Todo>());
    }

    public void quitExecutor() {
        mExecutor.quit();
    }

    public TodoListAdapter getAdapter() {
        return mAdapter;
    }

    public void loadTodos() {
        new GetAllTodoUC(mContext)
                .onSuccess(new UseCase.OnSuccessCallback<List<Todo>>() {
                    @Override
                    public void onSuccess(List<Todo> t) {
                        mAdapter.setItems(t);
                    }
                })
                .execute(mExecutor);
    }

    public void onAddButtonClick(View v) {
        new InsertTodoUC(mContext, "New Todo")
                .onSuccess(new UseCase.OnSuccessCallback<Todo>() {
                    @Override
                    public void onSuccess(Todo t) {
                        mAdapter.addItem(t);
                    }
                })
                .execute(mExecutor);
    }

}
