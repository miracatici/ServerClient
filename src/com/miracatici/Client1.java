package com.miracatici;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client1 {
	public static void main (String[] ars){
		Socket client = null;				// Connection socket
		final BufferedReader input;	// Input for client side, message comes from server side 
		BufferedReader userInput = null;		// User keyboard input, terminal or system prompt
		final PrintStream output;		// Output for client side, sends message to server
		String message = "";
		try {
			client = new Socket("178.62.193.160",8890);
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintStream(client.getOutputStream());
			userInput = new BufferedReader(new InputStreamReader(System.in));
			new Thread (new Runnable(){
				@Override
				public void run() {
					while(true){
						try {
							String line = input.readLine();
							System.out.println("Directed: " + line);
							if(line.equals("bye")){
								break;
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			while (true){
				System.out.print("Client1: ");
				message = userInput.readLine();
				output.println("Client1: " + message);
				if(message.equals("bye")){
					break;
				}
			}
			client.close();
			input.close();
			userInput.close();
			output.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
