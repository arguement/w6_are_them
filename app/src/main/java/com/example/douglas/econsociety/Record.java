package com.example.douglas.econsociety;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record{
	private String identifier;
	private Double duration;
	private Date startTime;
	private Date endTime;
	private Member member;
	
	public Record(String id){
		this.identifier = id;
		this.duration = new Double(0);
		this.startTime = new Date();
		this.endTime = new Date();
	}
	
	public String getId(){
		return this.identifier;
	}
	
	public Double getDuration(){
		return this.duration;
	}
	
	public void calcDuration(Date start, Date end){
		Integer durStay = new Integer(((int)(end.getTime() - start.getTime())));
		this.duration = durStay.doubleValue()/60000;
	}
	
	public Date getStart(){
		return this.startTime;
	}
	
	public Date getEnd(){
		return this.endTime;
	}
	
	public void setStart(Date time){
		this.startTime = time;
	}
	
	public void setEnd(Date time){
		this.endTime = time;
	}
	

	public String getDate(){
		Date date = new Date();
		String strDateFormat = "hh:mm:ss a";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate= dateFormat.format(date);

		return  formattedDate;
	}

}