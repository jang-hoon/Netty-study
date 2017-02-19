package kr.lunawyrd.nettypractice.lecture3;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class CommandServer {
	private final int port;
	
	public CommandServer(int port){
		this.port = port;
	}
	
	public void start(){
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			ServerBootstrap server = new ServerBootstrap();
			server.group(group)
				.localAddress(new InetSocketAddress(port))
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						System.out.println("connected");
						ch.pipeline().addLast(new ByteBufToStringDecoder());
						
						
					}
				});
			
			ChannelFuture future = server.bind().sync();
			future.channel().closeFuture().sync();
		}catch(Exception e){
			group.shutdownGracefully();
		}
	}	
}
