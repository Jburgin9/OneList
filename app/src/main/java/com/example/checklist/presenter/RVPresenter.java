package com.example.checklist.presenter;

import android.util.Log;

import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;

import java.util.List;

public class RVPresenter {
    private ModelImpl model;

    public RVPresenter(ModelImpl model){
        this.model = model;
    }

    public void deleteFromTaskList(Task task){
        int deleteIdx = model.getTaskList().indexOf(task);
        model.getTaskList().remove(deleteIdx);
        model.updateList(model.getTaskList());
    }

    public void taskComplete(Task task) {
        if(task != null){
            task.setCompleted(true);
            model.getCompletedList().add(task);
            model.saveCompletedList(model.getCompletedList());
            model.deleteTitle(task.getTitle());
            deleteFromTaskList(task);
        }
    }
}
