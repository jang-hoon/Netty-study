package kr.lunawyrd.nettypractice.lecture4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Example4_1_basic {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(7777);
			Socket clientSocket = serverSocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			
			String request, response;
			while((request = in.readLine()) !=  null){
				if("Done".equals(request)){
					break;
				}
				response = "processedData";
				out.println(response);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
