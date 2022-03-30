package com.example.checklist.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements
        MainPresenterInterface, Model {
    private static final String TAG = "share";
    private MainPresenter mainPresenter;
    private ActivityMainBinding binding;
    private SharedPreferenceSingleton preferenceSingleton;
    private ModelImpl modelImpl;
    private RVPresenter rvPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setup();
        Log.d(TAG, "onCreate: " + preferenceSingleton.toString());
        mainPresenter.displayTaskList();
        binding.addBtn.setOnClickListener(v -> {
            mainPresenter.addTask(binding.taskTitleEt.getText().toString());
            mainPresenter.displayTaskList();
            binding.taskTitleEt.setText("");
        });
    }

    public void setup(){
        preferenceSingleton = SharedPreferenceSingleton.getInstance(this);
        modelImpl = new ModelImpl(this, preferenceSingleton);
        mainPresenter = new MainPresenter(this, modelImpl);
        rvPresenter = new RVPresenter(modelImpl);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rv.setLayoutManager(layoutManager);
    }

    @Override
    public void displayTaskList(List<Task> taskList) {
        Log.d(TAG, "onCreate: ");
        binding.rv.setAdapter(new RVAdapter(taskList, rvPresenter));
    }

    @Override
    public void onSuccessTaskAdded(boolean isComplete) {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureAddError(boolean isComplete) {

    }

    @Override
    public void onSuccessTaskRemoved(boolean isComplete) {

    }

    @Override
    public void onFailureRemoveError(boolean isComplete) {

    }
}