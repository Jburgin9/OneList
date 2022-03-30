package com.example.checklist.presenter;

import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;

import java.util.List;

public class RVPresenter {
    private static final String TAG = "RV";

    private ModelImpl model;

    public RVPresenter(ModelImpl model){
        this.model = model;
    }

    public void deleteTask(Task task){
        List<Task> currList = model.getTaskList();
        int deleteIdx = currList.indexOf(task);
        currList.remove(deleteIdx);
        model.updateList(currList);
//        presenter.displayList(updateList);
    }
}
