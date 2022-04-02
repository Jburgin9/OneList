package com.example.checklist.presenter;

import android.util.Log;

import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;

import java.util.List;

public class RVPresenter {
    private static final String TAG = "RV";
    private ModelImpl model;
    private RVPresenterInterface presenter;

    public RVPresenter(ModelImpl model, RVPresenterInterface presenterInterface){
        this.model = model;
        presenter = presenterInterface;
    }

    public void deleteFromTaskList(Task task){
        int deleteIdx = model.getTaskList().indexOf(task);
        model.getTaskList().remove(deleteIdx);
        model.updateList(model.getTaskList());
    }

    public void displayCompletedTasks() {
        presenter.displayCompletedTaskList(model.getTaskList());
    }
}
