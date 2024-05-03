package caso3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
	public static final int PUERTO = 3400;
	public static final String SERVIDOR = "localhost";
	
	@Override
	public void run() {
		Socket socket = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		
		System.out.println(Colors.GREEN + "[client] Running client" + Colors.RESET);

		try {
			socket = new Socket(SERVIDOR, PUERTO);
			writer = new PrintWriter(socket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			ClientProtocol.process(stdIn, reader, writer);

			stdIn.close();
			writer.close();
			reader.close();
			socket.close();
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println(Colors.GREEN + "[client] exiting..." + Colors.RESET);
	}
}
