package com.example.checklist.presenter;

import com.example.checklist.ExceededListSizeException;
import com.example.checklist.model.Task;

import java.util.List;

public interface Presenter {
    void onAddButtonClicked(String title) throws ExceededListSizeException;
    void onCheckButtonClicked();
}