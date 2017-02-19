package kr.lunawyrd.nettypractice.lecture4;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.channel.local.LocalServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;

public class Example4_5_basic {

	public static void main(String[] args) throws Exception{
		EventLoopGroup group = new LocalEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
				.channel(LocalServerChannel.class)
				.localAddress(new InetSocketAddress(7777))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
							@Override
							public void channelActive(ChannelHandlerContext ctx) throws Exception {
								Channel channel = ctx.channel();
								ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
								ChannelFuture cf = channel.writeAndFlush(buf);
								cf.addListener(new ChannelFutureListener() {
									@Override
									public void operationComplete(ChannelFuture future) throws Exception {
										if(future.isSuccess()){
											System.out.println("Write successful");
										}
										else{
											System.out.println("Write erro");
											future.cause().printStackTrace();
										}
									}
								});
							}
						});
					}
				});
		}finally {
			group.shutdownGracefully().sync();
		}
	}
}
