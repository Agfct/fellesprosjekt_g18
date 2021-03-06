package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Employee implements Serializable{
	private int participantID;
	private int employeeID;
	private String username;
	private String name;
	private String email;
	private String password; // TEST PASSWORD ??
	private ArrayList<Notification> notifications;
	private Boolean isSelected;
	private PropertyChangeSupport pcs;

	//PropertyNames
	public static String NOTIFICATIONS_PROPERTY_NAME = "notifications";

	//Constructor for a viewable person
	public Employee(String name){
		this.name = name;
		this.isSelected = false;
	}

	//Constructor for a user
	public Employee(String name, String email){
		this.isSelected = false;
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
	public boolean equals(Object obj){
		return ((Employee)obj).getEmployeeID() == this.getEmployeeID();
	}

	//Listeners
	public void addPropertyChangedListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangedListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	//---------

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	//Getters/setters
	public String getName() 						{return name;}
	public String getEmail() 						{return email;}
	public String getUsername() 					{return username;}
	public int getParticipantID()					{return participantID;}
	public int getEmployeeID()						{return employeeID;}
	public String getPassword()						{return password;} //test
	public void setName(String name) 				{this.name = name;}
	public void setUsername(String username) 		{this.username = username;}
	public void setEmail(String email) 				{this.email = email;}
	public void setParticipantID(int participantID) {this.participantID=participantID;}
	public void setPassword(String password)		{this.password=password;}
	//---------------

	@Override
	public String toString(){return name;}
}
