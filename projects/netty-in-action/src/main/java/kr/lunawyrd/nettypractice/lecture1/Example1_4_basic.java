package kr.lunawyrd.nettypractice.lecture1;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Example1_4_basic {
	
	public static void main(String[] args) {
		Channel channel = new NioSocketChannel();
		new NioEventLoopGroup().register(channel);
		ChannelFuture future = channel.connect(new InetSocketAddress("127.0.0.1", 7777));
		future.addListener(new ChannelFutureListener() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				if(future.isSuccess()){
					ByteBuf buffer = Unpooled.copiedBuffer("Hello\n", Charset.defaultCharset());
					ChannelFuture wf = future.channel().writeAndFlush(buffer);
				}
				else{
					Throwable cause = future.cause();
					cause.printStackTrace();
				}
			}
		});
	}
}
