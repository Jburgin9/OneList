package com.example.checklist.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.checklist.ExceededListSizeException;
import com.example.checklist.databinding.ActivityMainBinding;
import com.example.checklist.model.Model;
import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;
import com.example.checklist.presenter.MainPresenterInterface;
import com.example.checklist.presenter.MainPresenter;
import com.example.checklist.repo.SharedPreferenceSingleton;
import com.example.checklist.views.sub.RVAdapter;
import com.example.checklist.presenter.RVPresenter;
import com.example.checklist.presenter.RVPresenterInterface;

import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements
        MainPresenterInterface, Model {
    private static final String TAG = "Main";
    private MainPresenter mainPresenter;
    private ActivityMainBinding binding;
    private SharedPreferenceSingleton preferenceSingleton;
    private RVPresenter rvPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setup();
        mainPresenter.displayTaskList();
        binding.addBtn.setOnClickListener(v -> {
            if(!binding.taskTitleEt.getText().toString().trim().equals("") &&
                !mainPresenter.isUniqueTitle(binding.taskTitleEt.getText().toString().trim())){
                try {
                    mainPresenter.addTask(binding.taskTitleEt.getText().toString().trim());
                } catch (ExceededListSizeException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                mainPresenter.displayTaskList();
                binding.taskTitleEt.setText("");
            } else {
                Toast.makeText(this, "Empty or duplicates values will not be accepted",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setup(){
        preferenceSingleton = SharedPreferenceSingleton.getInstance(this);
        ModelImpl modelImpl = new ModelImpl(this, preferenceSingleton);
        mainPresenter = new MainPresenter(this, modelImpl);
        rvPresenter = new RVPresenter(modelImpl);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rv.setLayoutManager(layoutManager);
    }

    @Override
    public void displayTaskList(List<Task> taskList) {
        binding.rv.setAdapter(new RVAdapter(taskList, rvPresenter));
    }

    @Override
    public void onSuccessTaskAdded(boolean isComplete) {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }
}