package com.example.checklist.contracts;

import com.example.checklist.model.Task;

import java.util.List;

public interface FragmentComms {
    void initCompletedListFrag(List<Task> completedTaskList);
}