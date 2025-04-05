package com.example.meutreino;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class WorkoutPagerAdapter extends FragmentStateAdapter {
    public WorkoutPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return WorkoutFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 5; // Número de abas
    }
}


