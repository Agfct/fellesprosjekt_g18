package networkClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import networkDiv.Packet;

public class Client {
	
	private static int testPort = 6060;
	private static String testIP = "localhost";
	
	private int port;
	private Socket clientSocket;
	private ObjectInputStream requestInput;
	private ObjectOutputStream requestOutput;
	
	
	public Client (String ip, int port) {
		try {
			System.out.println("Client: Binding socket...");
			clientSocket = new Socket(ip, port);
		}
		catch (IOException e) {
			System.out.println("Client: Failed binding socket!");
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			System.out.println("Client: Creating streams...");
			requestOutput = new ObjectOutputStream(clientSocket.getOutputStream());
			requestOutput.flush();
			requestInput = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("Client: Streams created!");
		}
		catch (IOException e) {
			System.out.println("Client: Failed creating streams!");
			e.printStackTrace();
		}
	}
	
	public Packet request (String name, Object... objects) {
		Packet request = new Packet(name, objects);
		Packet response;
		
		try {
			System.out.println("Client: Sending request...");
			requestOutput.writeObject(request);
			System.out.println("...");
			response = (Packet) requestInput.readObject();
			System.out.println("Client: Response received!");
			return response;
		}
		catch (Exception e) {
			e.printStackTrace();
			return new Packet("Error!", e);
		}
	}
	
}
