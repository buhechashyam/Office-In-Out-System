package com.example.timertask.room;

import androidx.room.TypeConverter;

import com.example.timertask.model.TimerModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ArrayListConverter {
    @TypeConverter
    public static ArrayList<TimerModel> fromString(String str){

        Type listType = new TypeToken<ArrayList<TimerModel>>(){}.getType();
        return new Gson().fromJson(str,listType);
    }
    @TypeConverter
    public static String fromArrayList(ArrayList<TimerModel> models){

        String string = new Gson().toJson(models);
        return string;
    }
}
