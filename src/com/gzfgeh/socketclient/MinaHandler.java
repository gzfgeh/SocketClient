package com.gzfgeh.socketclient;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MinaHandler extends IoHandlerAdapter {

	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String msg = (String) message;
		//session.write("client reply : " + msg);
		System.out.println("client received msg : " + msg);
	}

	public void messageSent(IoSession session, Object message) throws Exception {
		String msg = (String) message;
		System.out.println("sendMsg : " + msg);
	}

	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("sessionClosed");
	}

	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("sessionCreated");
	}

	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("sessionOpened");
	}

	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("sessionIdle");
	}
	
	
}
