package com.example.checklist.views;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerFragmentStateAdapter extends FragmentStateAdapter {
    private static final String TAG = "frag";

    public ViewPagerFragmentStateAdapter(@NonNull FragmentManager fragmentManager,
                                         @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d(TAG, "createFragment: " + " " + position);
        if(position == 0){
            return new WorkingTaskListFragment();
        }
        return new CompletedTaskListFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
