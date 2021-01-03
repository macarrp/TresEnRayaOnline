package tresEnRaya;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(1337)){
			while(true) {
				System.out.println("Esperando a nuevo jugador 2...");
				try {
					Socket j2 = server.accept();
					System.out.println("Jugador conectado con la ip " + j2.getInetAddress());
					System.out.println("Empezando partida");
					
					Partida p = new Partida(j2);
					p.iniciarPartida();
				} catch(IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
