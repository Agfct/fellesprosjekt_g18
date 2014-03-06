package gui;

import java.util.ArrayList;

public class MeetingRoom {
	private String name;
	private short capacity;
	private ArrayList<TimeSlot> schedule;

	public MeetingRoom(String name, short capacity, ArrayList<TimeSlot> schedule) {
		this.name = name;
		this.capacity = capacity;
		this.schedule = schedule;
	}
	
	public boolean isAvailable(TimeSlot timeSlot){
		//Gir true om timeSlotet ikke vil overlappe
		return !schedule.contains(timeSlot);
	}
	
	public void addTimeSlot(TimeSlot timeSlot){
		this.schedule.add(timeSlot);
	}
	
	public void removeTimeSlot(TimeSlot timeSlot){
		schedule.remove(timeSlot);
	}

	//Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setCapacity(short capacity) {
		this.capacity = capacity;
	}

	public void setSchedule(ArrayList<TimeSlot> schedule) {
		this.schedule = schedule;
	}
	
	
	//Getters
	public String getName() {return name;}
	public short getCapacity() {return capacity;}
	public ArrayList<TimeSlot> getSchedule() {return schedule;}
}
