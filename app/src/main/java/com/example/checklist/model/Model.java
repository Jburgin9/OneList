package com.example.checklist.model;

public interface Model {
    void onSuccessTaskAdded(boolean isComplete);
    void onFailureTaskAdded(boolean isComplete);
}
