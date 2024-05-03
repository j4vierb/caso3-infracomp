package caso3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread {
	public static final int PUERTO = 3400;
	
	@Override
	public void run() {
		ServerSocket server = null;
		boolean executing = true;
		AtomicInteger idGenerator = new AtomicInteger();
		
		System.out.println(Colors.RED + "[server] Running server" + Colors.RESET);
		
		try {
			server = new ServerSocket(PUERTO);
		} catch(IOException e) {
			System.err.println(Colors.RED + "[server] No se pudo crear el socket en el puerto " + PUERTO + Colors.RESET);
			System.exit(-1);
		}

		while(executing) {
			try {
				Socket socket = server.accept();
				int id = idGenerator.addAndGet(1);
				
				System.out.println(Colors.RED + "[server] Created a new process with id: " + id + Colors.RESET);
				
				DelegateThreadServer thread = new DelegateThreadServer(socket, id);
				
				thread.start();
			} catch(IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		System.out.println(Colors.RED + "[server] exiting... " + Colors.RESET);
	}
}
