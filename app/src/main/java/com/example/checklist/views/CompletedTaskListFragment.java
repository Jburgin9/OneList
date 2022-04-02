package com.example.checklist.views;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.example.checklist.contracts.FragPresenter;
import com.example.checklist.contracts.FragmentComms;
import com.example.checklist.databinding.CompletedTasksFragLayoutBinding;
import com.example.checklist.model.Model;
import com.example.checklist.model.ModelImpl;
import com.example.checklist.model.Task;
import com.example.checklist.repo.SharedPreferenceSingleton;
import com.example.checklist.views.sub.CompletedTasksRVAdapter;

import java.util.List;

public class CompletedTaskListFragment extends BaseTaskFragment
        implements FragmentComms, Model {
    private CompletedTasksFragLayoutBinding binding;
    private FragPresenter presenter;
    private ModelImpl modelImpl;


    @Override
    protected ViewBinding getViewBinding() {
       return CompletedTasksFragLayoutBinding.inflate(getLayoutInflater());
    }

    @Override
    void setupViews() {
        modelImpl = new ModelImpl(this, SharedPreferenceSingleton.getInstance(getContext()));
        presenter = new FragPresenter(modelImpl, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding = (CompletedTasksFragLayoutBinding) getViewBinding();
        binding.completedListRv.setLayoutManager(layoutManager);
    }

    @Override
    public void initCompletedListFrag(List<Task> completedTaskList) {
        binding.completedListRv.setAdapter(new CompletedTasksRVAdapter(completedTaskList));
    }

    @Override
    public void onSuccessTaskAdded(boolean taskAdded) {

    }

    @Override
    public void onFailureAddError(boolean isError) {

    }
}
