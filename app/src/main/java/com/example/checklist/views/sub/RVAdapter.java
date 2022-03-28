package com.example.checklist.views.sub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checklist.R;
import com.example.checklist.databinding.ItemLayoutBinding;
import com.example.checklist.model.Task;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TaskViewHolder> {
    private List<Task> taskList;
    private ItemLayoutBinding binding;

    public RVAdapter(List<Task> taskList, ItemLayoutBinding binding){
        this.taskList = taskList;
        this.binding = binding;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(binding);
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
        ItemLayoutBinding binding;
        public TaskViewHolder(ItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
