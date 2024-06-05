package com.example.timertask.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timertask.R;
import com.example.timertask.adapter.RoundAdapter;
import com.example.timertask.databinding.FragmentDashboardBinding;
import com.example.timertask.room.AppDatabase;
import com.example.timertask.room.RoundModel;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    RoundAdapter adapter;
    FragmentDashboardBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(getLayoutInflater());

        List<RoundModel> roundModels = AppDatabase.getInstance(requireContext()).getRoundDao().getAllRounds();
        adapter = new RoundAdapter(roundModels,requireContext());
        binding.recyclerviewRounds.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerviewRounds.setAdapter(adapter);
        return binding.getRoot();
    }
}