package model;

import java.util.ArrayList;

public class Employee {
	private String name;
	private String email;
	private ArrayList<Notification> notifications;
	private Boolean isSelected = false; //TEST
	
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
		notifications.remove(notification);
		notifications.add(notification);
	}
	
	public void removeNotification(Notification notification){
		notifications.remove(notification);
	}
	//-------------
	
	//Getters/setters
	public String getName() 			{return name;}
	public String getEmail() 			{return email;}
	public void setName(String name) 	{this.name = name;}
	public void setEmail(String email) 	{this.email = email;}
	//---------------
	
	@Override
	public String toString(){return name;}
}