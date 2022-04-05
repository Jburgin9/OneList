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
    private List<Task> completedList;
    private SharedPreferenceSingleton preferenceSingleton;
    private Set<String> inUseTitles;

    public ModelImpl(Model modelInterface,
                     SharedPreferenceSingleton preferenceSingleton){
        this.modelInterface = modelInterface;
        this.preferenceSingleton = preferenceSingleton;
        taskList = preferenceSingleton.getSavedTaskList();
        completedList = preferenceSingleton.getSavedCompletedList();
        inUseTitles = preferenceSingleton.getSavedTitles();
    }

    public synchronized void addTask(Task newTask){
        if(newTask != null){
            taskList.add(newTask);
            preferenceSingleton.saveList(taskList, inUseTitles);
            inUseTitles.add(newTask.getTitle());
            modelInterface.onSuccessTaskAdded(true);
        }
    }

    public synchronized void saveCompletedList(List<Task> completedList){
        preferenceSingleton.saveCompletedList(completedList);
    }

    public synchronized void updateList(List<Task> updatedList){
        preferenceSingleton.saveList(updatedList, inUseTitles);
    }

    public synchronized boolean isTitleUnique(String title){ return inUseTitles.contains(title); }
    public synchronized List<Task> getTaskList(){
        return taskList;
    }
    public synchronized List<Task> getCompletedList() { return completedList; }
    public void shutdown(){
        preferenceSingleton.shutdown();
    }

    public void deleteTitle(String title) {
        inUseTitles.remove(title);
    }
}
