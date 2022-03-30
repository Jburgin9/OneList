package com.example.checklist.views.sub;

import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;

import java.util.List;

public class RVPresenterImpl {
    private static final String TAG = "RV";
//    private RVPresenter presenter;
    private ModelImpl model;

    public RVPresenterImpl(RVPresenter presenter, ModelImpl model){
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