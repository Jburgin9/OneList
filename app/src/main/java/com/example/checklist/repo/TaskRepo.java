package com.example.checklist.repo;

import com.example.checklist.model.Task;

import java.util.List;
import java.util.Set;

public class TaskRepo {
    private Repo repoInterface;
    private List<Task> taskList;
    private List<Task> completedList;
    private SharedPreferenceSingleton preferenceSingleton;
    private Set<String> inUseTitles;

    public TaskRepo(Repo repoInterface,
                    SharedPreferenceSingleton preferenceSingleton){
        this.repoInterface = repoInterface;
        this.preferenceSingleton = preferenceSingleton;
        taskList = preferenceSingleton.getSavedTaskList();
        completedList = preferenceSingleton.getSavedCompletedList();
        inUseTitles = preferenceSingleton.getSavedTitles();
    }

    public synchronized boolean addTask(Task newTask){
        if(newTask != null){
            taskList.add(newTask);
            inUseTitles.add(newTask.getTitle());
            return preferenceSingleton.saveList(taskList, inUseTitles);
        }
        return false;
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
