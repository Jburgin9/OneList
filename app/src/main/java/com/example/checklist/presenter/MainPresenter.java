package com.example.checklist.presenter;

import android.util.Log;

import com.example.checklist.ExceededListSizeException;
import com.example.checklist.repo.Repo;
import com.example.checklist.repo.TaskRepo;
import com.example.checklist.model.Task;
import com.example.checklist.views.contracts.View;

import java.util.List;

public class MainPresenter implements Presenter, Repo.OnSuccessTaskAddedListener, Repo.OnSuccessListRetrieved {
    private final View view;
    private final TaskRepo taskRepo;

    public MainPresenter(View view, TaskRepo taskRepo){
        this.view = view;
        this.taskRepo = taskRepo;
    }

    public void loadDisplay(){
        taskRepo.retrieveList(this);
    }


    public boolean isUniqueTitle(String taskTitle){
        return taskRepo.isTitleUnique(taskTitle);
    }

    @Override
    public void onAddButtonClicked(String title) throws ExceededListSizeException{
        if(taskRepo.listSize() == 5) throw new ExceededListSizeException("Unable to add more than 5 tasks at current level");
        Task newTask = new Task(title, false);
        Log.d("cc", "onAddButtonClicked: " + newTask.getTitle());
        taskRepo.addNewTask(newTask, this);
    }

    @Override
    public void onCheckButtonClicked() {

    }

    @Override
    public void onSuccess(boolean taskAdded) {
        if(taskAdded){
            Log.d("cc", "onSuccess: " + taskAdded);
            view.updateRecyclerViewWithNewTask();
        }
    }

    @Override
    public void onSuccess(List<Task> currList) {
        view.setAdapter(currList);
    }
}
