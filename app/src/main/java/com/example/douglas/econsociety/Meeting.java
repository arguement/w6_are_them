package com.example.douglas.econsociety;

import java.text.SimpleDateFormat;
import java.util.*;
public class Meeting {




    private Date timeKeeping;
    private ArrayList<Member>  attendanceList = new ArrayList<Member>();


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
    public int timeKeeping(){
        return 0;
    }

    public Member getMember(){
        return new Member(null,null,null);
    }

}
