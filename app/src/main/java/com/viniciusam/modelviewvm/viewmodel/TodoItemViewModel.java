package com.viniciusam.modelviewvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;

import com.viniciusam.modelviewvm.R;
import com.viniciusam.modelviewvm.executor.Executor;
import com.viniciusam.modelviewvm.model.Todo;
import com.viniciusam.modelviewvm.usecase.todo.RemoveTodoUC;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Vinicius on 01/02/2017.
 */
public class TodoItemViewModel extends BaseObservable {

    private Context mContext;
    private Executor mExecutor;
    private Callbacks mCallbacks;
    private Todo mTodo;

    public TodoItemViewModel(Context context, Executor executor, Callbacks callbacks, Todo todo) {
        mContext = context;
        mExecutor = executor;
        mCallbacks = callbacks;
        mTodo = todo;
    }

    public String getTitle() {
        return mTodo.getTitle();
    }

    public String getCreatedAt() {
        DateFormat fmt = SimpleDateFormat.getDateTimeInstance();
        return String.format(mContext.getString(R.string.created_at), fmt.format(mTodo.getCreatedAt()));
    }

    public boolean isCompleted() {
        return mTodo.isCompleted();
    }

    public void onCompletedClick(View v) {
        CheckBox cb = (CheckBox) v;
        mTodo.setCompleted(cb.isChecked());
        if (cb.isChecked()) {
            cb.setPaintFlags(cb.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            cb.setPaintFlags(cb.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }

    public void onDeleteClick(View v) {
        new AlertDialog.Builder(mContext)
                .setMessage(R.string.are_you_sure)
                .setCancelable(true)
                .setPositiveButton(R.string.delete, (dialog, which) -> {
                    new RemoveTodoUC(mContext, mTodo.getId())
                            .onSuccess(t -> mCallbacks.onTodoDeleted(mTodo))
                            .execute(mExecutor);
                    dialog.dismiss();
                })
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.dismiss())
                .show();
    }

    public interface Callbacks {
        void onTodoDeleted(Todo todo);
    }

}
