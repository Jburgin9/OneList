package com.example.checklist.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.checklist.ExceededListSizeException;
import com.example.checklist.databinding.ActivityMainBinding;
import com.example.checklist.repo.Repo;
import com.example.checklist.repo.TaskRepo;
import com.example.checklist.model.Task;
import com.example.checklist.presenter.MainPresenter;
import com.example.checklist.repo.SharedPreferenceSingleton;
import com.example.checklist.views.contracts.View;
import com.example.checklist.views.sub.RVAdapter;
import com.example.checklist.presenter.RVPresenter;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements
        View{
    private MainPresenter mainPresenter;
    private ActivityMainBinding binding;
    private SharedPreferenceSingleton preferenceSingleton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        android.view.View view = binding.getRoot();
        setContentView(view);
        setup();
        mainPresenter.loadDisplay();
        binding.addBtn.setOnClickListener(v -> {
            if(!binding.taskTitleEt.getText().toString().trim().equals("") &&
                !mainPresenter.isUniqueTitle(binding.taskTitleEt.getText().toString().trim())){
                try {
                    mainPresenter.onAddButtonClicked(binding.taskTitleEt.getText().toString().trim());
                } catch (ExceededListSizeException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                binding.taskTitleEt.setText("");
            } else {
                Toast.makeText(this, "Empty or duplicates values will not be accepted",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setup(){
        preferenceSingleton = SharedPreferenceSingleton.getInstance(this);
        TaskRepo taskRepo = new TaskRepo(preferenceSingleton);
        mainPresenter = new MainPresenter(this, taskRepo);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rv.setLayoutManager(layoutManager);

    }

    @Override
    public void setAdapter(List<Task> taskList) {
        Log.d("cc", "setAdapter: " + taskList.size());
        binding.rv.setAdapter(new RVAdapter(taskList));
    }

    @Override
    public void updateRecyclerViewWithNewTask() {
        synchronized (Objects.requireNonNull(binding.rv.getAdapter())){
            mainPresenter.loadDisplay();
            binding.rv.getAdapter().notifyDataSetChanged();
            Log.d("cc", "updateRecyclerViewWithNewTask: ");
        }
    }

}