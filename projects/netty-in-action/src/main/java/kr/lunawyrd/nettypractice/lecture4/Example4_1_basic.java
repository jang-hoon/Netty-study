package kr.lunawyrd.nettypractice.lecture4;

import java.io.IOException;

public class Example4_1_basic {

	public static void main(String[] args) {
		try {
			PlainOioServer server = new PlainOioServer();
			server.serve(7777);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
