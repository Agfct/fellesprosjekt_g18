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
		Employee siv = dba.getEmployeeByParticipantID(11);

		//dba.createEmployee(siv);
		//System.out.println("createEmployee: Duplicate Siv if correct");

//		System.out.println(dba.getInvitedAppointments(18));
//
//		System.out.println("editInvitation:");
//		Appointment appointment = dba.getAppointmentByID(2);
//		System.out.println(appointment);
//		Invitation invitation = new Invitation(siv, appointment);
//		invitation.setInvitationID(1);
//		invitation.setHidden(true);
//		invitation.setEdited(true);
//		invitation.setAlarmTime(5);
//		System.out.println(invitation.getInvitationID());
//		System.out.println(invitation.getAlarmTime());
//		System.out.println(invitation.isEdited());
//		System.out.println(invitation.isHidden());
//		System.out.println(invitation.getStatus().name());
//		System.out.println(invitation.getAppointment().getAppointmentID());
//		dba.editInvitation(invitation);
		
		System.out.println("getGroupByID:");
		System.out.println(dba.getGroupByID(4));


//
//		System.out.println("createAppointment:");
//		Appointment app = dba.getAppointmentByID(2);
//		dba.createAppointment(app);
//		System.out.println("Duplicate Pers mote ID 2 om korrekt");
//
//		System.out.println("getAllEmployees:");
//		ArrayList<Employee> employees = dba.getAllEmployees();
//		for (Employee employee : employees) {
//			System.out.println(employee);
//		}
//
//
//		System.out.println("getSchedule:");
//		dba.getSchedule("100");
//		ArrayList<TimeSlot> j = dba.getSchedule("10");
//		for (TimeSlot k : j) {
//			System.out.println(k);
//		}
//
//		System.out.println("getAllAppointments:");
//		ArrayList<Appointment> a = dba.getAllAppointments(11);
//		for (Appointment b : a) {
//			System.out.println(b);
//		}
//
//		System.out.println("getGroupMembers:");
//		dba.getGroupMembers("test");
//		ArrayList<Employee> c = dba.getGroupMembers("Gruppe1");
//		for (Employee d : c) {
//			System.out.println(d);
//		}
//
//		System.out.println("getEmployeeByUsername:");
//		dba.getEmployeeByUsername("Invalid user");
//		System.out.println(dba.getEmployeeByUsername("Siv"));
//
//		System.out.println("getAllMeetingRooms:");
//		ArrayList<MeetingRoom> g = dba.getAllMeetingRooms();
//		for (MeetingRoom h : g) {
//			System.out.println(h);
//		}
//
//		System.out.println("checkPassword:");
//		System.out.println(dba.checkPassword(siv.getName(), "Siv123"));
//
//		System.out.println("getAllInvitationsByAppointmentID:");
//		System.out.println(dba.getAllInvitationsByAppointmentID(2));
//		dba.getAllInvitationsByAppointmentID(42);
//
//		System.out.println("getAllInvitationsByParticipantID:");
//		System.out.println(dba.getAllInvitationsByParticipantID(11));
//		dba.getAllInvitationsByParticipantID(20);


	}


	public static void main(String[] args) throws Exception {
		fullDBTest();

	}




} 