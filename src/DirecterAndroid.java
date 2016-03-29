import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DirecterAndroid {
	public static void main (String[] ars) throws Exception {
		final ServerSocket serverAndroid,serverClient;
		final Socket sockAndroid, sockClient;				// Connection socket
		final BufferedReader inputFromClient;	// Input for server side, message comes from client side 
		final PrintStream outputToAndroid;		// Output for server side, sends message to client
		serverAndroid =  new ServerSocket(8890); 
		serverClient = new ServerSocket(8891);
		System.out.println("Servers are created");
		sockAndroid = serverAndroid.accept();
		System.out.println("Android connected");
		sockClient = serverClient.accept();
		System.out.println("Client 2 connected");
		inputFromClient = new BufferedReader(new InputStreamReader(sockClient.getInputStream()));
		outputToAndroid = new PrintStream(sockAndroid.getOutputStream());
		new Thread(new Runnable() {
			@Override
			public void run(){
				try {
					while (true){
						String line = "" ;
						line = inputFromClient.readLine();
						System.out.println(line);
						outputToAndroid.println(line);
						if(line.equals("bye")){
							break;
						}	
					}
					inputFromClient.close();
					outputToAndroid.close();
					serverAndroid.close();
					serverClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}