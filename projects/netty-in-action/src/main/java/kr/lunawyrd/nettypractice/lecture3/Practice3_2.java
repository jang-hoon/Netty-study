package kr.lunawyrd.nettypractice.lecture3;

import java.util.Scanner;

public class Practice3_2 {

	public static void main(String[] args){
		CommandClient client = new CommandClient("127.0.0.1", 7777);
		client.start();
		
		Scanner scanner = null;
		try{
			scanner = new Scanner(System.in);
			while(true){
				String command = scanner.nextLine();
				client.send(command);
			}
		}finally {
			if(scanner != null)
				scanner.close();
		}
	}
}
