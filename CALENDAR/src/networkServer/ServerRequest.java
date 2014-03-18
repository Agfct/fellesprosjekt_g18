package networkServer;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import database.DBAccess;
import model.Alarm;
import model.Appointment;
import model.Employee;
import model.Invitation;
import model.MeetingRoom;
import model.Notification;
import model.TimeSlot;
import networkDiv.Packet;


public class ServerRequest {
	
	private final DBAccess db;
	
	public ServerRequest () {
		db = new DBAccess();
	}
	
	public Packet getRespose (Packet packet) {
		try {
			System.out.println(packet.getName());
			
			switch (packet.getName()) {
			
			
			// Request packets received from client
			case "LOGIN_REQUEST": return userValidation((String) packet.getObject(0), (String) packet.getObject(1));
			case "ADD_EMPLOYEE": return createEmployee((Employee) packet.getObject(0));
			case "ADD_NOTIFICATION": return createNotification((Notification) packet.getObject(0));
			case "ADD_APPOINTMENT": return createAppointment((Appointment) packet.getObject(0));
			case "EDIT_APPOINTMENT": return editAppointment((Appointment) packet.getObject(0));
			case "GET_ALL_EMPLOYEES": return getAllEmployees();
			case "GET_ALARM_BY_ID": return getAlarmByID((int) packet.getObject(0));
			case "GET_ALL_APPOINTMENTS": return GetAllApointments((int) packet.getObject(0));
			case "GET_ALL_INVITATIONS": return getAllInvitations((int) packet.getObject(0));
			case "GET_ALL_MEETING_ROOMS": return getAllMeetingRooms();
			case "GET_APPOINTMENT_BY_ID": return getAppointmentByID((int) packet.getObject(0));
			case "GET_CREATED_APPOINTMENTS": return getCreatedAppointments((int) packet.getObject(0));
			case "GET_EMPLOYEE_BY_PARTICIPANT_ID": return getEmployeeByParticipantID((int) packet.getObject(0));
			case "GET_EMPLOYEE_BY_USERNAME": return getEmployeeByUsername((String) packet.getObject(0));
			case "GET_GROUP_MEMBERS": return getGroupMembers((String) packet.getObject(0));
			case "GET_INVITATION_BY_ID": return getInvitationByID((int) packet.getObject(0));
			case "GET_INVITED_APPOINTMENTS": return getInvitedAppointments((int) packet.getObject(0));
			case "GET_NOTIFICATIONS_BY_PARTICIPANT_ID": return getNotificationsByParticipantID((int) packet.getObject(0));
			case "GET_SCHEDULE": return getSchedule((String) packet.getObject(0));
			
			
			default: return noResponse();
			}
		}
		catch (Exception e){
			return new Packet("ERROR", e);
		}
	}

	
	

