package kr.lunawyrd.nettypractice.lecture4;

public class Example4_4_basic {
	
	public static void main(String[] args) {
		try{
			NettyNioServer server = new NettyNioServer();
			server.serve(7777);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
