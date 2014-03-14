package database;

import java.lang.reflect.*;
import java.sql.*;
import model.*;
import java.util.ArrayList;

public class DBAccess{
	/**
	private String conURL = "jdbc:mysql://localhost:3306/kalenderDB";
	private Connection con;
	private String dbName;
	private Statement stmt;
	private ResultSet rs;
	
	public ArrayList<Notification> getNotificationsByParticipantID(int participantID) throws Exception{
		try {
			rs = createResultSet(String.format("select * from notification where participantID = %d", participantID));
		    return writeAllNotificationsResultSet(rs);
	    } catch (Exception e) {
	    	throw e;
	    } finally { 
	    	close();
	    }
		
	}
	
	
	public ArrayList<TimeSlot> getSchedule(String room) throws Exception {
		try {
			rs = createResultSet(String.format("select startTime, endTime from appointment natural join appointmentmeetingroom where roomName = \"10\""));
			return writeScheduleResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	public MeetingRoom getAllMeetingRooms() throws Exception {
		try {
			rs = createResultSet("select * from meetingroom");
			return writeMeetingRoomResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	public boolean checkPassword(Employee employee, String password) throws Exception {
		try {
			rs = createResultSet(String.format("select password from employee where participantID = %d", employee.getParticipantID()));
			return (writePasswordResultSet(rs)==password);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	public Employee getEmployeeByParticipantID(int participantID) throws Exception {
		try {
			rs = createResultSet(String.format("select * from employee where participantID = %d", participantID));
			return writeEmployeeResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	public Alarm getAlarmByID(int alarmID) throws Exception {
		try {
			rs = createResultSet(String.format("select * from alarm where participantID = %d", alarmID));
			return writeAlarmResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	public void createNotification(Notification notification) throws Exception{
		stmt = prepareEdit();
		stmt.executeUpdate(String.format("insert into notification values (null, %d, %d, \"%s\", \"%s\") where appointmentID = %d on duplicate key update message = \"%s\"", notification.getAppointmentID(), null, notification.getMessage(), null, notification.getMessage() ));
		
	}
	
	public void createEmployee(Employee employee) throws Exception {
		stmt = prepareEdit();
		stmt.executeUpdate("insert into participant values (null)");
		stmt.executeUpdate(String.format("insert into employee values(\"%s\", \"%s\", (select last_insert_ID()))", employee.getName(), employee.getPassword()));
		
	}
	
	public void editAppointment(Appointment app) throws Exception {
		stmt = prepareEdit();
		stmt.executeUpdate(String.format("update appointment set startTime = %d, endTime = %d, location = \"%s\", description = \"%s\", username = \"%s\" where appointmentID = %d", app.getTimeSlot(), app.getLocation(), app.getDescription(), app.getCreator(), app.getAppointmentID()));
	}
	
	public ArrayList<Employee> getGroupMembers(String groupname) throws Exception {
		try {
			rs = createResultSet(String.format("select * from employee natural join groupeemployee where groupname = \"%s\"", groupname));
			return writeAllEmployeesResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	public ArrayList<Invitation> getAllInvitationsByParticipantID(int participantID) throws Exception {
		try {
			rs = createResultSet(String.format("select * from invitation where participantID = %d", participantID));
		    return writeAllInvitations(rs);
	    } catch (Exception e) {
	    	throw e;
	    } finally { 
	    	close();
	    }	
	}
	
	public ArrayList<Invitation> getAllInvitationsByAppointmentID(int appointmentID) throws Exception {
		try {
			rs = createResultSet(String.format("select * from invitation where appointmentID = %d", appointmentID));
		    return writeAllInvitations(rs);
	    } catch (Exception e) {
	    	throw e;
	    } finally { 
	    	close();
	    }	
	}
	
	public ArrayList<Employee> getAllEmployees() throws Exception {
		try {
			rs = createResultSet("select * from employee");
		    return writeAllEmployeesResultSet(rs);
	    } catch (Exception e) {
	    	throw e;
	    } finally { 
	    	close();
	    }	
	}
	
	public ArrayList<Appointment> getInvitedAppointments(int participantID) throws Exception {
		try {
			rs = createResultSet(String.format("select * from appointment natural join invitation where participantID = %d;", participantID));
		    return writeAllAppointmentsResultSet(rs);
	    } catch (Exception e) {
	    	throw e;
	    } finally { 
	    	close();
	    }	
	}
	
	//perhaps better to use the separate lists to skip sorting back into created/invited for gui
	public ArrayList<Appointment> getAllAppointments(int participantID) throws Exception {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		appointments.addAll(getCreatedAppointments(participantID));
		appointments.addAll(getInvitedAppointments(participantID));
		return appointments;
	}
	
	public ArrayList<Appointment> getCreatedAppointments(int participantID) throws Exception {
		Employee creator = getEmployeeByParticipantID(participantID);
		try {	
			rs = createResultSet(String.format("select * from appointment where creator = %s;", creator.getName()));
		    return writeAllAppointmentsResultSet(rs);
	    } catch (Exception e) {
	    	throw e;
	    } finally { 
	    	close();
	    }	
	}
	
	public Employee getEmployeeByUsername(String user) throws Exception {
		try {
			rs = createResultSet(String.format("select * from employee where username = \"%s\"", user));
			rs.next();
			return writeEmployeeResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally { 
			close();
		}
	}
	
	public Appointment getAppointmentByID(int appointmentID) throws Exception {
		try {
			rs = createResultSet(String.format("select * from appointment where appointmentID = %d", appointmentID));
			rs.next();
			return writeAppointmentResultSet(rs);
		} catch (Exception e) {
			throw e;
		}  finally { 
			close();
		}
	}
	
	public Invitation getInvitationByID(int invitationID) throws Exception {
		try {
			rs = createResultSet(String.format("select * from invitation where invitationID = %d", invitationID));
			rs.next();
			Invitation invitation = writeInvitationResultSet(rs);
			System.out.println(invitation.getInvitationID());
			return setUsername(invitation);
		} catch (Exception e) {
			throw e;
		}  finally { 
			close();
		}
	}
	
	//Method call through String arg, might be useful
	public void getTable(String nameOfTable, String query) throws Exception {
		Class[] paramString = new Class[1];
		paramString[0] = String.class;
		String maker = "make" + nameOfTable + "Query";
		Class cls = this.getClass();
		Object obj = cls.newInstance();
		Method method = cls.getDeclaredMethod(maker, paramString );
		method.invoke(obj, query);
				
	}
	
	
	

private void writeMetaData(ResultSet rs) throws SQLException {
	//unused atm

}

private ArrayList<Notification> writeAllNotificationsResultSet(ResultSet rs) throws Exception {
	ArrayList<Notification> notifications = new ArrayList<Notification>();
	while (rs.next()) {
		Notification notification = writeNotificationResultSet(rs);
		notifications.add(notification);
	}
	return notifications;
}

private Notification writeNotificationResultSet(ResultSet rs) throws SQLException {
	int appointmentID = rs.getInt("appointmentID");
	int participantID = rs.getInt("participantID");
	String message = rs.getString("message");
	String type = rs.getString("type");
	return new Notification(appointmentID, message);
	
}

private ArrayList<TimeSlot> writeScheduleResultSet(ResultSet rs) throws SQLException {
	ArrayList<TimeSlot> schedule = new ArrayList<TimeSlot>();
	while (rs.next()) {
		TimeSlot timeslot = writeTimeSlotResultSet(rs);
		schedule.add(timeslot);
	}
	return schedule;
}

private TimeSlot writeTimeSlotResultSet(ResultSet rs) throws SQLException{
	long startTime = rs.getLong("startTime");
	long endTime = rs.getLong("endTime");
	return new TimeSlot(startTime, endTime);
}

private ArrayList<MeetingRoom> writeAllMeetingRoomResultSet(ResultSet rs) throws Exception {
	ArrayList<MeetingRoom> meetingrooms = new ArrayList<MeetingRoom>();
	while (rs.next()) {
		MeetingRoom meetingroom = writeMeetingRoomResultSet(rs);
		meetingrooms.add(meetingroom);
	}
	return meetingrooms;
}

private  MeetingRoom writeMeetingRoomResultSet(ResultSet rs) throws Exception {
	String roomName = rs.getString("roomName");
	Short capacity = rs.getShort("capacity");
	ArrayList<TimeSlot> schedule = getSchedule(roomName);
	return new MeetingRoom(roomName, capacity, schedule);
}

private Alarm writeAlarmResultSet(ResultSet rs) throws Exception {
	Alarm alarm = new Alarm();
	String type = rs.getString("type");
	int time = rs.getInt("time");
	alarm.setDescription(type);
	alarm.setTime(time);
	return alarm;
}

private ArrayList<Appointment> writeAllAppointmentsResultSet(ResultSet rs) throws Exception {
	ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	while (rs.next()) {
		Appointment appointment = writeAppointmentResultSet(rs);
		appointments.add(appointment);
	}
	return appointments;
}

private ArrayList<Employee> writeAllEmployeesResultSet(ResultSet rs) throws SQLException {
	ArrayList<Employee> employees = new ArrayList<Employee>();
	while (rs.next()) {
		Employee employee = writeEmployeeResultSet(rs);
		employees.add(employee);
	}
	return employees;
}

private ArrayList<Invitation> writeAllInvitations(ResultSet rs) throws Exception {
	ArrayList<Invitation> invitations = new ArrayList<Invitation>();
	while (rs.next()) {
		Invitation invitation = writeInvitationResultSet(rs);
		invitations.add(invitation);
	}
	return invitations;
}




private Employee writeEmployeeResultSet(ResultSet rs) throws SQLException {
	Employee employee = new Employee(rs.getString("username"));
	return employee;
}

private String writePasswordResultSet(ResultSet rs) throws SQLException {
	return rs.getString("password");
}

private Appointment writeAppointmentResultSet(ResultSet rs) throws Exception {
	String creator = rs.getString("creator");
	Employee creatorObject = getEmployeeByUsername(creator);
	Appointment appointment = new Appointment(creatorObject);
	int appointmentID = rs.getInt("appointmentID");
	long start = rs.getLong("startTime");
	long end = rs.getLong("endTime");
	String location = rs.getString("location");
	String description = rs.getString("description");
	//for testing
	System.out.println("StartTime: " + start);
	System.out.println("EndTime: " + end);
	System.out.println("Location: " + location);
	System.out.println("Description: " + description);
	System.out.println("ID: " + appointmentID);
	System.out.println("Creator: " + creator);
	//end test code
	TimeSlot timeslot = new TimeSlot(start, end);
	appointment.setTimeSlot(timeslot);
	appointment.setAppointmentID(appointmentID);
	appointment.setLocation(location);
	appointment.setDescription(description);
	return appointment;
}

private Invitation writeInvitationResultSet(ResultSet rs) throws Exception {
	int participantID = rs.getInt("participantID");
	Employee employee = getEmployeeByParticipantID(participantID);
	Invitation invitation = new Invitation(employee);
	int invitationID = rs.getInt("invitationID");
	int appointmentID = rs.getInt("appointmentID");
	int alarmID = rs.getInt("alarmID");
	boolean statusChanged = rs.getBoolean("statusChanged");
	boolean statusHidden = rs.getBoolean("statusHidden");
	String invitationStatus = rs.getString("invitationStatus");
	//for testing
	System.out.println(invitationID);
	System.out.println(appointmentID);
	System.out.println(participantID);
	System.out.println(alarmID);
	System.out.println(statusHidden);
	//end test code
	InvitationStatus is = InvitationStatus.fromString(invitationStatus);
	invitation.setStatus(is);
	Alarm alarm = getAlarmByID(alarmID);
	invitation.setAlarm(alarm);
	invitation.setEdited(statusChanged);
	invitation.setHidden(statusHidden);
	invitation.setInvitationID(invitationID);
	return invitation;
}

public Invitation setUsername(Invitation invitation) throws Exception {
	try {
		String query = String.format("select * from employee where participantID = %d", invitation.getPerson().getParticipantID());
		System.out.println(invitation.getPerson().getParticipantID());
		rs = createResultSet(query);
		Employee employee =  writeEmployeeResultSet(rs);
		invitation.getPerson().setName(employee.getName());
		System.out.println(employee.getName());
		return invitation;
	} catch (Exception e) {
		throw e;
	} finally { 
		close();
	}
}

//unnecessary
public Appointment setCreator(Appointment appointment) throws Exception {
	try {
		String query = String.format("select * from employee where participantID = %d", appointment.getCreator().getParticipantID());
		System.out.println(appointment.getCreator().getParticipantID() +"Confirm");
		rs = createResultSet(query);
		Employee employee =  writeEmployeeResultSet(rs);
		appointment.setCreator(employee);
		System.out.println(employee);
		return appointment;
	} catch (Exception e) {
		throw e;
	} finally { 
		close();
	}
}

public ResultSet createResultSet(String query) throws Exception {
	try {	
		Class.forName("com.mysql.jdbc.Driver");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		con = DriverManager.getConnection(conURL, "root", "Legend100");
		stmt = con.createStatement();
		return rs = stmt.executeQuery(query);
	
	} catch (Exception e) {
		throw e;
	}
}

public Statement prepareEdit() throws Exception {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		con = DriverManager.getConnection(conURL, "root", "Legend100");
		return con.createStatement();
	} catch (Exception e) {
		throw e;
	}
}

private void close() {
	try {
		if (rs != null) {
			rs.close();
		}
		
		if (stmt != null) {
			stmt.close();
		}
		
		if (con != null) {
			con.close();
		}
	} catch (Exception e) {
		
	}
}
**/
}







