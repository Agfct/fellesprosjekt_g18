package model;

public class Notification{
	private final int appointmentID;
	private final String msg;
	//TODO: Legge til type. Trengs det egentlig?
	
	public Notification(int appointmentID, String msg) {
		this.appointmentID = appointmentID;
		this.msg = msg;
	}
	
	@Override
	public boolean equals(Object notification){
		return this.appointmentID == ((Notification)notification).getAppointmentID();
	}
	
	@Override
	public String toString(){
		return appointmentID + ": " + msg;
	}
	
	//Getter
	public int getAppointmentID() {return appointmentID;}
	public String getMessage(){
		return msg;
	}
}
