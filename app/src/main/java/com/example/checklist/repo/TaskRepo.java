package com.example.checklist.repo;

import android.util.Log;

import com.example.checklist.model.Task;

import java.util.List;
import java.util.Set;

public class TaskRepo implements Repo{
    private SharedPreferenceSingleton preferenceSingleton;

    public TaskRepo(SharedPreferenceSingleton preferenceSingleton){
        this.preferenceSingleton = preferenceSingleton;
    }

    public int listSize(){
        return preferenceSingleton.getSavedTaskList().size();
    }

    @Override
    public synchronized void retrieveList(final OnSuccessListRetrieved listRetrieved) {
        Log.d("cc", "retrieveList: " + preferenceSingleton.getSavedTaskList().size());
        listRetrieved.onSuccess(preferenceSingleton.getSavedTaskList());
    }

    @Override
    public synchronized void addNewTask(Task newTask, final OnSuccessTaskAddedListener listener) {
        if(newTask != null) {
            List<Task> currList = preferenceSingleton.getSavedTaskList();
            currList.add(newTask);
            preferenceSingleton.getSavedTitles().add(newTask.getTitle());
            preferenceSingleton.saveList(currList,
                    preferenceSingleton.getSavedTitles());
            listener.onSuccess(true);
        }
    }

    public synchronized boolean isTitleUnique(String title){
        return preferenceSingleton.getSavedTitles().contains(title);
    }

    public void shutdown(){
        preferenceSingleton.shutdown();
    }
}
