package model;

public class AppointmentHandler {
	public static Appointment createAppointment(Employee creator){
		return new Appointment(creator);
	}
	
	public static void editAppointment(Appointment appointment){
		sendNotification(appointment);
		//TODO: Hva skal denne egentlig gj�re? DB-kall?
	}
	
	public static void deleteAppointment(Appointment appointment){
		sendNotification(appointment);
		//TODO: Hva skal denne gj�re? DB-kall?
	}
	
	private static void sendNotification(Appointment appointment){
		//TODO: send notifications
	}
}
