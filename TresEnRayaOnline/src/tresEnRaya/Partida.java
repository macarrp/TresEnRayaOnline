package tresEnRaya;

import java.util.Random;
import java.util.Scanner;

public class Partida {
	public static void iniciarPartida() {
		Random r = new Random();
		int turno = r.nextInt(1); // Me devuelve un numero entre 0 y 1
		
		Ficha fichaX = new Ficha("X"), fichaO = new Ficha("O");
		
		Jugador j1 = new Jugador(fichaX); // Turno 0
		Jugador j2 = new Jugador(fichaO); // Turno 1
		
		Tablero tablero = new Tablero(j1, j2);
		
		tablero.mostrar();
		
		boolean hayGanador = false;
		while(!hayGanador) {
			if(turno == 0) {
				j1.ponerFicha(tablero);
				turno = 1;
				hayGanador = tablero.hayGanador(j1);
			}
			else {
				j2.ponerFicha(tablero);
				turno = 0;
				hayGanador = tablero.hayGanador(j2);
			}
			tablero.mostrar();
		}
		
		// Si salgo del bucle es porque hay un ganador
		if(tablero.hayGanador(j1)) System.out.println("Ha ganado el jugador 1");
		else System.out.println("Ha ganado el jugador 2");
		
//		Scanner leer = new Scanner(System.in);
//		System.out.println("¿Otra partida? (Y/N)");
//		if(leer.nextLine().equalsIgnoreCase("y")) {
//			tablero.limpiar();
//		}
	}
	
	
	public static void main(String[] args) {
		iniciarPartida();
	}
}
