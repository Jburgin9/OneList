package com.example.checklist.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.checklist.repo.SharedPreferenceSingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelImpl {
    private Model modelInterface;
    private List<Task> taskList;
    private SharedPreferenceSingleton preferenceSingleton;

    public ModelImpl(Model modelInterface,
                     SharedPreferenceSingleton preferenceSingleton){
        this.modelInterface = modelInterface;
        this.preferenceSingleton = preferenceSingleton;
        taskList = preferenceSingleton.getSavedTaskList();
    }

    public synchronized void addTask(Task newTask){
        boolean isComplete = false;
        if(newTask != null){
            taskList.add(newTask);
            preferenceSingleton.saveList(taskList);
            isComplete = true;
            modelInterface.onSuccessTaskAdded(isComplete);
        } else {
            modelInterface.onFailureAddError(isComplete);
        }
    }

    public synchronized List<Task> getTaskList(){
        return taskList;
    }

    public synchronized void updateList(List<Task> updatedList){
        preferenceSingleton.saveList(updatedList);
    }
    public void shutdown(){
        preferenceSingleton.shutdown();
    }
}
