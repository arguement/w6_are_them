package com.example.douglas.econsociety;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Member {


    protected double attendance = 1;
    protected String name;



    protected String hall;
    protected String cellNumber;
    protected String email;
    protected String id;
    private String progress;
    private int timeScan = 0;


    private String startTime;
    private String endTime;



    private String calcInterval;

    public Member(String name, String idNum, String progress){
        this.name = name;
        this.id = idNum;
        this.progress = progress;

    }
    public Member(String name, String id, String progress, String hall, String cell, String email){
        this.id = id;
        this.name = name;
        this.hall = hall;
        this.cellNumber = cell;
        this.email = email;
        this.progress = progress;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Updates the percentage of meetings attended
    public void updateAttendance(int numMeeting, int totalMeeting){
        double percentage = numMeeting/totalMeeting;
        this.attendance = percentage;
    }



    public String getProgress() {
        return progress;
    }

    public void incrementTimeScan() {
        this.timeScan = this.timeScan+1;
    }

    @com.google.firebase.firestore.Exclude
    public int getTimeScan() {
        return timeScan;
    }

    public void storeStart(){

            this.startTime = getDate();

    }
    public void storeEnd(){


        this.endTime = getDate();

        try {
            this.calcInterval = printDifference(new SimpleDateFormat("hh:mm:ss a").parse(this.startTime), new SimpleDateFormat("hh:mm:ss a").parse(this.endTime));
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("creation", "storeEnd: ");
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public String getCalcInterval() {
        return calcInterval;
    }


    @com.google.firebase.firestore.Exclude
    public String getDate(){
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);

        return  formattedDate;
    }

    public static String printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();
        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;



        return String.format("%d",elapsedMinutes);
    }
}
