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
	
	/*public String toString(){
		String output = "";
		output = output + identifier + "\n" + "Arrived: " + startTime + "\n" + "Departed: " + endTime + "\n" + "Duration of Stay: " + Math.round(duration);
		return output;
	}*/
	
/*	public static void main(String[] args){
		Record r = new Record("6257gyd");
		r.setEnd(new Date(118, 10, 23, 23, 30));
		r.calcDuration(r.getStart(), r.getEnd());
		System.out.println(r);
	}*/
	public String getDate(){
		Date date = new Date();
		String strDateFormat = "hh:mm:ss a";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate= dateFormat.format(date);

		return  formattedDate;
	}

}