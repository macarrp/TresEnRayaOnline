package tresEnRaya;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Principal {
	public static void main(String[] args) {
		try(ServerSocket server = new ServerSocket(5050)){
			while(true) {
				System.out.println("Esperando a jugador 2...");
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
