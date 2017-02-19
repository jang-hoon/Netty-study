package kr.lunawyrd.nettypractice.lecture2;

public class Example2_4_basic {
	
	public static void main(String[] args) throws Exception {
		if(args.length != 2){
			System.out.println("Usage: " + Example2_4_basic.class.getSimpleName() + " <host> <port>");
			return ;
		}
		
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		new EchoClient(host, port).start();
	}
}
