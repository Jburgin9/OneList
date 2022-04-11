package com.example.checklist.repo;

import com.example.checklist.model.Task;

import java.util.List;

public interface Repo {
    interface OnSuccessTaskAddedListener {
        void onSuccess(boolean taskAdded);
    }

    interface OnSuccessListRetrieved {
        void onSuccess(List<Task> currList);
    }

    interface OnSuccessTaskCompletedListener {
        void onSuccess();
    }
    void retrieveList(final OnSuccessListRetrieved listRetrieved);
    void addNewTask(Task newTask, final OnSuccessTaskAddedListener listener);
 }