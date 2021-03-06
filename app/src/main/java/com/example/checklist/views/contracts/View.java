package com.example.checklist.views.contracts;

import com.example.checklist.model.Task;

import java.util.List;

public interface View {
    void setAdapter(List<Task> taskList);
    void updateRecyclerViewWithNewTask();
}
