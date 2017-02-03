package com.viniciusam.modelviewvm.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.viniciusam.modelviewvm.model.Todo;
import com.viniciusam.modelviewvm.view.adapter.TodoListAdapter;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Vinicius on 01/02/2017.
 */
public class TodoListViewModel extends BaseObservable {

    private Context mContext;
    private TodoListAdapter mAdapter;

    public TodoListViewModel(Context context) {
        mContext = context;
        mAdapter = new TodoListAdapter(mContext);
        mAdapter.setItems(new ArrayList<Todo>());
    }

    public TodoListAdapter getAdapter() {
        return mAdapter;
    }

    public void onAddButtonClick(View v) {
        int lastItem = mAdapter.getItemCount();
        mAdapter.addItem(new Todo(String.format("Title #%s", lastItem + 1), new Date()));
    }

}
