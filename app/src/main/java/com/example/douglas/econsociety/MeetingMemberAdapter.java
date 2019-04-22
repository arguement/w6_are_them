package com.example.douglas.econsociety;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


import android.content.Context;

import io.grpc.internal.SharedResourceHolder;


public class MeetingMemberAdapter extends RecyclerView.Adapter<MeetingMemberAdapter.WordViewHolder> {


    private  ArrayList<Member> MeetingMemberList;
    private  LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder{
//            implements View.OnClickListener {
        public final TextView wordItemView;
        public TextView idNum,prog,dateSignIn;
        final MeetingMemberAdapter mAdapter;
        public View itemView,container;


        public WordViewHolder(View itemView, MeetingMemberAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.meeting_member);
            this.itemView = itemView; // adds the entire view to the variable
            idNum = itemView.findViewById(R.id.meeting_id);
            prog = itemView.findViewById(R.id.meeting_progress);
            container = itemView.findViewById(R.id.container);
            this.mAdapter = adapter;
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            // Get the position of the item that was clicked.
//            int mPosition = getLayoutPosition();
//
//            // Use that to access the affected item in MeetingMemberList.
//            String element = MeetingMemberList.get(mPosition);
//            // Change the word in the MeetingMemberList.
//
//            MeetingMemberList.set(mPosition, "Clicked! " + element);
//            // Notify the adapter, that the data has changed so it can
//            // update the RecyclerView to display the data.
//            mAdapter.notifyDataSetChanged();
//        }
    }

    public MeetingMemberAdapter(Context context, ArrayList<Member> wordList) {
        mInflater = LayoutInflater.from(context);
        this.MeetingMemberList = wordList;
    }


    @Override
    public MeetingMemberAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // Inflate an item view.
        View mItemView = mInflater.inflate(
                R.layout.meeting_member, parent, false);
        return new WordViewHolder(mItemView, this);
    }



    @Override
    public void onBindViewHolder(final MeetingMemberAdapter.WordViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        final String mCurrent = MeetingMemberList.get(position).getName();
        final Member person = MeetingMemberList.get(position);
        // Add the data to the view holder.
        String currentId = MeetingMemberList.get(position).getId();
        String attendace = MeetingMemberList.get(position).getProgress();
        holder.wordItemView.setText(mCurrent);
        holder.idNum.setText(currentId);
        holder.prog.setText(attendace);

//        System.out.println(holder.itemView.);
//        @TargetApi(26)
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            Color c = Color.valueOf(Color.parseColor("#f6f6f6"));
//        }

        holder.container.setBackgroundColor(person.isSelected() ? Color.CYAN : Color.parseColor("#f6f6f6"));
        holder.wordItemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                person.setSelected(!person.isSelected());
                holder.container.setBackgroundColor(person.isSelected() ? Color.CYAN : Color.parseColor("#f6f6f6"));
                return false;
            }

//            @Override
//            public void onClick(View view) {
//                person.setSelected(!person.isSelected());
//                holder.container.setBackgroundColor(person.isSelected() ? Color.CYAN : Color.WHITE);
//            }
        });
    }
//    @TargetApi(26)
//public Color getColor(){
//
//    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//        Color c = Color.valueOf(Color.parseColor("#f6f6f6"));
//        return  c;
//    }
//    return Color;
//
//}

    @Override
    public int getItemCount() {
        return MeetingMemberList.size();
    }
}
