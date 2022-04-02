package com.example.checklist.views.sub;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checklist.databinding.WorkingTaskLayoutBinding;
import com.example.checklist.model.Task;
import com.example.checklist.presenter.RVPresenter;

import java.util.List;

public class WorkingTasksRVAdapter extends RecyclerView.Adapter<WorkingTasksRVAdapter.TaskViewHolder>{
    private List<Task> taskList;


    public WorkingTasksRVAdapter(List<Task> taskList){
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(WorkingTaskLayoutBinding.inflate(LayoutInflater
                .from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.binding.titleTv.setText(taskList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }


    class TaskViewHolder extends RecyclerView.ViewHolder {
        WorkingTaskLayoutBinding binding;
        public TaskViewHolder(WorkingTaskLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
