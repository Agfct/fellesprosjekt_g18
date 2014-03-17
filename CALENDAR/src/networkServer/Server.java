package networkServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
	
	private static int testPort = 6060;
	
	private int port;
	private boolean running;
	private ServerSocket serverSocket;
	private Thread activeThread;
	
	
	public Server (int port) {
		this.port = port;
	}
	
	public void run () {
		synchronized (this) {
			activeThread = Thread.currentThread();
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
			new Thread(new RequestThread(clientSocket)).start();
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
