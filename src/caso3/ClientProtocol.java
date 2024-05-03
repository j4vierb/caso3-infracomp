package caso3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClientProtocol {
	public static void process(BufferedReader stdIn, BufferedReader reader, PrintWriter writer) throws IOException {
		String fromServer = "";
		String fromUser = "";
		boolean executing = true;
		
		while(executing) {
			System.out.println(Colors.MAGENTA + "[client_p] Write the message> " + Colors.RESET);
			fromUser = stdIn.readLine();
			
			// send it throw the network
			if(fromUser != null) {
				System.out.println(Colors.MAGENTA + "[client_p] Message to be send: " + fromUser + Colors.RESET);
				
				if(fromUser.equals("EXIT"))
					executing = false;
				
				writer.println(fromUser);
			}
			
			// server answer
			if((fromServer = reader.readLine()) != null)
				System.out.println(Colors.MAGENTA + "[client_p] Server answer: " + fromServer + Colors.RESET);
		}
		System.out.println(Colors.MAGENTA + "[client_p] Ending protocol..." + Colors.RESET);
	}
}
