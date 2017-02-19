package kr.lunawyrd.nettypractice.lecture4;

import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class Example4_6_basic {

	public static void main(String[] args) throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
				.channel(NioServerSocketChannel.class)
				.localAddress(new InetSocketAddress(7777))
				.childHandler(new ChannelInitializer<SocketChannel>() {
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
							@Override
							public void channelActive(ChannelHandlerContext ctx) throws Exception {
								final Channel channel = ctx.channel();
								final ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8).retain();
								
								Runnable writer = new Runnable() {
									@Override
									public void run() {
										channel.writeAndFlush(buf.duplicate());
									}
								};
								
								Executor executor = Executors.newCachedThreadPool();
								executor.execute(writer);
								executor.execute(writer);
								executor.execute(writer);
							}
						});
					}
				});
		}finally {
			group.shutdownGracefully().sync();
		}
	}
}
