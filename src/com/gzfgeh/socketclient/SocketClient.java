package com.gzfgeh.socketclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {

	public static void main(String[] args) {
		SocketClient socketClient = new SocketClient();
		socketClient.start();
	}
	
	public void start(){
		BufferedReader inputReader = null;
		BufferedReader socketReader = null;
		BufferedWriter bufferedWriter = null;
		Socket socket = null;
		
		try {
			socket = new Socket("127.0.0.1", 8989);
			inputReader = new BufferedReader(new InputStreamReader(System.in));
			socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			startServerReplyListener(socketReader);
			String inputContent;
			int count = 0;
			while (!(inputContent = inputReader.readLine()).equals("bye")) {
				bufferedWriter.write(inputContent);
				
				if (count % 2 == 0){
					bufferedWriter.write("\n");
				}
				count ++;
				bufferedWriter.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				socket.close();
				inputReader.close();
				bufferedWriter.close();
				socketReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void startServerReplyListener(final BufferedReader reader){
		new Thread(new Runnable() {
			
			public void run() {
				String response;
				try {
					while ((response = reader.readLine()) != null){
						System.out.println(response);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}

}
