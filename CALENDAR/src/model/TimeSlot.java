package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class TimeSlot implements Serializable{
	private long start;
	private long end;
	private long duration;
	
	//New TimeSlot
	public TimeSlot(){}
	
	//TimeSlot fra database
	public TimeSlot(long start, long end) {
		this.start = start;
		this.end = end;
		duration = end - start;
	}
	
	@Override
	public boolean equals(Object timeSlot){
		TimeSlot other = (TimeSlot) timeSlot;
		//Returnerer true hvis timeSlot overlapper this
		return (this.start > other.start && this.start < other.end) || 
				(this.end > other.start && this.end < other.end); 
	}
	
	public void setDuration(long newDur){
		this.duration = newDur;
		if (start != 0) end = start + duration;
	}
	
	public void setStart(long newStart){
		start = newStart;
		if (duration != 0) end = start + duration;
		else if (end != 0) duration = end - start;
	}
	
	public void setEnd(long newEnd){
		end = newEnd;
		if (start != 0) duration = end - start;
	}
	
	public void setDate(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		setStart(c.getTimeInMillis());
	}
	//Getters
	public long getEnd() 		{return end;}
	public long getStart()		{return start;}
	public long getDuration() 	{return duration;}
	public Date getDate(){
		if (start != 0) return new Date(start);
		return new Date();
	}
}
