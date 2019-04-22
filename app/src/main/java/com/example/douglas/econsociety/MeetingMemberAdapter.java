package com.example.douglas.econsociety;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


import android.content.Context;


public class MeetingMemberAdapter extends RecyclerView.Adapter<MeetingMemberAdapter.WordViewHolder> {

    private  ArrayList<Member> MeetingMemberList;
    private  LayoutInflater mInflater;

    class WordViewHolder extends RecyclerView.ViewHolder{
//            implements View.OnClickListener {
        public final TextView wordItemView;
        public TextView idNum,prog,dateSignIn;
        final MeetingMemberAdapter mAdapter;


        public WordViewHolder(View itemView, MeetingMemberAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.meeting_member);

            idNum = itemView.findViewById(R.id.meeting_id);
            prog = itemView.findViewById(R.id.meeting_progress);
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
    public void onBindViewHolder(MeetingMemberAdapter.WordViewHolder holder,
                                 int position) {
        // Retrieve the data for that position.
        String mCurrent = MeetingMemberList.get(position).getName();
        // Add the data to the view holder.
        String currentId = MeetingMemberList.get(position).getId();
        String attendace = MeetingMemberList.get(position).getProgress();
        holder.wordItemView.setText(mCurrent);
        holder.idNum.setText(currentId);
        holder.prog.setText(attendace);
    }


    @Override
    public int getItemCount() {
        return MeetingMemberList.size();
    }
}
