package database;


import java.lang.reflect.*;
import java.sql.*;

import model.*;

import java.util.ArrayList;


public class DBAccess{
	private String conURL = "jdbc:mysql://mysql.stud.ntnu.no/areeh_calenderdb";
	private Connection con;
	private Statement stmt;
	
	public DBAccess() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(conURL, "areeh", "Legend100");
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		}
	
	public void editInvitation(Invitation invitation) throws Exception {
		try {
			stmt.executeUpdate(String.format("update invitation set alarmTime = %d, invitationStatus = \"%s\", isNew = false, isHidden = %b, isEdited = %b where invitationID = %d", invitation.getAlarmTime().toInt(), invitation.getStatus().name(), invitation.isHidden(), invitation.isEdited(), invitation.getInvitationID()));	
		} catch ( NullPointerException e) {
			System.err.println("A field in the edit request is null");
			throw e;
		} finally {
			flush();
		}
	}
	
	public void setInvitationAsDeleted(Invitation inv) throws SQLException{
		try {
			stmt.executeUpdate(String.format("update invitation set invDeleted = 1 where invitationID = %d", inv.getInvitationID()));	
		} catch ( NullPointerException e) {
			System.err.println("A field in the edit request is null");
			throw e;
		} finally {
			flush();
		}
	}
	
	public void removeAppointmentMeetingRoom(String roomname, int appID) throws Exception{
		try {
			stmt.executeUpdate(String.format("delete from appointmentmeetingroom where appID = %d and roomname = \"%s\"", appID, roomname));
		} catch (Exception e) {
			System.err.println("Possible invalid ID");
			throw e;
		} finally {
			flush();
		}		
	}
	
	public void removeInvitationByID(int invID) throws Exception {
		try {
			stmt.executeUpdate(String.format("delete from invitation where invitationID = %d", invID));
		} catch (Exception e) {
			System.err.println("Possible invalid ID");
			throw e;
		} finally {
			flush();
		}
	}
	
	public void removeAppointmentByID(int appID) throws Exception {
		try {
			stmt.executeUpdate(String.format("delete from appointment where appointmentID = %d", appID));
		} catch (Exception e) {
			System.err.println("Possible invalid ID");
			throw e;
		} finally {
			flush();
		}
	}
	
	public void setDeletedInvitationByID(int invID) throws Exception {
		try {
			stmt.executeUpdate(String.format("update invitation set invDeleted = 1 where invitationID = %d", invID));
		} catch (Exception e) {
			System.err.println("Possible invalid ID");
			throw e;
		} finally {
			flush();
		}
	}
	
	
	public void setDeletedAppointmentByID(int appID) throws Exception {
		try {
			stmt.executeUpdate(String.format("update appointment set isDeleted = 1 where appointmentID = %d", appID));
		} catch (Exception e) {
			System.err.println("Possible invalid ID");
			throw e;
		} finally {
			flush();
		}
	}
	

	public ArrayList<TimeSlot> getSchedule(String room) throws Exception {
		try {
			ResultSet rs2 = stmt.executeQuery(String.format("select startTime, endTime from appointment natural join appointmentmeetingroom where roomName = \"%s\"", room));
			return writeScheduleResultSet(rs2);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
	}




	public ArrayList<MeetingRoom> getAllMeetingRooms() throws Exception {
		try {
			ResultSet rs = stmt.executeQuery("select * from meetingroom");
			return writeAllMeetingRoomResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
	}


	public boolean checkPassword(String username, String password) throws Exception {
		try {
			Employee employee = getEmployeeByUsername(username);
			if (employee == null) {
				System.err.println("No matching employee");
				return false;
			}
			else{
				ResultSet rs = stmt.executeQuery(String.format("select password from employee where participantID = %d", employee.getParticipantID()));
				String result = writePasswordResultSet(rs);
				return (result.equals(password));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
	}


	public Employee getEmployeeByParticipantID(int participantID) throws Exception {
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from employee where participantID = %d", participantID));
			if (rs.next()) {
				return writeEmployeeResultSet(rs);	
			} else {
				System.err.println("No matching employee");
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
	}


	public void createEmployee(Employee employee) throws Exception {
		try {
			stmt.executeUpdate("insert into participant values (null)");
			stmt.executeUpdate(String.format("insert into employee values(null, \"%s\", \"%s\", (select last_insert_ID()), \"%s\", \"%s\")", employee.getUsername(), employee.getPassword(), employee.getName(), employee.getEmail()));
		} catch ( NullPointerException e) {
			System.err.println("A field in the edit is null");
		}finally {
			flush();
		}


	}
	
	public void createAppointmentMeetingroom(MeetingRoom mr, int id){
		try {
			stmt.executeUpdate(String.format("insert into appointmentmeetingroom values(\"%s\", %d)", mr.getName(), id));

		} catch ( NullPointerException e) {
			System.err.println("A field in the created object is null");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			flush();
		}
	}
		
	
	


	public void createInvitationOnFresh(Invitation invitation, int id){
		try {
			stmt.executeUpdate(String.format("replace into invitation values(null, %d, %d, %d, \"%s\", true, %b, %b, 0)", id, invitation.getEmployee().getParticipantID(), invitation.getAlarmTime(), invitation.getStatus().name(), invitation.isHidden(), invitation.isDeleted() ));

		} catch ( NullPointerException e) {
			System.err.println("A field in the created object is null");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			flush();
		}
	}
	
	public void createInvitationOnStored(Invitation invitation){
		try {
			stmt.executeUpdate(String.format("replace into invitation values(null, %d, %d, %d, \"%s\", true, %b, %b, 0)",invitation.getAppointment().getAppointmentID(), invitation.getEmployee().getParticipantID(), invitation.getAlarmTime(), invitation.getStatus().name(), invitation.isHidden(), invitation.isDeleted() ));

		} catch ( NullPointerException e) {
			System.err.println("A field in the created object is null");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			flush();
		}


	}


	public void editAppointment(Appointment app) throws Exception {
		try {
			stmt.executeUpdate(String.format("update appointment set startTime = %d, endTime = %d, location = \"%s\", description = \"%s\", creator = %d, title = \"%s\", internal = %b, isDeleted = %b where appointmentID = %d", app.getTimeSlot().getStart(), app.getTimeSlot().getEnd(), app.getLocation(), app.getDescription(), app.getCreator().getEmployee().getParticipantID(), app.getTitle(), app.isInternal(), app.isDeleted(), app.getAppointmentID()));
			stmt.executeUpdate(String.format("update invitation set isEdited = 1 where appointmentID = %d", app.getAppointmentID()));
			for (Invitation inv : app.getInvitations()) {
				createInvitationOnStored(inv);
				if(app.isDeleted()){
					setInvitationAsDeleted(inv);
				}
			}
			if (app.isInternal()) {
				int id = app.getAppointmentID();
				createAppointmentMeetingroom(app.getRoom(), id);
			}
		} catch ( NullPointerException e) {
			System.err.println("A field in the edit request is null");
		}finally {
			flush();
		}
	}
	
	public void removeInvitation(Appointment appointment, Employee employee) throws SQLException {
		try {
			stmt.executeUpdate(String.format("delete from invitation where participantID = %d and appointmentID = %d", employee.getParticipantID(), appointment.getAppointmentID()));
		} catch (Exception e) {
			System.err.println("Possible invalid ID");
			throw e;
		} finally {
			flush();
		}
		
	}
	
	public int getLastInsertID() throws Exception {
		try {
			ResultSet rs = stmt.executeQuery("select last_insert_ID()");
			if (rs.next()) {
				return writeLastIDResultSet(rs);				
			} else {
				System.err.println("No last insert id");
				return 0;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
	}
	
	private int writeLastIDResultSet(ResultSet rs) throws SQLException {
		return rs.getInt("last_insert_id()");
	}

	public void createAppointment(Appointment app) throws Exception {
		try {
			stmt.executeUpdate(String.format("insert into appointment values(null, %d, %d, \"%s\", \"%s\", %d, 0, %b, \"%s\")", app.getTimeSlot().getStart(), app.getTimeSlot().getEnd(), app.getLocation(), app.getDescription(), app.getCreator().getEmployee().getParticipantID(), app.isInternal(), app.getTitle()));
			int id = getLastInsertID();
			for (Invitation inv : app.getInvitations()) {
				createInvitationOnFresh(inv, id);
			}
			if (app.isInternal()) {
				createAppointmentMeetingroom(app.getRoom(), id);
				
			}
		} catch ( NullPointerException e) {
			System.err.println("A field in the add request is null");
		} finally {
			flush();
		}
	}
	

	public ArrayList<Employee> getGroupMembers(String groupname) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from employee natural join groupeemployee where groupname = \"%s\"", groupname));
			return writeAllEmployeesResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
	}
	
	public Group getGroupByID(int participantID) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from groupe where participantID = %d", participantID));
			if (rs.next()) {
				return writeGroupResultSet(rs);				
			} else {
				System.err.println("No such group");
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
		
	}
	public MeetingRoom getMeetingRoom(String name) throws Exception{
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from meetingroom where roomName = \"%s\"", name));
			if (rs.next()) {	
				MeetingRoom room = writeMeetingRoomResultSet(rs);
				return room;
			} else {
				System.err.println("No matching room");
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
		
	}




	private Group writeGroupResultSet(ResultSet rs) throws Exception {
		Group group = new Group(rs.getString("groupname"));
		group.setMembers(getGroupMembers(group.getName()));
		return group;
	}

	public ArrayList<Invitation> getAllInvitationsByParticipantID(int participantID) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from invitation where participantID = %d", participantID));
			return writeAllInvitations(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}	
	}


	public ArrayList<Invitation> getAllInvitationsByAppointmentID(int appointmentID) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from invitation where appointmentID = %d", appointmentID));
			return writeAllInvitations(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}	
	}


	public ArrayList<Employee> getAllEmployees() throws Exception {
		try {
			ResultSet rs = stmt.executeQuery("select * from employee");
			return writeAllEmployeesResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}	
	}
	
	


	public ArrayList<Appointment> getInvitedAppointments(int participantID) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from appointment natural join invitation where participantID = %d;", participantID));
			return writeAllAppointmentsResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}	
	}
	
	
	public ArrayList<Appointment> getOtherInvitedAppointments(int participantID, long weekStart, long weekEnd) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from appointment natural join invitation where participantID = %d and startTime between %d and %d;", participantID, weekStart, weekEnd));
			return writeAllAppointmentsResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}	
	}
	
	public ArrayList<Appointment> getAllAppointmentsByWeek(Employee employee, long start, long end) throws Exception {
		int partID = employee.getParticipantID();
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		appointments.addAll(getOtherCreatedAppointments(partID, start, end));
		appointments.addAll(getOtherInvitedAppointments(partID, start, end));
		return appointments;
	}


	//perhaps better to use the separate lists to skip sorting back into created/invited for gui
	public ArrayList<Appointment> getAllAppointments(int participantID) throws Exception {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		appointments.addAll(getCreatedAppointments(participantID));
		appointments.addAll(getInvitedAppointments(participantID));
		return appointments;
	}
	
	public ArrayList<Appointment> getOtherCreatedAppointments(int participantID, long weekStart, long weekEnd) throws Exception {
		Employee creator = getEmployeeByParticipantID(participantID);
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from appointment where creator = %d and startTime between %d and %d;", creator.getParticipantID(), weekStart, weekEnd));
			return writeAllAppointmentsResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}	
	}
	
	

	public ArrayList<Appointment> getCreatedAppointments(int participantID) throws Exception {
		Employee creator = getEmployeeByParticipantID(participantID);
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from appointment where creator = %d;", creator.getParticipantID()));
			return writeAllAppointmentsResultSet(rs);
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}	
	}


	public Employee getEmployeeByUsername(String user) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from employee where username = \"%s\"", user));
			if (rs.next()) {
				return writeEmployeeResultSet(rs);

			} else {
				System.err.println("DBAccess: No matching user!");
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
	}
	
	public ArrayList<Appointment> getDeletedAppointmentsByParticipantID(int partID) throws Exception {
			try {
				ResultSet rs = stmt.executeQuery(String.format("select * from appointment natural join invitation where participantID = %d and isDeleted = 1;", partID));
				return writeAllAppointmentsResultSet(rs);
			} catch (Exception e) {
				throw e;
			} finally {
				flush();
			}	
	}


	public Appointment getAppointmentByID(int appointmentID) throws Exception {
		Appointment app = prepareAppointmentByID(appointmentID);
		app.setInvitations(getAllInvitationsByAppointmentID(appointmentID));
		return app;
	}
	
	public Appointment prepareAppointmentByID(int appointmentID) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from appointment where appointmentID = %d", appointmentID));
			if (rs.next()) {
				return writeAppointmentResultSet(rs);
			} else {
				System.err.println("No matching appointment");
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
		
	}


	public Invitation getInvitationByID(int invitationID) throws Exception {
		try {
			ResultSet rs = stmt.executeQuery(String.format("select * from invitation where invitationID = %d", invitationID));
			if (rs.next()) {	
				Invitation invitation = writeInvitationResultSet(rs);
				return invitation;
			} else {
				System.err.println("No matching invitation");
				return null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
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

	public Connection getCon() {
		return con;
	}

	private void writeMetaData(ResultSet rs) throws SQLException {
		//unused atm


	}

//	DEPRECATED
//	private ArrayList<Notification> writeAllNotificationsResultSet(ResultSet rs) throws Exception {
//		ArrayList<Notification> notifications = new ArrayList<Notification>();
//		while (rs.next()) {
//			Notification notification = writeNotificationResultSet(rs);
//			notifications.add(notification);
//		}
//		return notifications;
//	}


//	DEPRECATED
//	private Notification writeNotificationResultSet(ResultSet rs) throws Exception {
//		int appointmentID = rs.getInt("appointmentID");
//		Appointment appointment = getAppointmentByID(appointmentID);
//		int participantID = rs.getInt("participantID");
//		String message = rs.getString("message");
//		String type = rs.getString("type");
//		return new Notification(appointment, message);
//	}


	private ArrayList<TimeSlot> writeScheduleResultSet(ResultSet rs) throws SQLException {
		ArrayList<TimeSlot> schedule = new ArrayList<TimeSlot>();
		while (rs.next()) {
			TimeSlot timeslot = new TimeSlot(rs.getLong("startTime"), rs.getLong("endTime"));
			schedule.add(timeslot);
		}
		return schedule;
	}


	private ArrayList<MeetingRoom> writeAllMeetingRoomResultSet(ResultSet rs) throws Exception {
		ArrayList<MeetingRoom> meetingrooms = new ArrayList<MeetingRoom>();
		while (rs.next()) {
			MeetingRoom meetingroom = writeMeetingRoomResultSet(rs);
			meetingrooms.add(meetingroom);
		}
		for (MeetingRoom mroom : meetingrooms) {
			mroom.setSchedule(getSchedule(mroom.getName()));
		}
		return meetingrooms;
	}


	private MeetingRoom writeMeetingRoomResultSet(ResultSet rs) throws Exception {
		String roomName = rs.getString("roomName");
		Short capacity = rs.getShort("capacity");
		MeetingRoom meetingroom = new MeetingRoom(roomName, capacity);
		return meetingroom;
		
	}

//	DEPRECATED
//	private Alarm writeAlarmResultSet(ResultSet rs) throws Exception {
//		Alarm alarm = new Alarm();
//		int alarmID = rs.getInt("alarmID");
//		String type = rs.getString("type");
//		int time = rs.getInt("time");
//		alarm.setDescription(type);
//		alarm.setTime(time);
//		alarm.setAlarmID(alarmID);
//		return alarm;
//	}


	private ArrayList<Appointment> writeAllAppointmentsResultSet(ResultSet rs) throws Exception {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		while (rs.next()) {
			Appointment appointment = writeAppointmentResultSet(rs);
			appointment.setInvitations(getAllInvitationsByAppointmentID(appointment.getAppointmentID()));
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
		Employee employee = new Employee(rs.getString("name"), rs.getString("email"));
		employee.setEmployeeID(rs.getInt("employeeID"));
		employee.setParticipantID(rs.getInt("participantID"));
		employee.setUsername(rs.getString("username"));
		return employee;
	}


	private String writePasswordResultSet(ResultSet rs) throws SQLException {
		if (rs.next()) {
			String pass = rs.getString("password");
			return pass;

		} else {
			System.err.println("DBAccess: No matching user or no pass");
			return null;
		}
	}


	private Appointment writeAppointmentResultSet(ResultSet rs) throws Exception {
		int creatorID = rs.getInt("creator");
		Employee creatorObject = getEmployeeByParticipantID(creatorID);
		Appointment appointment = new Appointment(creatorObject);
		int appointmentID = rs.getInt("appointmentID");
		long start = rs.getLong("startTime");
		long end = rs.getLong("endTime");
		String location = rs.getString("location");
		String description = rs.getString("description");
		String title = rs.getString("title");
		appointment.setTitle(title);
		boolean isDeleted = rs.getBoolean("isDeleted");
		boolean internal = rs.getBoolean("internal");
		if (internal && !location.equals("")) {
			MeetingRoom room = getMeetingRoom(location);			
			appointment.setRoom(room);
		}
		appointment.setInternal(internal);

		TimeSlot timeslot = new TimeSlot(start, end);
		appointment.setTimeSlot(timeslot);
		appointment.setAppointmentID(appointmentID);
		appointment.setLocation(location);
		appointment.setDescription(description);
		appointment.setDeleted(isDeleted);
		return appointment;
		}


	private Invitation writeInvitationResultSet(ResultSet rs) throws Exception {
		int participantID = rs.getInt("participantID");
		Employee employee = getEmployeeByParticipantID(participantID);
		Appointment appointment = prepareAppointmentByID(rs.getInt("appointmentID"));
		Invitation invitation = new Invitation(employee, appointment);
		int invitationID = rs.getInt("invitationID");
		boolean isNew = rs.getBoolean("isNew");
		boolean isHidden = rs.getBoolean("isHidden");
		String invitationStatus = rs.getString("invitationStatus");
		int alarmTime = rs.getInt("alarmTime");
		boolean isDeleted = rs.getBoolean("invDeleted");
		boolean isEdited = rs.getBoolean("isEdited");
		try {
			InvitationStatus is = InvitationStatus.valueOf(invitationStatus);			
			invitation.setStatus(is);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("writeInvitation: No invitationStatus set");
		}
		invitation.setAlarmTime(alarmTime);
		invitation.setEdited(isEdited);
		invitation.setNew(isNew);
		invitation.setHidden(isHidden);
		invitation.setInvitationID(invitationID);
		invitation.setDeleted(isDeleted);
		return invitation;
	}


	public Invitation setUsername(Invitation invitation) throws Exception {
		try {
			String query = String.format("select * from employee where participantID = %d", invitation.getEmployee().getParticipantID());
			ResultSet rs = stmt.executeQuery(query);
			Employee employee = writeEmployeeResultSet(rs);
			invitation.getEmployee().setName(employee.getName());
			return invitation;
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
	}


	//unnecessary
	public Appointment setCreator(Appointment appointment) throws Exception {
		try {
			String query = String.format("select * from employee where participantID = %d", appointment.getCreator().getEmployee().getParticipantID());
			ResultSet rs = stmt.executeQuery(query);
			Employee employee = writeEmployeeResultSet(rs);
			appointment.setCreator(new Creator(employee));
			return appointment;
		} catch (Exception e) {
			throw e;
		} finally {
			flush();
		}
	}


	private void flush() {
		try {


			if (stmt != null) {
				
			}


			if (con != null) {
				
			}
		} catch (Exception e) {


		}
	}
}
