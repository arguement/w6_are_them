package com.example.douglas.econsociety;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.ArrayList;

/**
 *  class for opnening a popup window for setting the meeting
 */
public class PopUp extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private RecyclerView mRecyclerView;
    private MeetingMemberAdapter mAdapter;
    private Button post, start,stop;
    private PopUp cont = this;
    private int[] startTime; // stores information on the time picker

    /**
     *  stores the information about when the meeting will end
     */
    private int[] endTime;

    /**
     *  the button id that caused the time popup
     */
    private int initialiseTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.meeting_popup, container, false);

        // Do all the stuff to initialize your custom view
        mRecyclerView = v.findViewById(R.id.add_member_to_meeting);
        mRecyclerView.setHasFixedSize(true);


        /**
         * dummy data for recycle view
         */
        ArrayList<Member> memberList = new ArrayList<>();

        memberList.add(new Member("Garfield Grant","620103582"," 7%"));
        memberList.add(new Member("Horaine McCalla","620103582"," 6%"));
        memberList.add(new Member("Derwent Johnson","620103582"," 5%"));
        memberList.add(new Member("Daniel Battick","620103582"," 4%"));

        mAdapter = new MeetingMemberAdapter(getActivity(),LOFE.showList());
        // Connect the adapter with the recycler view.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRecyclerView.setAdapter(mAdapter);

        /**
         * set on click to to each item inside the recycle view
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                (long) viewHolder.itemView.getTag();
//                mAdapter.notifyItemRemoved(0);
                Log.d("adapter position jordan",viewHolder.getAdapterPosition()+"");
            }
        }).attachToRecyclerView(mRecyclerView);

        post  = v.findViewById(R.id.post);
        final TextView meetingName = v.findViewById(R.id.meeting_name);
        final TextView meetingAgenda = v.findViewById(R.id.agenda);

        start = v.findViewById(R.id.start);
        stop = v.findViewById(R.id.stop);


         // when the post button is clicked it should store the info in firebase

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (startTime == null || endTime == null){
                    return;
                }

                Log.d("jordan","inside on click");


                 // create a meeting object that will store the name of the meeting and the attendance represented as members
                String nameOfEvent = meetingName.getText().toString();
                String agenda = meetingAgenda.getText().toString();


                Log.d("jordan","stores text");

                Meeting meeting = new Meeting(nameOfEvent,agenda,startTime,endTime);
                Log.d("jordan","adds meeting");

                ArrayList<Member> memberList = LOFE.showList();
                Log.d("jordan","declare member list");
                for (Member i: memberList){
                    if (i.isSelected()) {
                        meeting.attendance(i);
                    }
                }
                Log.d("jordan","inside post button");
                LOFE.sendMeeting(meeting);

                cont.dismiss();
            }
        });

        start.setOnClickListener(new Time());
        stop.setOnClickListener(new Time());

        return v;
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        System.out.println("arrives in popup");
        doToast(""+hourOfDay);

        if (initialiseTime == R.id.start){
            this.startTime = new int[2];
            this.startTime[0] = hourOfDay;
            this.startTime[1] = minute;
            System.out.println("start button clicked");
        }
        else if ( initialiseTime == R.id.stop ){
            this.endTime = new int[2];
            this.endTime[0] = hourOfDay;
            this.endTime[1] = minute;
            System.out.println("stop button clicked");
        }

    }


    private class Time implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            initialiseTime = v.getId(); // stores the button that called the time popup
            TimePickerFragment newFragment = new TimePickerFragment();
            newFragment.setListener(cont);
            newFragment.show(getFragmentManager(), "timePicker");
        }
    }

    public void doToast(String mess){
        Toast.makeText(getActivity(),mess,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        Log.d("jordan","destroyed");
        super.onDestroy();
        ArrayList<Member> memberList = LOFE.showList();
        for (Member i: memberList){
            i.setSelected(false);
        }
    }
}
