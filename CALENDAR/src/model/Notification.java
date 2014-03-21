package model;

import java.io.Serializable;

public class Notification implements Serializable{
	private final Appointment appointment;
	private final String msg;
	
	public Notification(Appointment appointment, String msg) {
		this.appointment = appointment;
		this.msg = msg;
	}
	
	@Override
	public boolean equals(Object notification){
		return this.appointment == ((Notification)notification).getAppointment();
	}
	
	@Override
	public String toString(){
		return appointment + ": " + msg;
	}
	
	//Getter
	public Appointment getAppointment() {return appointment;}
	public String getMessage(){
		return msg;
	}
}
