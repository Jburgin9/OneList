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
import com.example.checklist.presenter.RVPresenter;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TaskViewHolder>{
    private static final String TAG = "Adapter";
    private List<Task> taskList;
    private RVPresenter presenter;

    public RVAdapter(List<Task> taskList, RVPresenter presenter){
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
        int truePosition = holder.getAdapterPosition();
        holder.binding.titleTv.setText(taskList.get(truePosition).getTitle());
        holder.binding.isCompleteBox.setOnCheckedChangeListener(null);
        holder.binding.isCompleteBox.setSelected(taskList.get(truePosition).isCompleted());
        holder.binding.isCompleteBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    taskList.get(truePosition).setCompleted(b);
//                    holder.binding.isCompleteBox.setChecked(taskList.get(truePosition).isCompleted());
//                    presenter.deleteFromTaskList(taskList.get(truePosition));
//                    notifyItemRemoved(truePosition);
//                    notifyDataSetChanged();

                }
            }
        });

        holder.binding.isCompleteBox.setChecked(taskList.get(truePosition).isCompleted());
        holder.binding.isCompleteBox.setOnClickListener(v -> {
            if(taskList.get(truePosition).isCompleted()){
                holder.binding.isCompleteBox.setChecked(taskList.get(truePosition).isCompleted());
                presenter.taskComplete(taskList.get(truePosition));
                notifyItemRemoved(truePosition);
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), "Task Completed", Toast.LENGTH_SHORT).show();
            }
        });


//        holder.binding.trashBtn.setOnClickListener(v -> {
//                presenter.deleteFromTaskList(taskList.get(position));
//                notifyItemRemoved(position);
//                notifyDataSetChanged();
//        });
//        holder.binding.isCompleteBox.setOnClickListener(v -> {
//
//            if(holder.binding.isCompleteBox.isChecked()){
//                Log.d(TAG, "onBindViewHolder: checked" );
//                taskList.get(position).setCompleted(true);
//                presenter.taskComplete(taskList.get(position));
//                notifyItemRemoved(position);
//                notifyDataSetChanged();
//            }
//        });
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
