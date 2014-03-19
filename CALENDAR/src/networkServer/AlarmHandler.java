package networkServer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import model.Appointment;
import model.Employee;
import model.Invitation;

public class AlarmHandler {
	
	private Timer timer;
	private EmailHandler emailHandler;

	
	public AlarmHandler () {
		timer = new Timer();
		emailHandler = new EmailHandler();
	}
	
	public void setAlarm (Invitation invitation){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(invitation.getAlarmTime());
		Date alarmTime = calendar.getTime();
		timer.schedule(new AlarmTask(invitation.getAppointment(), invitation.getEmployee()), alarmTime);
	}
	
	class AlarmTask extends TimerTask{
		private Appointment appointment;
		private Employee employee;
		
		public AlarmTask(Appointment appointment, Employee employee) {
			this.appointment = appointment;
			this.employee = employee;
		}
		
		public void executeAlarm() {
			String msg = String.format("Reminder for %s. The meeting starts at %d", appointment.getDescription(), appointment.getStartTime());
			emailHandler.sendEmail(employee.getEmail(), msg);
		}
		public void run () {
			executeAlarm();
			cancel();
		}
		
		
	}
}
