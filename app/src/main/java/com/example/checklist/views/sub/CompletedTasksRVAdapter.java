package com.example.checklist.views.sub;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checklist.databinding.CompletedTasksFragLayoutBinding;
import com.example.checklist.model.Task;

import java.util.List;

public class CompletedTasksRVAdapter extends
        RecyclerView.Adapter<CompletedTasksRVAdapter.CompletedTasksViewHolder> {

    private List<Task> completedTaskList;

    public CompletedTasksRVAdapter(List<Task> completedTaskList){
        this.completedTaskList = completedTaskList;
    }

    @NonNull
    @Override
    public CompletedTasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CompletedTasksViewHolder(CompletedTasksFragLayoutBinding
                .inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedTasksViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return completedTaskList.size();
    }

    class CompletedTasksViewHolder extends RecyclerView.ViewHolder {
        CompletedTasksFragLayoutBinding binding;
        public CompletedTasksViewHolder(CompletedTasksFragLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
