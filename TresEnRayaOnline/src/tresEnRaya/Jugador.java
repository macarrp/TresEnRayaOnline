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
		Scanner leer = new Scanner(System.in);
		DataOutputStream dos = null;
		DataInputStream dis = null;
		int fila, columna;
		
		if(this.getFichaJugador().getFicha().equals("X")) {
			try {
				dos = new DataOutputStream(j2.getOutputStream());
				dos.writeBytes("Esperando a que jugador 1 haga su jugada \n\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Introduce la fila y la columna donde quieres poner la pieza " + this.getFichaJugador().getFicha());
			System.out.print("Fila ->: ");
			fila = leer.nextInt();
			while(fila < 1 || fila > 3) {
				System.out.println("Movimiento no valido");
				tablero.mostrar();
				System.out.print("Fila ->: ");
				fila = leer.nextInt();
			}
			
			System.out.print("Columna ^: ");
			columna = leer.nextInt();
			while(columna < 1 || columna > 3) {
				System.out.println("Movimiento no valido");
				tablero.mostrar();
				System.out.print("Columna ^: ");
				columna = leer.nextInt();
			}
			
			if(tablero.getTablero(fila, columna).equals("-")) {
				tablero.setTablero(fila, columna, getFichaJugador());
			}
			else {
				System.out.println("Ya hay una pieza en esa posicion");
				System.out.println();
				tablero.mostrar();
				this.ponerFicha(tablero, j2);
			}
		}
		else {
			try {
				dos = new DataOutputStream(j2.getOutputStream());
				dis = new DataInputStream(j2.getInputStream());
				
				System.out.println("Esperando que jugador 2 haga su jugada");
				
				dos.writeBytes("Introduce la fila y la columna donde quieres poner la pieza " + this.getFichaJugador().getFicha() + "\n\n");
				
				dos.writeBytes("Fila ->: \r\n"); 
				fila = dis.readInt(); 			// SE QUEDA PILLADO EN ESTA LINEA
				while(fila < 1 || fila > 3) {
					dos.writeBytes("Movimiento no valido");
					tablero.mostrarACliente(j2);
					dos.writeBytes("Fila ->: \r\n");
					fila = dis.readInt();
				}
				
				dos.writeBytes("Columna ->: \r\n");
				columna = dis.readInt();
				while(columna < 1 || columna > 3) {
					dos.writeBytes("Movimiento no valido");
					tablero.mostrarACliente(j2);
					dos.writeBytes("columna ->: \r\n");
					columna = dis.readInt();
				}
				
				if(tablero.getTablero(fila, columna).equals("-")) {
					tablero.setTablero(fila, columna, getFichaJugador());
				}
				else {
					dos.writeBytes("Ya hay una pieza en esa posicion \n\n");
					tablero.mostrarACliente(j2);
					this.ponerFicha(tablero, j2);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
