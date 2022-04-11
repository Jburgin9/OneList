package com.example.checklist.views.sub;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checklist.databinding.ItemLayoutBinding;
import com.example.checklist.model.Task;
import com.example.checklist.presenter.MainPresenter;
import com.example.checklist.presenter.RVPresenter;
import com.example.checklist.repo.Repo;
import com.example.checklist.repo.SharedPreferenceSingleton;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TaskViewHolder> {
    private List<Task> taskList;
    private SharedPreferenceSingleton preferenceSingleton;

    public RVAdapter(List<Task> taskList){
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding binding = ItemLayoutBinding.inflate(LayoutInflater
        .from(parent.getContext()), parent, false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        int truePosition = holder.getAdapterPosition();
        holder.binding.titleTv.setText(taskList.get(truePosition).getTitle());
        holder.binding.isCompleteBox.setOnCheckedChangeListener(null);
        holder.binding.isCompleteBox.setSelected(taskList.get(truePosition).isCompleted());
        holder.binding.isCompleteBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    taskList.get(truePosition).setCompleted(b);
                }
            }
        });

        holder.binding.isCompleteBox.setChecked(taskList.get(truePosition).isCompleted());
        holder.binding.isCompleteBox.setOnClickListener(v -> {
            if(taskList.get(truePosition).isCompleted()){
                holder.binding.isCompleteBox.setChecked(taskList.get(truePosition).isCompleted());
                Task currTask = taskList.get(truePosition);
                preferenceSingleton = SharedPreferenceSingleton.getInstance(holder.binding.getRoot().getContext());
                preferenceSingleton.getSavedCompletedList().add(currTask);
                preferenceSingleton.getSavedTitles().remove(currTask.getTitle());
                int deleteIdx = taskList.indexOf(currTask);
                taskList.remove(currTask);
                preferenceSingleton.saveList(taskList, preferenceSingleton.getSavedTitles());
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });
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