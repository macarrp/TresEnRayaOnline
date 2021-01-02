package tresEnRaya;

import java.util.Scanner;

public class Jugador {
	private Ficha ficha;
	
	// Inicializa un jugador dado una ficha
	public Jugador(Ficha ficha) {
		this.ficha = ficha;
	}
	
	// Devuelve la ficha con la que juega el jugador
	public Ficha getFichaJugador() {
		return this.ficha;
	}
	
	// Devuelve una cadena conteniendo la ficha del jugador
	public String toString() {
		return this.ficha.toString();
	}
	
	
	// Pone una ficha en el tablero
	public void ponerFicha(Tablero tablero) {
		Scanner leer = new Scanner(System.in);
		
		System.out.println("Introduce la fila y la columna donde quieres poner la pieza " + this.getFichaJugador().getFicha());
		System.out.print("Fila ->: ");
		int fila = leer.nextInt();
		while(fila < 1 || fila > 3) {
			System.out.println("Movimiento no valido");
			tablero.mostrar();
			System.out.print("Fila ->: ");
			fila = leer.nextInt();
		}
		
		
		System.out.print("Columna ^: ");
		int columna = leer.nextInt();
		while(columna < 1 || columna > 3) {
			System.out.println("Movimiento no valido");
			tablero.mostrar();
			System.out.print("Columna ^: ");
			columna = leer.nextInt();
		}
		//boolean b = tablero.getTablero(fila, columna).equals("-");
		if(tablero.getTablero(fila, columna).equals("-")) {
			tablero.setTablero(fila, columna, getFichaJugador());
		}
		else {
			System.out.println("Ya hay una pieza en esa posicion");
			System.out.println();
			tablero.mostrar();
			this.ponerFicha(tablero);
		}
		
	}
}
