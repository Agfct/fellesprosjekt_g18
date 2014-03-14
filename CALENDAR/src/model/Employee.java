package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Employee {
	private int participantID;
	private String name;
	private String email;
	private String password; // TEST PASSWORD ??
	private ArrayList<Notification> notifications;
	private Boolean isSelected = false; //TEST
	private PropertyChangeSupport pcs;
	
	//PropertyNames
	public static String NOTIFICATIONS_PROPERTY_NAME = "notifications";
	
	//Constructor for a viewable person
	public Employee(String name){
		this.name = name;
	}
	
	//Constructor for a user
	public Employee(String name, String email){
		this.name = name;
		this.email = email;
		notifications = new ArrayList<Notification>();
	}
	
	//TEST
	public Boolean isSelected(){
		return isSelected;
	}
	public void setSelected(boolean b){
		isSelected = b;
	}
	
	//Notifications
	public ArrayList<Notification> getNotifiations() {
		return notifications;
	}
	public void setNotifiation(ArrayList<Notification> notifications) {
		this.notifications = notifications;
	}
	
	public void addNotifiation(Notification notification) {
		//Fjerner notification med samme appointmentID
		ArrayList<Notification> oldList = notifications;
		notifications.remove(notification);
		notifications.add(notification);
		pcs.firePropertyChange(NOTIFICATIONS_PROPERTY_NAME, oldList, notifications);
	}
	
	public void removeNotification(Notification notification){
		ArrayList<Notification> oldList = notifications;
		notifications.remove(notification);
		pcs.firePropertyChange(NOTIFICATIONS_PROPERTY_NAME, oldList, notifications);
	}
	//-------------
	
	//Listeners
	public void addPropertyChangedListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangedListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	//---------
	
	//Getters/setters
	public String getName() 			{return name;}
	public String getEmail() 			{return email;}
	public int getParticipantID()		{return participantID;}
	public String getPassword()			{return password;} //test
	public void setName(String name) 	{this.name = name;}
	public void setEmail(String email) 	{this.email = email;}
	public void setParticipantID(int participantID) {this.participantID=participantID;}
	public void setPassword(String password)		{this.password=password;}
	//---------------
	
	@Override
	public String toString(){return name;}
}
