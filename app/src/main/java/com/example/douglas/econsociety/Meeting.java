package com.example.douglas.econsociety;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;

import com.google.firebase.firestore.Exclude;

import android.text.format.DateUtils;

import java.text.SimpleDateFormat;


import java.time.LocalTime;
import java.util.*;
public class Meeting {




//    private LocalTime timeKeeping;
    private ArrayList<Member>  attendanceList = new ArrayList<Member>();
    private String meetingName;
    private String meetingAgenda;
    private String beginning;
    private String end;


    public  Meeting(String name,String meetingAgenda,int[] start,int[] end){
        this.meetingName = name;
        this.meetingAgenda = meetingAgenda;
        this.beginning = getTime(start[0],start[1]);
        this.end = getTime(end[0],end[1]);



    }


    public String meetingIdentifier(){
        SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy");
        Date date = new Date();
       String meetingName = "LOFE" + dt.format(date);
        return meetingName;
    }

    public void attendance(Member m){
//       String name = m.getName();
       attendanceList.add(m);
    }

//    public LocalTime timeKeeping(){
//        return timeKeeping;
//    }

    @com.google.firebase.firestore.Exclude
    public Member getMember(){
        return new Member(null,null,null);
    }

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }


    public String getMeetingAgenda() {
        return meetingAgenda;
    }
    public ArrayList<Member> getAttendanceList(){
        return this.attendanceList;
    }


    public String getBeginning() {
        return beginning;
    }




    public String getEnd() {
        return end;
    }

    @Exclude
    private String getTime(int a, int b){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR, a);
        c.set(Calendar.MINUTE, b);

        SimpleDateFormat s = new SimpleDateFormat("hh:mm",Locale.ENGLISH);
        Date jor = c.getTime();

        return s.format(jor);
    }

    @Override
    public String toString() {
        return "Meeting{" +

                ", attendanceList=" + attendanceList +
                ", meetingName='" + meetingName + '\'' +
                ", meetingAgenda='" + meetingAgenda + '\'' +
                '}';
    }
}
