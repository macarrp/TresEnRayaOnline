package tresEnRaya;

import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Partida {
	private Socket cliente;
	
	public Partida(Socket cliente) {
		this.cliente = cliente;
	}
	
	public void iniciarPartida() {
		Random r = new Random();
		int turno = r.nextInt(2); // Me devuelve un numero entre 0 y 1
		
		Ficha fichaX = new Ficha("X"), fichaO = new Ficha("O");
		
		Jugador j1 = new Jugador(fichaX); // Turno 0
		Jugador j2 = new Jugador(fichaO); // Turno 1
		
		Tablero tablero = new Tablero(j1, j2);
		
		tablero.mostrar();
		tablero.mostrarACliente(cliente);
		
		boolean hayGanador = false;
		while(!hayGanador) {
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
		}
		
		// Si salgo del bucle es porque hay un ganador
		if(tablero.hayGanador(j1)) System.out.println("Ha ganado el jugador 1");
		else System.out.println("Ha ganado el jugador 2");
		
		System.out.println("¿Otra partida? (Y/N)");
		reiniciarPartida(new Scanner(System.in));
	}
	
	public void reiniciarPartida(Scanner yn) {
		String respuesta = yn.nextLine();
		if(respuesta.equalsIgnoreCase("y")) {
			iniciarPartida();
		}
		else if(respuesta.equalsIgnoreCase("n")) {
			System.out.println("Partida terminada");
		}
		else {
			System.out.println("Caracter no valido");
			System.out.println("¿Otra partida? (Y/N)");
			reiniciarPartida(new Scanner(System.in));
		}
	}
	
}
