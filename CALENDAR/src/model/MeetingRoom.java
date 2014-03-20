package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MeetingRoom implements Comparable<MeetingRoom>, Serializable {
	private String name;
	private short capacity;
	private ArrayList<TimeSlot> schedule;

	public MeetingRoom(String name, short capacity, ArrayList<TimeSlot> schedule) {
		this.name = name;
		this.capacity = capacity;
		this.schedule = schedule;
	}
	
	public MeetingRoom(String name, Short capacity) {
		this.name = name;
		this.capacity = capacity;
	}

	//TimeSlots
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
	//---------
	
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
	
	@Override
	public String toString(){
		return name;
	}
	//Getters
	public String getName() 					{return name;}
	public short getCapacity() 					{return capacity;}
	public ArrayList<TimeSlot> getSchedule() 	{return schedule;}

	@Override
	public int compareTo(MeetingRoom otherRoom) {
		if (this.capacity > otherRoom.capacity) {
			return 1;
		}
		else if (this.capacity == otherRoom.capacity) {
			return 0;
		}
		else {
			return -1;
		}
	}
}
