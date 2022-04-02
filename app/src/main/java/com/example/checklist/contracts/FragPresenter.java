package com.example.checklist.contracts;

import com.example.checklist.model.ModelImpl;
import com.example.checklist.views.sub.CompletedTasksRVAdapter;

public class FragPresenter {
    private ModelImpl modelImpl;
    private FragmentComms fragmentComms;

    public FragPresenter(ModelImpl modelImpl, FragmentComms fragmentComms){
        this.modelImpl = modelImpl;
        this.fragmentComms = fragmentComms;
    }




}
