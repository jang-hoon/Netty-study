package kr.lunawyrd.nettypractice.lecture4;

import java.io.IOException;

public class Example4_2_basic {

	public static void main(String[] args) {
		try {
			PlainNioServer server = new PlainNioServer();
			server.serve(7777);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