	private Packet userValidation (String username, String password) {
		try {
			boolean checkPassword = db.checkPassword(username, password);
			Employee emp = db.getEmployeeByUsername(username);
			if (checkPassword == true) {
				return new Packet("LOGIN_ACCEPTED", emp);
			}
			else return new Packet("LOGIN_DENIED");
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: userValidation failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: userValidation failed!", e);
		}
	}
	private Packet createEmployee (Employee employee) {
		try {
			db.createEmployee(employee);
			return new Packet("EMPLOYEE_CREATED");
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: createEmployees failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: createEmployees failed!", e);
		}
	}
	private Packet createNotification (Notification notification) {
		try {
			db.createNotification(notification);
			return new Packet("NOTIFICATION_CREATED");
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: createNotification failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: createNotification failed!", e);
		}
	}
	private Packet createAppointment(Appointment object) {
		try {
			db.createAppointment(object);
			return new Packet("APPOINTMENT_CREATED");
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: createAppointment failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: createAppointment failed!", e);
		}
	}
	private Packet editAppointment (Appointment appointment) {
		try {
			db.editAppointment(appointment);
			return new Packet("APPOINTMENT_EDITED");
		}
		catch (Exception e) {
			System.out.println("ServerRequest: editAppointment failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: editAppointment failed!", e);
		}
	}
	private Packet getAllEmployees () {
		try {
			ArrayList<Employee> allEmp = db.getAllEmployees();
			return new Packet("ALL_EMPLOYEES", allEmp);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getAllEmployees failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getAllEmployees failed!", e);
		}
	}
	private Packet getAlarmByID (int alarmID) {
		try {
			Alarm alarm = db.getAlarmByID(alarmID);
			return new Packet("ALARM", alarm);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getAlarmByID failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getAlarmByID failed!", e);
		}
	}
	private Packet GetAllApointments (int participantID) {
		try {
			ArrayList<Appointment> allAppointments = db.getAllAppointments(participantID);
			return new Packet("ALL_APPOINTMENTS", allAppointments);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: GetAllAppointments failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: GetAllAppointments failed!", e);
		}
	}
	private Packet getAllInvitations (int appointmentID) {
		try {
			ArrayList<Invitation> allInvitations = db.getAllInvitationsByAppointmentID(appointmentID);
			return new Packet("ALL_INVITATIONS", allInvitations);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getAllInvitations failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getAllInvitations failed!", e);
		}
	}
	private Packet getAllMeetingRooms () {
		try {
//			ArrayList<MeetingRoom> allMeetingRooms = db.getAllMeetingRooms();
//			return new Packet("ALL_MEETING_ROOMS", allMeetingRooms);
			return new Packet("ALL_MEETING_ROOMS");
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getAllMeetingRooms failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getAllMeetingRooms failed!", e);
		}
	}
	private Packet getAppointmentByID (int appointmentID) {
		try {
			Appointment appointment = db.getAppointmentByID(appointmentID);
			return new Packet("APPOINTMENT", appointment);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getAppointmentByID failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getAppointmentByID failed!", e);
		}
	}
	private Packet getCreatedAppointments (int participantID) {
		try {
			ArrayList<Appointment> appointments = db.getCreatedAppointments(participantID);
			return new Packet("CREATED_APPOINTMENTS", appointments);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getCreatedAppointments failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getCreatedAppointments failed!", e);
		}
	}
	private Packet getEmployeeByParticipantID (int participantID) {
		try {
			Employee employee = db.getEmployeeByParticipantID(participantID);
			return new Packet("EMPLOYEE", employee);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getEmployeeByParticipantID failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getEmployeeByParticipantID failed!", e);
		}
	}
	private Packet getEmployeeByUsername (String username) {
		try {
			Employee employee = db.getEmployeeByUsername(username);
			return new Packet("EMPLOYEE", employee);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getEmployeeByUsername failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getEmployeeByUsername failed!", e);
		}
	}
	private Packet getGroupMembers (String groupname) {
		try {
			ArrayList<Employee> groupMembers = db.getGroupMembers(groupname);
			return new Packet("GROUP_MEMBERS", groupMembers);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getGroupMembers failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getGroupMembers failed!", e);
		}
	}
	private Packet getInvitationByID (int invitationID) {
		try {
			Invitation invitation = db.getInvitationByID(invitationID);
			return new Packet("INVITATION", invitation);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getInvitationByID failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getInvitationByID failed!", e);
		}
	}
	private Packet getInvitedAppointments (int participantID) {
		try {
			ArrayList<Appointment> appointments = db.getInvitedAppointments(participantID);
			return new Packet("INVITED_APPOINTMENTS", appointments);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getInvitedAppointments failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getInvitedAppointments failed!", e);
		}
	}
	private Packet getNotificationsByParticipantID (int participantID) {
		try {
			ArrayList<Notification> notifications = db.getNotificationsByParticipantID(participantID);
			return new Packet("NOTIFICATIONS", notifications);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getNotificationsByParticipantID failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getNotificationsByParticipantID failed!", e);
		}
	}
	private Packet getSchedule (String meetingRoom) {
		try {
			ArrayList<TimeSlot> schedule = db.getSchedule(meetingRoom);
			return new Packet("SCHEDULE", schedule);
		} 
		catch (Exception e) {
			System.out.println("ServerRequest: getSchedule failed!");
			e.printStackTrace();
			return new Packet("ERROR", "ServerRequest: getSchedule failed!", e);
		}
	}	
	
	
	
	private Packet noResponse (){
		return new Packet("NO_RESPONSE");
	}
	
}
