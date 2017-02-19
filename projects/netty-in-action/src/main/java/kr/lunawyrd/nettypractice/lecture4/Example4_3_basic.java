package kr.lunawyrd.nettypractice.lecture4;

public class Example4_3_basic {

	public static void main(String[] args) {
		try{
			NettyOioServer server = new NettyOioServer();
			server.serve(7777);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
