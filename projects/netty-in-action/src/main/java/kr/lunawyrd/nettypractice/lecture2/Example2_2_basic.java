package kr.lunawyrd.nettypractice.lecture2;

public class Example2_2_basic {
	public static void main(String[] args) throws Exception{
		if(args.length != 1){
			System.out.println("Usage:" + Example2_2_basic.class.getSimpleName() + " <port>");
			return ;
		}
		
		int port = Integer.parseInt(args[0]);
		new EchoServer(port).start();
	}
}
