package com.example.checklist.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.checklist.databinding.ActivityMainBinding;
import com.example.checklist.databinding.ItemLayoutBinding;
import com.example.checklist.model.Model;
import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;
import com.example.checklist.presenter.Presenter;
import com.example.checklist.presenter.PresenterImpl;
import com.example.checklist.views.sub.RVAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        Presenter, Model {
    private PresenterImpl presenterImpl;
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    private ModelImpl modelImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sharedPreferences = getSharedPreferences(ModelImpl.PREFERENCES_INSTANCE,
                Context.MODE_PRIVATE);
        modelImpl = new ModelImpl(sharedPreferences, this);
        presenterImpl = new PresenterImpl(this, modelImpl);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rv.setLayoutManager(layoutManager);
        binding.addBtn.setOnClickListener(v -> {
            presenterImpl.addTask(binding.taskTitleEt.getText().toString());
            presenterImpl.getTask();
        });
    }

    @Override
    public void displayTaskList(List<Task> taskList) {
        binding.rv.setAdapter(new RVAdapter(taskList));
    }

    @Override
    public void onSuccessTaskAdded(boolean isComplete) {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureTaskAdded(boolean isComplete) {

    }

    @Override
    public void onErrorTaskAdded(boolean isError) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }


}