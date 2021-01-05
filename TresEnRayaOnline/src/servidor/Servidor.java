package servidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tresEnRaya.Partida;

public class Servidor {
	public static void main(String[] args) {
		int puerto = 1337;
		int numeroPartida = 0;
		
		try(ServerSocket server = new ServerSocket(puerto)){
			System.out.println("Esperando a jugadores...");
			while(true) {
				Socket sc1 = server.accept();
				System.out.println("Jugador 1 conectado con la ip " + sc1.getInetAddress());
				
				DataOutputStream dos1 = new DataOutputStream(sc1.getOutputStream());
				
				System.out.println("Esperando jugador 2...");
				dos1.writeBytes("Esperando a jugador 2 \r\n");
				
				Socket sc2 = server.accept();
				System.out.println("Jugador 2 conectado con la ip " + sc2.getInetAddress());
				
				DataOutputStream dos2 = new DataOutputStream(sc2.getOutputStream());
				
				System.out.println("Empezando partida");
				dos1.writeBytes("Empezando partida \r\n");
				dos2.writeBytes("Empezando partida \r\n");
				
				Partida p = new Partida(sc1, sc2);
				p.iniciarPartida();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
