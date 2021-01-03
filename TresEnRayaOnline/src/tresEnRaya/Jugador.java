package tresEnRaya;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
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
	
	
	// Pone una ficha en el tablero, también controla que los numeros introducidos sean validos
	public void ponerFicha(Tablero tablero, Socket j2) {
		Scanner leer = new Scanner(System.in); // Leo por teclado en caso de que juegue el servidor
		DataOutputStream dos = null; // Dos canales para recibir datos del cliente 
		DataInputStream dis = null;  
		int fila, columna;			 // Las posiciones donde se colocaran las fichas
		
		if(this.getFichaJugador().getFicha().equals("X")) { // JUGADOR 1
			try {
				dos = new DataOutputStream(j2.getOutputStream());
				dos.writeBytes("Esperando a que jugador 1 haga su jugada \n\n"); // Mientras jugador 1 hace su jugada, aviso a jugador 2
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("Introduce la fila y la columna donde quieres poner la pieza " + this.getFichaJugador().getFicha());
			System.out.print("Fila ->: ");
			fila = leer.nextInt();
			while(fila < 1 || fila > 3) {
				System.out.println("Fila no valido");
				tablero.mostrar();
				System.out.print("Fila ->: ");
				fila = leer.nextInt();
			}
			
			System.out.print("Columna ^: ");
			columna = leer.nextInt();
			while(columna < 1 || columna > 3) {
				System.out.println("Columna no valido");
				tablero.mostrar();
				System.out.print("Columna ^: ");
				columna = leer.nextInt();
			}
			
			if(tablero.getTablero(fila, columna).equals("-")) {		// Si no hay una ficha en esa posicion, la pone
				tablero.setTablero(fila, columna, getFichaJugador()); // Evito que se pongan fichas superpuestas
			}
			else {
				System.out.println("¡Ya hay una pieza en esa posicion! \n"); // Si se intenta, me avisa
				tablero.mostrar();
				this.ponerFicha(tablero, j2); // Vuelvo a preguntar por una posicion valida
				
			}
		}
		else {
			try {
				dos = new DataOutputStream(j2.getOutputStream());
				dis = new DataInputStream(j2.getInputStream());
				
				System.out.println("Esperando que jugador 2 haga su jugada"); // Mientras jugador 1 hace su jugada, aviso a jugador 2
				
				
				dos.writeBytes("Introduce la fila y la columna donde quieres poner la pieza " + this.getFichaJugador().getFicha() + "\n");
				
				dos.writeBytes("Fila ->:\r");
				dos.writeBytes("-1\r\n"); // Marca de fin de bucle
				fila = dis.readInt(); 			
				
//				if(fila < 1 || fila > 3) { // PARTE CLIENTE?
//					dos.writeBytes("Fila no valido\n");
//					tablero.mostrarACliente(j2);
//																				// Resolver numero fuera de rango con interfaz
//					ponerFicha(tablero, j2);
//				}
				
				dos.writeBytes("Columna ->: \r\n");
				
				columna = dis.readInt();
				
//				if(columna < 1 || columna > 3) { // PARTE CLIENTE?
//					dos.writeBytes("Columna no valido\n");
//					tablero.mostrarACliente(j2);
//					
//					this.ponerFicha(tablero, j2);
//				}
				
				if(tablero.getTablero(fila, columna).equals("-")) { // Si no hay una ficha en esa posicion, la pone
					tablero.setTablero(fila, columna, getFichaJugador()); // Evito que se pongan fichas superpuestas
				}
				else {
					dos.writeBytes("¡Ya hay una pieza en esa posicion! \n");
					tablero.mostrarACliente(j2);
					this.ponerFicha(tablero, j2); // Vuelvo a preguntar por una posicion valida
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
