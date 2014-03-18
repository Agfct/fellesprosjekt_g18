package model;

public class Notification{
	private final Appointment appointment;
	private final String msg;
	//TODO: Legge til type. Trengs det egentlig?
	
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
