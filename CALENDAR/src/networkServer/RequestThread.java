package networkServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import networkDiv.Packet;

public class RequestThread extends Thread{
	
	Socket clientSocket;
	ServerRequest serverRequest;
	ObjectInputStream inputStream;
	ObjectOutputStream outputStream;
	
	
	public RequestThread (Socket clientSocket) {
		System.out.println("RequestThread: Creating request thread...");
		try{
			this.clientSocket = clientSocket;
			serverRequest = new ServerRequest();
			
			outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
			outputStream.flush();
			inputStream = new ObjectInputStream(clientSocket.getInputStream());
			
			System.out.println("RequestThread: Successfully created request thread!");
		}
		catch (IOException e) {
			System.out.println("RequestThread: Failed creating request thread!");
			e.printStackTrace();
		}
	}
	public void run () {
		try {
			while (true){
				Packet packet = (Packet) inputStream.readObject();
				Packet response = serverRequest.getRespose(packet);
				outputStream.writeObject(response);
			}
		}
		catch (SocketException e) {
			System.out.println("RequestThread: Socket not found!");
		}
		catch (IOException | ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

}
