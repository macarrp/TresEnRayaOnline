package tresEnRaya;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import interfaz.InterfazGrafica;

public class Partida {
	private Socket j1, j2;
	
	public Partida(Socket j1, Socket j2) {
		this.j1 = j1;
		this.j2 = j2;
	}
	
	// Inicia la partida del 3 en raya como tal
	public void iniciarPartida() {
		Random r = new Random(); // r.nextInt(1) Me devuelve un numero entre 0 y 1
		int turno = r.nextInt(1); // Empieza un jugador aleatorio
		
		Ficha fichaX = new Ficha("X"), fichaO = new Ficha("O");
		
		Jugador jug1 = new Jugador(fichaX); // Turno 0
		Jugador jug2 = new Jugador(fichaO); // Turno 1
		
		try {
			DataOutputStream dos1 = new DataOutputStream(j1.getOutputStream()); // Canal de comunicacion con el j1
			DataOutputStream dos2 = new DataOutputStream(j2.getOutputStream()); // Canal de comunicacion con el j2
																					
			Tablero tablero = new Tablero(jug1, jug2);
			
			if(turno == 0) {
				System.out.println("\nTURNO JUGADOR 1"); // Solo para tener constancia en el servidor
				dos1.writeBytes("TURNO JUGADOR 1\n");
				dos2.writeBytes("TURNO JUGADOR 1\n");
				dos1.flush();
				dos2.flush();
			}
			else {
				System.out.println("\nTURNO JUGADOR 2"); // Solo para tener constancia en el servidor
				dos1.writeBytes("TURNO JUGADOR 2\n");
				dos2.writeBytes("TURNO JUGADOR 2\n");
				dos1.flush();
				dos2.flush();
			}
			
			tablero.mostrarACliente(j1);
			tablero.mostrarACliente(j2);
			
			boolean hayGanador = false, tableroLleno = false;
			while(!hayGanador && !tableroLleno) {
				if(turno == 0) {
					jug1.ponerFicha(tablero, j1, j2);
					turno = 1;
					hayGanador = tablero.hayGanador(jug1);
				}
				else {
					jug2.ponerFicha(tablero, j1, j2);
					turno = 0;
					hayGanador = tablero.hayGanador(jug2);
				}
				if(turno == 0) {
					System.out.println("\nTURNO JUGADOR 1"); // Solo para tener constancia en el servidor
					dos1.writeBytes("TURNO JUGADOR 1\n");
					dos2.writeBytes("TURNO JUGADOR 1\n");
					dos1.flush();
					dos2.flush();
				}
				else {
					System.out.println("\nTURNO JUGADOR 2"); // Solo para tener constancia en el servidor
					dos1.writeBytes("TURNO JUGADOR 2\n");
					dos2.writeBytes("TURNO JUGADOR 2\n");
					dos1.flush();
					dos2.flush();
				}
				
				tablero.mostrar(); // El servidor puede ver el estado de la partida
				tablero.mostrarACliente(j1);
				tablero.mostrarACliente(j2);
				tableroLleno = tablero.tableroLleno();
			}
			
			// Si salgo del bucle es porque hay un ganador o porque el tablero está lleno
			String ganador;
			if(tablero.hayGanador(jug1)) {
				ganador = "Ha ganado el jugador 1 [" + jug1.getFichaJugador().toString() + "]";
				System.out.println(ganador);		// INFO server
				dos1.writeBytes(ganador + "\r\n");
				dos2.writeBytes(ganador + "\r\n");
				dos1.flush();
				dos2.flush();
			}
			else if(tablero.hayGanador(jug2)){
				ganador = "Ha ganado el jugador 2 [" + jug2.getFichaJugador().toString() + "]";
				System.out.println(ganador);		// INFO server
				dos1.writeBytes(ganador + "\r\n");
				dos2.writeBytes(ganador + "\r\n");
				dos1.flush();
				dos2.flush();
			}
			
			else {
				ganador = "EMPATE";
				System.out.println(ganador);		// INFO server
				dos1.writeBytes(ganador + "\r\n");
				dos2.writeBytes(ganador + "\r\n");
				dos1.flush();
				dos2.flush();
			}
			dos1.writeBytes("-2\r\n"); // Para terminar la partida(El cliente)
			System.out.println("Jugador 1 desconectado");
			
			dos2.writeBytes("-2\r\n");
			System.out.println("Jugador 2 desconectado");
			dos1.flush();
			dos2.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		System.out.println("¿Otra partida? (Y/N)");
//		reiniciarPartida(new Scanner(System.in));
	}
	
//	public void reiniciarPartida(Scanner yn) {
//		String respuesta = yn.nextLine();
//		if(respuesta.equalsIgnoreCase("y")) {
//			iniciarPartida();
//		}
//		else if(respuesta.equalsIgnoreCase("n")) {
//			System.out.println("Partida terminada");
//		}
//		else {
//			System.out.println("Caracter no valido");
//			System.out.println("¿Otra partida? (Y/N)");
//			reiniciarPartida(new Scanner(System.in));
//		}
//	}
	
}
