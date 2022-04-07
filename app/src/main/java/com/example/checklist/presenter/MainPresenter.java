package com.example.checklist.presenter;

import com.example.checklist.ExceededListSizeException;
import com.example.checklist.repo.TaskRepo;
import com.example.checklist.model.Task;
import com.example.checklist.views.contracts.View;

public class MainPresenter {
    private View view;
    private TaskRepo taskRepo;

    public MainPresenter(View view, TaskRepo taskRepo){
        this.view = view;
        this.taskRepo = taskRepo;
    }

    public void displayTaskList(){
        view.displayTaskList(taskRepo.getTaskList());
    }

    public boolean addTask(String title) throws ExceededListSizeException {
        if(taskRepo.getTaskList().size() == 5) throw new ExceededListSizeException("Unable to add more than 5 tasks at current level");
        Task newTask = new Task(title, false);
        boolean test = taskRepo.addTask(newTask);
        return test;
    }

    public boolean isUniqueTitle(String taskTitle){
        return taskRepo.isTitleUnique(taskTitle);
    }
}
