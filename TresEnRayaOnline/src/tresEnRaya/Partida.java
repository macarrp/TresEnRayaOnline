package tresEnRaya;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class Partida {
	private Socket cliente;
	
	public Partida(Socket cliente) {
		this.cliente = cliente;
	}
	
	public void iniciarPartida() {
		Random r = new Random(); // r.nextInt(0) Me devuelve un numero entre 0 y 1
		int turno = 1; // Para que el jugador 2 empieze primero
		
		Ficha fichaX = new Ficha("X"), fichaO = new Ficha("O");
		
		Jugador j1 = new Jugador(fichaX); // Turno 0
		Jugador j2 = new Jugador(fichaO); // Turno 1
		
		try {
			DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
			
			Tablero tablero = new Tablero(j1, j2);
			
			tablero.mostrar();
			tablero.mostrarACliente(cliente);
			
			boolean hayGanador = false, tableroLleno = false;
			while(!hayGanador && !tableroLleno) {
				if(turno == 0) {
					j1.ponerFicha(tablero, cliente);
					turno = 1;
					hayGanador = tablero.hayGanador(j1);
				}
				else {
					j2.ponerFicha(tablero, cliente);
					turno = 0;
					hayGanador = tablero.hayGanador(j2);
				}
				tablero.mostrar();
				tablero.mostrarACliente(cliente);
				tableroLleno = tablero.tableroLleno();
			}
			
			// Si salgo del bucle es porque hay un ganador o porque el tablero está lleno
			String ganador;
			if(tablero.hayGanador(j1)) {
				ganador = "Ha ganado el jugador 1";
				System.out.println(ganador);
				dos.writeBytes(ganador + "\r\n");
			}
			else {
				ganador = "Ha ganado el jugador 2";
				System.out.println(ganador);
				dos.writeBytes(ganador + "\r\n");
			}
			
			if(tableroLleno) {
				ganador = "EMPATE";
				System.out.println(ganador);
				dos.writeBytes(ganador + "\r\n");
			}
			dos.writeBytes("-2\r\n");
			
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
