package kr.lunawyrd.nettypractice.lecture1;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Scanner;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 
 * Edited code in example1-4 of lecture1 
 * 
 * @author	Hoon Jang
 * @since	2017.01.16
 */
public class Example1_4_edited {
	
	public static void main(String[] args) {
		Channel channel = new NioSocketChannel();
		new NioEventLoopGroup().register(channel);
		
		ChannelFuture future = channel.connect(new InetSocketAddress("127.0.0.1", 7777));
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					System.out.println("connected writeable=" + future.channel().isWritable());
					
					try(Scanner scanner = new Scanner(System.in)){
						while(true){
							String msg = scanner.nextLine();
							ByteBuf buffer = Unpooled.copiedBuffer(msg, Charset.defaultCharset());
							future.channel().writeAndFlush(buffer);
							if(msg.startsWith("done")){
								break;
							}
						}
					}
				}
				else{
					Throwable cause = future.cause();
					cause.printStackTrace();
				}
			}
		});
	}
	
}
