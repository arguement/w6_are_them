package com.example.douglas.econsociety;

public class Executive extends Member{
	private String position;


	public String getPassword() {
		return password;
	}

	private String password;
	
	public Executive(String id, String name, String hall, String cell, String email, String position,String password){
		super(name,id,"", hall, cell, email);
		this.position = position;
		this.password = password;
	}

	public void setPosition(String position){
		this.position = position;
	}
	
	public String getPosition(){
		return this.position;
	}
	
	public String generateReport(){
		//Implement report generator
		return "";
	}
	
	public String toString(){
		String output = "";
		output = output + id + "\n" + name + "\n" + "Position: " + position + "\n" + "Attendance Percentage: " + attendance*100 + "%\n";
		return output;
	}
}