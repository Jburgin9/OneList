package com.example.checklist.presenter;

import com.example.checklist.model.Task;

import java.util.List;

public interface Presenter {
    void displayTaskList(List<Task> taskList);
    void onSuccessTaskAdded(boolean isComplete);
    void onErrorTaskAdded(boolean isError);
}
