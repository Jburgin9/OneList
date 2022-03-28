package com.example.checklist.presenter;

import com.example.checklist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Presenter {
    private Presenter.View viewInterface;

    public Presenter(Presenter.View viewInterface){
        this.viewInterface = viewInterface;
    }

    public void getTask(){
        List<Task> testList = new ArrayList<>();
        testList.add(new Task("One", false));
        viewInterface.displayMovies(testList);
    }


    public interface View {
        void displayMovies(List<Task> taskList);
    }
}
