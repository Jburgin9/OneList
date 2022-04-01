package com.example.checklist.views;

import androidx.viewbinding.ViewBinding;

import com.example.checklist.databinding.TaskWorkingFragLayoutBinding;

public class WorkingTaskListFragment extends BaseTaskFragment{
    @Override
    protected ViewBinding getViewBinding() {
        return TaskWorkingFragLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    void setupViews() {

    }
}
