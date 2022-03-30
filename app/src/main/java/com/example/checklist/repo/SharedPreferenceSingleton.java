package com.example.checklist.repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.checklist.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceSingleton {
    public static final String PREFERENCES_INSTANCE = "first";
    private static final String TAG = "share2";
    private static SharedPreferenceSingleton singleton;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public SharedPreferenceSingleton(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_INSTANCE,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    public static SharedPreferenceSingleton getInstance(Context context) {
        if (singleton != null) {
            return singleton;
        } else {
            singleton = new SharedPreferenceSingleton(context);
        }
        return singleton;
    }


    public boolean saveList(List<Task> currList){
        Log.d(TAG, "addTask: " + editor.toString());
        Log.d(TAG, "addTask: " + sharedPreferences.toString());
        boolean isSuccessful = false;
        if(currList == null) return isSuccessful;
        String json = gson.toJson(currList);
        editor.putString("taskList", json);
        isSuccessful = editor.commit();
        return isSuccessful;
    }

    public List<Task> getSavedTaskList(){
        List<Task> currList;
        String json = sharedPreferences.getString("taskList", "");
        if(json.isEmpty()){
            return new ArrayList<>();
        } else {
            Type type = new TypeToken<List<Task>>(){}.getType();
            currList = gson.fromJson(json, type);
        }
        return currList;
    }

    public void deleteTask(Task taskToRemove){
        int idxToRemove = getSavedTaskList().indexOf(taskToRemove);
        getSavedTaskList().remove(idxToRemove);
        saveList(getSavedTaskList());
    }
    public void shutdown(){
        editor = null;
        sharedPreferences = null;
        gson = null;
    }
}
