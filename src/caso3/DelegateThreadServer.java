package caso3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DelegateThreadServer extends Thread {
	private Socket clientSocket = null;
	private int pId;

	public DelegateThreadServer(Socket s, int pId) {
		this.clientSocket = s;
		this.pId = pId;
	}

	public void run() {
		System.out.println(Colors.PURPLE + "[delegate_server] Started new thread with id: " + this.pId + Colors.RESET);

		try {
			PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			System.out.println(Colors.PURPLE + "[delegate_server] Starting to process client petitions" + Colors.RESET);
			ServerProtocol.process(reader, writer);
			
			writer.close();
			reader.close();
			clientSocket.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(Colors.PURPLE + "[delegate_server] End thread with id: " + this.pId + Colors.RESET);
	}
}
