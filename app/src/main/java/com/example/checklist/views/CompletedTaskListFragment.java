package com.example.checklist.views;

import androidx.viewbinding.ViewBinding;

import com.example.checklist.databinding.CompletedTasksFragLayoutBinding;

public class CompletedTaskListFragment extends BaseTaskFragment{

    @Override
    protected ViewBinding getViewBinding() {
       return CompletedTasksFragLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    void setupViews() {

    }
}
