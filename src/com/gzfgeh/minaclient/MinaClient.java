package com.gzfgeh.minaclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.gzfgeh.socketclient.MinaHandler;

public class MinaClient {
	public static final String HOST = "127.0.0.1";
	public static final int PORT = 8989;
	
	public static void main(String[] args) throws Exception{
		NioSocketConnector connector = new NioSocketConnector();
		connector.setHandler(new MinaHandler());
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("mina_chain", new ProtocolCodecFilter(new TextLineCodecFactory()));
		ConnectFuture future = connector.connect(new InetSocketAddress(HOST, PORT));
		future.awaitUninterruptibly();
		IoSession session = future.getSession();
		BufferedReader inputBufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String inputContent;
		while (!(inputContent = inputBufferedReader.readLine()).equals("bye")){
			session.write(inputContent);
		}
	}

}
