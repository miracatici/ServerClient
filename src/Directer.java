import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Directer {
	public static void main (String[] ars) throws Exception {
		final ServerSocket server,server2;
		final Socket sock, sock2;				// Connection socket
		final BufferedReader input1,input2;	// Input for server side, message comes from client side 
		final PrintStream output1,output2;		// Output for server side, sends message to client
		server =  new ServerSocket(8890); 
		server2 = new ServerSocket(8891);
		System.out.println("Servers are created");
		sock = server.accept();
		System.out.println("Client 1 connected");
		sock2 = server2.accept();
		System.out.println("Client 2 connected");
		input1 = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		output1 = new PrintStream(sock.getOutputStream());
		input2 = new BufferedReader(new InputStreamReader(sock2.getInputStream()));
		output2 = new PrintStream(sock2.getOutputStream());
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					String line = "";
					try {
						line = input1.readLine();
						System.out.println(line);
						output2.println(line);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(line.equals("bye")){
						break;
					}
				}
				try {
					output1.close();
					input1.close();
					output2.close();
					input2.close();
					sock.close();
					sock2.close();
					server.close();
					server2.close();					
				} catch (Exception e){
				}	
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run(){
				while(true){
					String line2 = "";
					try {
						line2 = input2.readLine();
						System.out.println(line2);
						output1.println(line2);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if(line2.equals("bye")){
						break;
					}
				}
				try {
					output1.close();
					input1.close();
					output2.close();
					input2.close();
					sock.close();
					sock2.close();
					server.close();
					server2.close();					
				} catch (Exception e){
				}	
			}
		}).start();
	}
}
