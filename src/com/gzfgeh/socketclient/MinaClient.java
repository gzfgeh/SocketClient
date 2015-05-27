package com.gzfgeh.socketclient;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChain;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {
	private static final int PORT = 8989;
	
	public static void main(String[] args) {
		NioSocketConnector connector = new NioSocketConnector();
		connector.setHandler(new MinaHandler());
		DefaultIoFilterChainBuilder chain = connector.getFilterChain();
		chain.addLast("mina_chain", new ProtocolCodecFilter(new TextLineCodecFactory()));
		connector.connect(new InetSocketAddress("127.0.0.1", PORT));
		
	}

}
