package com.example.timertask.view.fragments;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.timertask.adapter.TimerAdapter;
import com.example.timertask.databinding.FragmentTimerBinding;
import com.example.timertask.model.TimerModel;
import com.example.timertask.room.AppDatabase;
import com.example.timertask.room.RoundModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimerFragment extends Fragment {

    FragmentTimerBinding binding;
    long pauseOffset = 0;
    boolean isRunning = false;
    ArrayList<TimerModel> mListTimes;
    TimerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTimerBinding.inflate(getLayoutInflater());

        mListTimes = new ArrayList<>();

        adapter = new TimerAdapter(mListTimes);

        binding.chronometer.setText("00:00");

        binding.recyclerviewTimeData.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerviewTimeData.setAdapter(adapter);
        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    binding.chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    binding.chronometer.start();
                    binding.btnStart.setText("pause");
                    isRunning = true;
                } else {
                    binding.chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - binding.chronometer.getBase();
                    binding.btnStart.setText("resume");
                    isRunning = false;

                    adapter.addItem(new TimerModel(getCurrentDate(), binding.chronometer.getText().toString()));

                }
            }
        });

        binding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isRunning == true){
                    adapter.addItem(new TimerModel(getCurrentDate(), binding.chronometer.getText().toString()));
                }
                binding.chronometer.stop();

                if (!mListTimes.isEmpty()){
                    RoundModel roundModel = new RoundModel(mListTimes, String.valueOf(binding.chronometer.getText()), false);
                    AppDatabase.getInstance(requireContext()).getRoundDao().addRound(roundModel);
                }else {
                    Toast.makeText(requireContext(), "please start a timer!", Toast.LENGTH_SHORT).show();
                }

                binding.chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                binding.btnStart.setText("Start");

                adapter.removeAllItems();
            }
        });

        return binding.getRoot();
    }

    public String getCurrentDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa");
        String currentDate = simpleDateFormat.format(date);

        return currentDate;
    }
}
