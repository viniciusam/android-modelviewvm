package com.viniciusam.modelviewvm.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.viniciusam.modelviewvm.R;
import com.viniciusam.modelviewvm.databinding.ActivityMainBinding;
import com.viniciusam.modelviewvm.executor.Executor;
import com.viniciusam.modelviewvm.executor.ThreadedExecutor;
import com.viniciusam.modelviewvm.viewmodel.TodoListViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private Executor mExecutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mExecutor = new ThreadedExecutor();

        TodoListViewModel viewModel = new TodoListViewModel(this, mExecutor);
        mBinding.setViewModel(viewModel);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setAdapter(viewModel.getAdapter());

        viewModel.loadTodos();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mExecutor.quit();
    }
}
