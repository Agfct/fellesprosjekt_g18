package model;

public class Alarm {
	private String description;
	private long time;
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
