package kr.lunawyrd.nettypractice.lecture3;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class CommandClient extends Thread{
	private final String ip;
	private final int port;
	
	private SocketChannel channel;
	
	public CommandClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	@Override
	public void run() {
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap client = new Bootstrap();
			client.group(group)
				.remoteAddress(new InetSocketAddress(ip, port))
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>(){
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						System.out.println("connected");
						channel = ch;
					}
				});
			ChannelFuture future = client.connect().sync();
			future.channel().closeFuture().sync();
		}catch (Exception e) {
			group.shutdownGracefully();
		}
	}
	
	public void send(String message){
		if(channel != null && channel.isOpen() && channel.isActive()){
			System.out.println("send :" + message);
			channel.writeAndFlush(Unpooled.copiedBuffer(message, CharsetUtil.UTF_8));
		}
	}
}
