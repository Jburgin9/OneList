package com.example.checklist.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.checklist.R;
import com.example.checklist.databinding.ActivityMainBinding;
import com.example.checklist.databinding.ItemLayoutBinding;
import com.example.checklist.model.Task;
import com.example.checklist.presenter.Presenter;
import com.example.checklist.views.sub.RVAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Presenter.View {
    private Presenter presenter;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        presenter = new Presenter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rv.setLayoutManager(layoutManager);
        presenter.getTask();
    }

    @Override
    public void displayMovies(List<Task> taskList) {
        ItemLayoutBinding itemBinding = ItemLayoutBinding.inflate(getLayoutInflater());
        binding.rv.setAdapter(new RVAdapter(taskList, itemBinding));
    }
}