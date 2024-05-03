package caso3;

public class Main {
	public static void main(String []args) {
		System.out.println("[+] Starting point");
		
		Server server = new Server();
		Client c1 = new Client();
		
		c1.start();
		server.start();
	}
}
