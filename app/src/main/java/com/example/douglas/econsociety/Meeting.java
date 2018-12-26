package com.example.douglas.econsociety;

import java.text.SimpleDateFormat;
import java.util.*;
public class Meeting {


    protected Date date =  new Date();
  //protected Member memberName = new Member();
    protected ArrayList  attendanceList = new ArrayList();


    protected String meetingIdentifier(){
        SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy");
       String meetingName = "LOFE" + dt.format(date);
        return meetingName;
    }

    protected void attendance(Member m){
       String name = m.getName();
       attendanceList.add(name);
    }

}
