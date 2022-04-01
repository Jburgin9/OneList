package com.example.checklist.model;

import java.util.List;

public interface Model {
    void onSuccessTaskAdded(boolean taskAdded);
    void onFailureAddError(boolean isError);
}
