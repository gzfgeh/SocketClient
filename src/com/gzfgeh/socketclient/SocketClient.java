package com.gzfgeh.socketclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SocketClient {

	public static void main(String[] args) {
		SocketClient socketClient = new SocketClient();
		socketClient.start();
	}
	
	public void start(){
		BufferedReader inputReader;
		try {
			inputReader = new BufferedReader(new InputStreamReader(System.in));
			String inputContent;
			while (!(inputContent = inputReader.readLine()).equals("bye")) {
				System.out.println(inputContent);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
