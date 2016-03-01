import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiplyS {
	public static void main (String[] ars){
		ServerSocket server = null;
		Socket sock = null;				// Connection socket
		BufferedReader input = null;	// Input for server side, message comes from client side 
		BufferedReader serverInput = null;		// User keyboard input, terminal or system prompt
		PrintStream output = null;		// Output for server side, sends message to client
		String message, line = "";
		try {
			server =  new ServerSocket(8890); 
			sock = server.accept();
			input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			output = new PrintStream(sock.getOutputStream());
			serverInput = new BufferedReader(new InputStreamReader(System.in));
			while (true){
				line = input.readLine();
				if (line.equals("bye")){
					System.out.println("Chat is ended");
					break;
				}
				System.out.print("Client: " + line + "\n");
				float f = Float.valueOf(line);
				
				message = String.valueOf(f*5);
				System.out.print("Server: " + message);
				output.println(message);
			}
			output.close();
			input.close();
			serverInput.close();
			sock.close();
			server.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
