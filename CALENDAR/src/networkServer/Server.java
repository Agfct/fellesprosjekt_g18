package networkServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import model.Invitation;
import database.DBAccess;

public class Server implements Runnable {
	
	private static int testPort = 6060;
	
	private int port;
	private boolean running;
	private ServerSocket serverSocket;
	private final DBAccess db;
	private AlarmHandler alarmHandler;
	private Thread activeThread;
	
	
	public Server (int port) {
		this.port = port;
		db = new DBAccess();
		alarmHandler = new AlarmHandler();
	}
	
	public void run () {
		synchronized (this) {
			activeThread = Thread.currentThread();
		}
		
		ArrayList<Invitation> alarmedInvitations = db.getAlarmedInvitations();
		for (Invitation invitation : alarmedInvitations) {
			alarmHandler.setAlarm(invitation);
		}
		
		openServerSocket();
		
		while (!isRunning()) {
			Socket clientSocket;
			
			try {
				clientSocket = serverSocket.accept();
			}
			catch (IOException e) {
				if (isRunning()) {
					System.out.println("Server: Server stopped!");
					return;
				}
				throw new RuntimeException("Server: Could not accept client socket!", e);
			}
			new Thread(new RequestThread(clientSocket, db, alarmHandler)).start();
		}
	}
	
	private void openServerSocket() {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server: Server open!");
		}
		catch (IOException e){
			throw new RuntimeException("Server: Could not open port: " + port, e);
		}
	}
	
//	public void addRequest (Socket clientSocket){
//		new RequestThread(clientSocket).start();
//	}
	
	public synchronized boolean isRunning () {
		return running;
	}

	public synchronized void stop () {
		running = true;
		
		try {
			serverSocket.close();
		}
		catch (IOException e){
			throw new RuntimeException("Server: Error while closing socket!", e);
		}
	}
	
	
	public static void main (String[] args) {
		Server server = new Server(testPort);
		System.out.println("Server: Starting server...");
		new Thread(server).start();
	}
}
