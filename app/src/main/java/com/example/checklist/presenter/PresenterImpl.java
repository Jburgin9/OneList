package com.example.checklist.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.checklist.model.Model;
import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class PresenterImpl {
    private Presenter presenterInterface;
    private ModelImpl modelImpl;

    public PresenterImpl(Presenter presenterInterface, ModelImpl modelImpl){
        this.presenterInterface = presenterInterface;
        this.modelImpl = modelImpl;
    }

    public void getTask(){
        List<Task> testList = modelImpl.getTaskList();
        presenterInterface.displayTaskList(testList);
    }

    public void addTask(String title){
        Task newTask = new Task(title, false);
        modelImpl.addTask(newTask);
    }
}
