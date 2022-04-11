package com.example.checklist.presenter;

import com.example.checklist.repo.TaskRepo;
import com.example.checklist.model.Task;

public class RVPresenter {
    private TaskRepo model;

    public RVPresenter(TaskRepo model){
        this.model = model;
    }

    public void deleteFromTaskList(Task task){
//        int deleteIdx = model.retrieveList();
//        model.getTaskList().remove(deleteIdx);
//        model.updateList(model.getTaskList());
    }

    public void taskComplete(Task task) {
        if(task != null){
//            task.setCompleted(true);
//            model.getCompletedList().add(task);
//            model.saveCompletedList(model.getCompletedList());
//            model.deleteTitle(task.getTitle());
//            deleteFromTaskList(task);
        }
    }
}
