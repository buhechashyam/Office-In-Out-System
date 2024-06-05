package com.example.timertask.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timertask.R;
import com.example.timertask.model.TimerModel;

import java.util.ArrayList;

public class TimerAdapter extends RecyclerView.Adapter<TimerAdapter.TimerViewHolder> {

    ArrayList<TimerModel> mListTimes;

    public TimerAdapter(ArrayList<TimerModel> mListTimes) {
        this.mListTimes = mListTimes;
    }

    public void addItem(TimerModel timerModel){

        int size = mListTimes.size();
        if (mListTimes.isEmpty()){
            mListTimes.add(timerModel);
            notifyDataSetChanged();
        }else {
            TimerModel lastModel = mListTimes.get(size-1);
            if (!lastModel.getTime().equals(timerModel.getTime())){
                mListTimes.add(timerModel);
                notifyDataSetChanged();
            }
        }
    }

    public void removeAllItems(){
        mListTimes.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TimerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_count_down,parent,false);
        return new TimerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimerViewHolder holder, int position) {
        TimerModel model = mListTimes.get(position);

        holder.mTextViewDate.setText(model.getDate());
        holder.mTextViewTime.setText(model.getTime());

    }

    @Override
    public int getItemCount() {
        return mListTimes.size();
    }

    public class TimerViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewDate, mTextViewTime;
        public TimerViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewDate = itemView.findViewById(R.id.text_date);
            mTextViewTime = itemView.findViewById(R.id.text_time);
        }
    }
}
