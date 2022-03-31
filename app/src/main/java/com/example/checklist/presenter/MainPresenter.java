package com.example.checklist.presenter;

import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;

public class MainPresenter {
    private MainPresenterInterface mainPresenterInterfaceInterface;
    private ModelImpl modelImpl;

    public MainPresenter(MainPresenterInterface mainPresenterInterfaceInterface, ModelImpl modelImpl){
        this.mainPresenterInterfaceInterface = mainPresenterInterfaceInterface;
        this.modelImpl = modelImpl;
    }

    public void displayTaskList(){
        mainPresenterInterfaceInterface.displayTaskList(modelImpl.getTaskList());
    }

    public void addTask(String title){
        Task newTask = new Task(title, false);
        modelImpl.addTask(newTask);
    }
}
