package com.example.checklist.views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.checklist.databinding.ActivityMain1Binding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity1 extends AppCompatActivity {
    private ActivityMain1Binding binding;
    private ViewPagerFragmentStateAdapter viewPagerFragmentStateAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewPagerFragmentStateAdapter = new
                ViewPagerFragmentStateAdapter(getSupportFragmentManager(), getLifecycle());
        binding.viewpager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        binding.viewpager2.setAdapter(viewPagerFragmentStateAdapter);

        binding.tabs.addTab(binding.tabs.newTab().setText("Working Tasks"));
        binding.tabs.addTab(binding.tabs.newTab().setText("Completed Tasks"));

        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewpager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
