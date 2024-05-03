package caso3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerProtocol {
	public static void process(BufferedReader reader, PrintWriter writer) throws IOException {
		String inputLine;
		String outputLine;
		
		int state = 0;
		
		while(state < 3 && (inputLine = reader.readLine()) != null) {
			System.out.println(Colors.MAGENTA + "[server_p] Input to process: " + inputLine + Colors.RESET);
			
			switch(state) {
			// step 1: hello!
			case 0:
				if(inputLine.equalsIgnoreCase("Hola")) {
					outputLine = "RECEIVED HOLA";
					state++;
				} else {
					outputLine = "I'm waiting for HOLA";
					state = 0;
				}
				break;
			//step 2: ah?
			case 1:
				try {
					int val = Integer.parseInt(inputLine);
					val--;
					outputLine = "" + val;
					state++;
				} catch(Exception e) {
					outputLine = "I'm waiting for an argument";
					state = 0;
				}
				break;
			// step 3: bye!
			case 2:
				if(inputLine.equalsIgnoreCase("EXIT")) {
					outputLine = "Bye!";
					state++;
				} else {
					outputLine = "ERROR! I'm waiting for 'EXIT'";
					state = 0;
				}
				break;
				
			default:
					outputLine = "ERROR";
					state = 0;
			}
			writer.println(outputLine);
		}
		System.out.println(Colors.MAGENTA + "[server_p] Ending protocol..." + Colors.RESET);
	}
}
