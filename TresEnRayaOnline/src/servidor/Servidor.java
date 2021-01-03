package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tresEnRaya.Partida;

public class Servidor extends Thread{
	int puerto;
	
	public Servidor(int puerto) {
		this.puerto = puerto;
	}
	
	public void run() {
		try(ServerSocket server = new ServerSocket(puerto)){
			while(true) {
				System.out.println("Esperando a nuevo jugador 2...");
				try {
					Socket sc = server.accept();
					System.out.println("Jugador conectado con la ip " + sc.getInetAddress());
					System.out.println("Empezando partida");
					
					Partida p = new Partida(sc);
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
