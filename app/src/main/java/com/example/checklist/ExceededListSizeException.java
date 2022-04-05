package com.example.checklist;

public class ExceededListSizeException extends Exception{
    public ExceededListSizeException(String errorMessage){
        super(errorMessage);
    }
}
