package networkDiv;

import java.io.Serializable;

public class Packet implements Serializable{
	
	private final String name;
	private final Object[] objects;
	
	
	public Packet (String name, Object... objects) {
		this.name = name;
		this.objects = objects;
	}
	
	public String getName () {
		return name;
	}
	public Object[] getObjects () {
		return objects;
	}
	public Object getObject (int i) {
		return objects[i];
	}
}
