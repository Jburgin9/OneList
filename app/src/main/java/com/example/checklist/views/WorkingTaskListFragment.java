package com.example.checklist.views;

import androidx.viewbinding.ViewBinding;

import com.example.checklist.databinding.TaskWorkingFragLayoutBinding;
import com.example.checklist.presenter.MainPresenter;

public class WorkingTaskListFragment extends BaseTaskFragment{
    private MainPresenter presenter;
    @Override
    protected ViewBinding getViewBinding() {
        return TaskWorkingFragLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    void setupViews() {

    }
}
