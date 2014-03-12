package model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarModel {
	private ArrayList<Appointment> appointments;
	private ArrayList<Employee> persons;
	private Date date;
	private PropertyChangeSupport pcs;
	
	//PropertyNames
	public static String APPOINTMENTS_PROPERTY_NAME = "appointments";
	public static String DATE_PROPERTY_NAME = "date";
	
	public CalendarModel(ArrayList<Appointment> appointments, ArrayList<Employee> persons) {
		this.appointments = appointments;
		this.persons = persons;
		//Velger dagens dato når programmet starter.
		this.date = new Date();
	}
	
	//Appointments
	public void addAppointment(Appointment appointment){
		ArrayList<Appointment> oldList = appointments;
		appointments.add(appointment);
		pcs.firePropertyChange(APPOINTMENTS_PROPERTY_NAME, oldList, appointments);
	}
	
	public void removeAppointment(Appointment appointment){
		ArrayList<Appointment> oldList = appointments;
		appointments.remove(appointment);
		pcs.firePropertyChange(APPOINTMENTS_PROPERTY_NAME, oldList, appointments);
	}
	//------------
	
	//Date
	public void setDate(Date date) {
		Date oldDate = this.date;
		this.date = date;
		pcs.firePropertyChange(DATE_PROPERTY_NAME, oldDate, this.date);
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
	public ArrayList<Employee> getPersons() 			{return persons;}
	public ArrayList<Appointment> getAppointments() {return appointments;}
}
