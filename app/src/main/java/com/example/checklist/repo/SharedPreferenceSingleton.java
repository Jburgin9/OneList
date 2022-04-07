package com.example.checklist.repo;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.checklist.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SharedPreferenceSingleton {
    public static final String PREFERENCES_INSTANCE = "first";
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

    public boolean saveList(List<Task> currList, Set<String> uniqueNames){
        if(currList == null) return false;
        String json = gson.toJson(currList);
        editor.putString("taskList", json);
        editor.putStringSet("uniqueNames", uniqueNames);
        return editor.commit();
    }

    public List<Task> getSavedTaskList(){
        List<Task> currList;
        String json = sharedPreferences.getString("taskList", "");
        if(json.isEmpty()){
            return new ArrayList<>(5);
        } else {
            Type type = new TypeToken<List<Task>>(){}.getType();
            currList = gson.fromJson(json, type);
        }
        return currList;
    }

    public void saveCompletedList(List<Task> completedList){
        if(completedList == null) return;
        String json = gson.toJson(completedList);
        editor.putString("completedList", json);
        editor.commit();
    }

    public List<Task> getSavedCompletedList() {
        List<Task> completedList;
        String json = sharedPreferences.getString("completedList", "");
        if(json.isEmpty()){
            return new ArrayList<>(5);
        } else {
            Type type = new TypeToken<List<Task>>(){}.getType();
            completedList = gson.fromJson(json, type);
        }
        return completedList;
    }

    public Set<String> getSavedTitles(){
        return sharedPreferences.getStringSet("uniqueNames", new HashSet<>(5));
    }

    public void shutdown(){
        editor = null;
        sharedPreferences = null;
        gson = null;
    }

}
