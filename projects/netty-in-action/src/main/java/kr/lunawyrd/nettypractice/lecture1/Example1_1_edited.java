package kr.lunawyrd.nettypractice.lecture1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * Edited code in example1-1 of lecture1 
 * 
 * @author	Hoon Jang
 * @since	2017.01.16
 */
public class Example1_1_edited {

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		try{
			serverSocket = new ServerSocket(7777);
			while(true){
				Socket clientSocket = serverSocket.accept();
				new ProcessingThread(clientSocket).start();
			}
		}finally {
			if(serverSocket != null){
				serverSocket.close();
			}
		}
	}
	
	private static class ProcessingThread extends Thread{
		private Socket socket;
		
		public ProcessingThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			String clientAddress = socket.getInetAddress().getHostAddress();
			System.out.println("[" + clientAddress + "] connected");
			try(
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream());
			){
				String request;
				while((request = in.readLine()) != null){
					if(request.startsWith("done")){
						break;
					}
					
					System.out.println("[" + clientAddress + "] " + request);
					out.println(request);
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("[" + clientAddress + "] connection closed");
		}
	}
}
