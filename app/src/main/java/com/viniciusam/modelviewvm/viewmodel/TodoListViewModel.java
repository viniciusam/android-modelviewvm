package com.viniciusam.modelviewvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.text.Editable;
import android.widget.EditText;

import com.viniciusam.modelviewvm.executor.Executor;
import com.viniciusam.modelviewvm.usecase.todo.GetAllTodoUC;
import com.viniciusam.modelviewvm.usecase.todo.InsertTodoUC;
import com.viniciusam.modelviewvm.view.adapter.TodoListAdapter;

import java.util.ArrayList;

/**
 * Created by Vinicius on 01/02/2017.
 */
public class TodoListViewModel extends BaseObservable {

    private Context mContext;
    private Executor mExecutor;
    private TodoListAdapter mAdapter;

    public TodoListViewModel(Context context, Executor executor) {
        mContext = context;
        mExecutor = executor;
        mAdapter = new TodoListAdapter(mContext, mExecutor);
        mAdapter.setItems(new ArrayList<>());
    }

    public TodoListAdapter getAdapter() {
        return mAdapter;
    }

    public void loadTodos() {
        new GetAllTodoUC(mContext)
                .onSuccess(t -> mAdapter.setItems(t))
                .execute(mExecutor);
    }

    public void onAddButtonClick(EditText editText) {
        final Editable text = editText.getText();
        if (text.length() == 0)
            return;

        new InsertTodoUC(mContext, text.toString())
                .onSuccess(t -> {
                    mAdapter.addItem(t);
                    text.clear();
                })
                .execute(mExecutor);
    }

}
