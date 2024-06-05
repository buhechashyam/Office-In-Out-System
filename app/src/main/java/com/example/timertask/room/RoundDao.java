package com.example.timertask.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timertask.R;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RoundDao {

    @Insert
    public void addRound(RoundModel model);

    @Query("SELECT * FROM `rounds-data-table`")
    public List<RoundModel> getAllRounds();
}
