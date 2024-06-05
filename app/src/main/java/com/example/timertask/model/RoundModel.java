package com.example.timertask.model;

import java.util.ArrayList;

public class RoundModel {

    ArrayList<TimerModel> timerModel;

    String mTotalTime;

    public RoundModel(ArrayList<TimerModel> timerModel, String mTotalTime) {
        this.timerModel = timerModel;
        this.mTotalTime = mTotalTime;
    }

    public ArrayList<TimerModel> getTimerModel() {
        return timerModel;
    }

    public void setTimerModel(ArrayList<TimerModel> timerModel) {
        this.timerModel = timerModel;
    }

    public String getmTotalTime() {
        return mTotalTime;
    }

    public void setmTotalTime(String mTotalTime) {
        this.mTotalTime = mTotalTime;
    }
}
