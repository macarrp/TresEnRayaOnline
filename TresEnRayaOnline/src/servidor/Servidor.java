package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Servidor {
	public static void main(String[] args) {
		int puerto = 1337;
		
		try(ServerSocket server = new ServerSocket(puerto)){
			System.out.println("Esperando a jugadores...");
			while(true) {
				Socket sc = server.accept();
				ExecutorService pool = Executors.newCachedThreadPool();
				
				pool.submit(new AtenderPeticion(sc));
				

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


