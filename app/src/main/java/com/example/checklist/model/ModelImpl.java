package com.example.checklist.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ModelImpl {
    private Model modelInterface;
    public static final String PREFERENCES_INSTANCE = "first";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<Task> taskList;

    public ModelImpl(SharedPreferences sharedPreferences, Model modelInterface){
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
        this.modelInterface = modelInterface;
        taskList = new ArrayList<>();
    }

    public synchronized void addTask(Task newTask){
        boolean isComplete = false;
        if(newTask != null){
            taskList.add(newTask);
            Gson gson = new Gson();
            String json = gson.toJson(taskList);
            editor.putString("taskList", json);
            isComplete = editor.commit();
            modelInterface.onSuccessTaskAdded(isComplete);
        } else {
            modelInterface.onFailureTaskAdded(isComplete);
        }
    }

    public synchronized List<Task> getTaskList(){
        Gson gson = new Gson();
        String json = sharedPreferences.getString("taskList", "");
        if(json.isEmpty()){

        } else {
            Type type = new TypeToken<List<Task>>(){}.getType();
            taskList = gson.fromJson(json, type);
        }
        return taskList;
    }
}
