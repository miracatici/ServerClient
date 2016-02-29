package com.miracatici;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSide {
	public static void main (String[] ars){
		Socket client = null;				// Connection socket
		BufferedReader input = null;	// Input for client side, message comes from server side 
		BufferedReader userInput = null;		// User keyboard input, terminal or system prompt
		PrintStream output = null;		// Output for client side, sends message to server
		String message, line = "";
		try {
			client = new Socket("178.62.193.160",8890);
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintStream(client.getOutputStream());
			userInput = new BufferedReader(new InputStreamReader(System.in));
			
			while (true){
				System.out.print("Client: ");
				message = userInput.readLine();
				output.println(message);
				line = input.readLine();
				System.out.println("Server: " + line + "\n");
				if(line.equals("bye")){
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
