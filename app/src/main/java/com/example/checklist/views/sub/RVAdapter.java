package com.example.checklist.views.sub;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.checklist.databinding.ItemLayoutBinding;
import com.example.checklist.model.Task;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TaskViewHolder>{
    private static final String TAG = "Adapter";
    private List<Task> taskList;
    private RVPresenterImpl presenter;

    public RVAdapter(List<Task> taskList, RVPresenterImpl presenter){
        this.taskList = taskList;
        this.presenter = presenter;
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
        holder.binding.titleTv.setText(taskList.get(position).getTitle());
        holder.binding.trashBtn.setOnClickListener(v -> {
                presenter.deleteTask(taskList.get(position));
                notifyItemRemoved(position);
                notifyDataSetChanged();
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
