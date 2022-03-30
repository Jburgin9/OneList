package com.example.checklist.presenter;

import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;

import java.util.List;

public class RVPresenter {
    private static final String TAG = "RV";
//    private RVPresenter presenter;
    private ModelImpl model;

    public RVPresenter(RVPresenterInterface presenter, ModelImpl model){
//        this.presenter = presenter;
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
