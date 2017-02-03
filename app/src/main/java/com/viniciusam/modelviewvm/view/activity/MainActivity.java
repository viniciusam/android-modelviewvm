package com.viniciusam.modelviewvm.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.viniciusam.modelviewvm.R;
import com.viniciusam.modelviewvm.databinding.ActivityMainBinding;
import com.viniciusam.modelviewvm.viewmodel.TodoListViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        TodoListViewModel viewModel = new TodoListViewModel(this);
        mBinding.setViewModel(viewModel);

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setHasFixedSize(true);
        mBinding.recyclerView.setAdapter(viewModel.getAdapter());
    }

}
