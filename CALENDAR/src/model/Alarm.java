package model;

public class Alarm {
	private int alarmID;
	private String description;
	private long time;
	
	public int getAlarmID() {
		return alarmID;
	}
	public void setAlarmID(int alarmID) {
		this.alarmID = alarmID;
	}

	//TODO: Fornuftige metoder.
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
