package gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarModel {
	private ArrayList<Appointment> appointments;
	private ArrayList<Person> persons;
	private Date date;
	
	public CalendarModel(ArrayList<Appointment> appointments, ArrayList<Person> persons) {
		this.appointments = appointments;
		this.persons = persons;
		//Velger dagens dato når programmet starter.
		this.date = new Date();
	}
	
	//Appointments
	public void addAppointment(Appointment appointment){
		appointments.add(appointment);
	}
	
	public void removeAppointment(Appointment appointment){
		appointments.remove(appointment);
	}
	//------------
	
	//Date
	public void setDate(Date date) {
		this.date = date;
	}
	
	//Søndag = 1, Mandag = 2, etc...
	public int getDayOfWeek(){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}
	//----
	
	//Getters
	public Date getDate() 							{return date;}
	public ArrayList<Person> getPersons() 			{return persons;}
	public ArrayList<Appointment> getAppointments() {return appointments;}
}
