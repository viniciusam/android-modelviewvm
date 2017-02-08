package com.viniciusam.modelviewvm.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.viniciusam.modelviewvm.R;
import com.viniciusam.modelviewvm.databinding.ItemTodoBinding;
import com.viniciusam.modelviewvm.executor.Executor;
import com.viniciusam.modelviewvm.model.Todo;
import com.viniciusam.modelviewvm.viewmodel.TodoItemViewModel;

import java.util.List;

/**
 * Created by Vinicius on 01/02/2017.
 */
public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.BindingHolder>
        implements TodoItemViewModel.Callbacks {

    private Context mContext;
    private Executor mExecutor;
    private List<Todo> mList;

    public TodoListAdapter(Context context, Executor executor) {
        mContext = context;
        mExecutor = executor;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTodoBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.item_todo, parent, false);
        return new BindingHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        Todo todo = mList.get(position);
        holder.getBinding().setViewModel(new TodoItemViewModel(mContext, mExecutor, this, todo));
    }

    @Override
    public int getItemCount() {
        return (mList != null) ? mList.size() : 0;
    }

    public void setItems(List<Todo> items) {
        mList = items;
        notifyDataSetChanged();
    }

    public void addItem(Todo todo) {
        mList.add(todo);
        notifyItemInserted(getItemCount() + 1);
    }

    public void removeItem(Todo todo) {
        int i = mList.indexOf(todo);
        if (i != -1) {
            mList.remove(i);
            notifyItemRemoved(i);
        }
    }

    @Override
    public void onTodoDeleted(Todo todo) {
        removeItem(todo);
    }

    public static class BindingHolder extends  RecyclerView.ViewHolder {

        private ItemTodoBinding mBinding;

        public BindingHolder(ItemTodoBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public ItemTodoBinding getBinding() {
            return mBinding;
        }

    }

}
