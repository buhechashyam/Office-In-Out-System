package com.example.timertask.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.timertask.model.TimerModel;

import java.util.ArrayList;

@Entity(tableName = "rounds-data-table")
public class RoundModel {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "total_time")
    String mTotalTime;
    @ColumnInfo(name = "timer_list")
    @TypeConverters(ArrayListConverter.class)
    ArrayList<TimerModel> timerModels;

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public RoundModel( ArrayList<TimerModel> timerModels,String mTotalTime, boolean isExpandable) {
        this.mTotalTime = mTotalTime;
        this.timerModels = timerModels;
        this.isExpandable = isExpandable;
    }

    @ColumnInfo(name = "expandable")
    boolean isExpandable = false;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTotalTime() {
        return mTotalTime;
    }

    public void setTotalTime(String mTotalTime) {
        this.mTotalTime = mTotalTime;
    }

    public ArrayList<TimerModel> getTimerModels() {
        return timerModels;
    }

    public void setTimerModels(ArrayList<TimerModel> timerModels) {
        this.timerModels = timerModels;
    }
}
