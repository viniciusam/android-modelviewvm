package com.viniciusam.modelviewvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;

import com.viniciusam.modelviewvm.R;
import com.viniciusam.modelviewvm.model.Todo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Vinicius on 01/02/2017.
 */
public class TodoItemViewModel extends BaseObservable {

    private Context mContext;
    private Todo mTodo;

    public TodoItemViewModel(Context context, Todo todo) {
        mContext = context;
        mTodo = todo;
    }

    public String getTitle() {
        return mTodo.getTitle();
    }

    public String getCreatedAt() {
        DateFormat fmt = SimpleDateFormat.getDateTimeInstance();
        return String.format(mContext.getString(R.string.created_at), fmt.format(mTodo.getDate()));
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

}
