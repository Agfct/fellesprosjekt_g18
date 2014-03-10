package model;

import java.util.ArrayList;

public class RoomBooker {
	private ArrayList<MeetingRoom> meetingRooms;

	public RoomBooker(ArrayList<MeetingRoom> meetingRooms) {
		this.meetingRooms = meetingRooms;
	}

	public ArrayList<MeetingRoom> getAllRooms() {
		return meetingRooms;
	}
	
	public ArrayList<MeetingRoom> getAvailableRooms(TimeSlot timeSlot) {
		ArrayList<MeetingRoom> availableRooms = new ArrayList<MeetingRoom>();
		
		//Itererer gjennom lista over møterom, og legger ledige rom i en egen liste
		for (int i = 0; i < meetingRooms.size(); i++) {
			if (meetingRooms.get(i).isAvailable(timeSlot)){
				availableRooms.add(meetingRooms.get(i));
			}
		}
		return availableRooms;
	}
	
	public MeetingRoom autoBook(TimeSlot timeSlot, short capasity){
		MeetingRoom meetingRoom = null;
		short minCapasity = Short.MAX_VALUE;
		
		/* Itererer gjennom lista over møterom, og beholder rommet
		 * som både er ledig og har nok og minst kapasitet*/
		for (int i = 0; i < meetingRooms.size(); i++) {
			MeetingRoom temp = meetingRooms.get(i);
			
			if (temp.isAvailable(timeSlot) && temp.getCapacity() > capasity && temp.getCapacity() < minCapasity  ){
				meetingRoom = temp;
				minCapasity = temp.getCapacity();
			}
		}
		return meetingRoom;
	}
}
