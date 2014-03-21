package database;


import database.DBAccess;
import model.*;

import java.util.ArrayList;


public class DBQueries {


	private static String query = "select * from person";
	private static String query2 = "select * from appointment where appointmentID = 2";
	private static String getInv = "select username from person where participantID = (select participantID from invitation where invitationID = 1);";

	public static void fullDBTest() throws Exception {
		DBAccess dba = new DBAccess();
		ArrayList<MeetingRoom> meetingrooms = dba.getAllMeetingRooms();
		MeetingRoom mr1 = meetingrooms.get(0);
		Appointment app = dba.getAppointmentByID(90);
		app.setDeleted(false);
		Employee siv = dba.getEmployeeByParticipantID(11);
		Employee anders = dba.getEmployeeByParticipantID(18);
		Long a = Long.parseLong("48656850648555");
		dba.editAppointment(app);
		
	}


	public static void main(String[] args) throws Exception {
		fullDBTest();

	}




} 