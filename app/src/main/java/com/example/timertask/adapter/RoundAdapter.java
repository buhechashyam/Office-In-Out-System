package com.example.timertask.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timertask.R;
import com.example.timertask.room.RoundModel;

import java.util.List;

public class RoundAdapter extends RecyclerView.Adapter<RoundAdapter.RoundViewHolder> {

    List<RoundModel> mListRounds;
    Context mContext;

    public RoundAdapter(List<RoundModel> mListRounds, Context context) {
        this.mListRounds = mListRounds;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RoundViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_round,parent,false);

        return new RoundViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoundViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RoundModel roundModel = mListRounds.get(position);
        holder.mTextViewTotalTime.setText(roundModel.getTotalTime());
        holder.mTextViewRoundIndex.setText("Round-" + (holder.getAdapterPosition()+1));

        holder.recyclerViewTimers.setLayoutManager(new LinearLayoutManager(mContext));

        TimerAdapter adapter = new TimerAdapter(roundModel.getTimerModels());
        holder.recyclerViewTimers.setAdapter(adapter);

        if (roundModel.isExpandable() == true) {
            holder.recyclerViewTimers.setVisibility(View.VISIBLE);
            holder.mImageViewDownArrow.setVisibility(View.GONE);
        }else {
            holder.mImageViewDownArrow.setVisibility(View.VISIBLE);
            holder.recyclerViewTimers.setVisibility(View.GONE);
        }

        holder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    isAnyItemExpand(position);
                    roundModel.setExpandable(!roundModel.isExpandable());
                    notifyItemChanged(position);
            }
        });
    }
    private void isAnyItemExpand(int position) {
        int temp = 0;
        for (int i = 0; i < mListRounds.size(); i++) {
            if (mListRounds.get(i).isExpandable()) {
                temp = i;
                break;
            }
        }
        if (temp >= 0 && temp != position) {
            mListRounds.get(temp).setExpandable(false);
            notifyItemChanged(temp);
        }
    }

    @Override
    public int getItemCount() {
        return mListRounds.size();
    }

    public class RoundViewHolder extends RecyclerView.ViewHolder{

        TextView mTextViewRoundIndex, mTextViewTotalTime;
        ConstraintLayout header;
        ImageView mImageViewDownArrow;
        RecyclerView recyclerViewTimers;
        public RoundViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewRoundIndex = itemView.findViewById(R.id.text_round_index);
            mTextViewTotalTime = itemView.findViewById(R.id.text_total_time);
            header = itemView.findViewById(R.id.header);
            mImageViewDownArrow = itemView.findViewById(R.id.img_down_arrow);
            recyclerViewTimers = itemView.findViewById(R.id.recyclerview_timers);
        }
    }
}
