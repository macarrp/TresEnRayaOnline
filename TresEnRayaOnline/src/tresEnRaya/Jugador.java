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

	// Pone una ficha en el tablero, también controla que los numeros introducidos
	// sean validos
	public void ponerFicha(Tablero tablero, Socket sc1, Socket sc2) {
		DataOutputStream dos1 = null; // Dos canales para enviar datos al cliente 2
		DataOutputStream dos2 = null; // Dos canales para enviar datos al cliente 1
		DataInputStream dis = null;
		int fila, columna; // Las posiciones donde se colocaran las fichas

		if (this.getFichaJugador().getFicha().equals("X")) { // JUGADOR 1
			try {
				dis = new DataInputStream(sc1.getInputStream());
				dos1 = new DataOutputStream(sc1.getOutputStream());
				dos2 = new DataOutputStream(sc2.getOutputStream());
				
				dos2.writeBytes("Esperando a que jugador 1 haga su jugada \n\n"); // Mientras jugador 1 hace su jugada,
																					// aviso a jugador 2

				System.out.println("Esperando a que jugador 1 haga su jugada"); // Info server

				dos1.writeBytes("Introduce la fila y la columna donde quieres poner la pieza " + this.getFichaJugador().getFicha() + "\n");

				dos1.writeBytes("Fila ->:\r");
				dos1.writeBytes("-1\r\n"); // Marca de fin de bucle
				
				System.out.println("Jugador 1 introduciendo fila");
				
				fila = dis.readInt();
//				while (fila < 1 || fila > 3) {
//					System.out.println("Fila no valido");
//					tablero.mostrar();
//					System.out.print("Fila ->: ");
//					fila = leer.nextInt();
//				}
				System.out.println("Jugador 1 introduciendo columna");
				
				dos1.writeBytes("Columna ->:\r");
				dos1.flush();
				columna = dis.readInt();
//				while (columna < 1 || columna > 3) {
//					System.out.println("Columna no valido");
//					tablero.mostrar();
//					System.out.print("Columna ^: ");
//					columna = leer.nextInt();
//				}

				if (tablero.getTablero(fila, columna).equals("-")) { // Si no hay una ficha en esa posicion, la pone. Evito que se pongan fichas superpuestas
					tablero.setTablero(fila, columna, getFichaJugador()); // Actualizo el tablero
				} else {
					dos1.writeBytes("¡Ya hay una pieza en esa posicion! \n"); // Si se intenta, le avisa
					tablero.mostrarACliente(sc1);
					System.out.println("Movimiento no valido de j1");
					this.ponerFicha(tablero, sc1, sc2); // Vuelvo a preguntar por una posicion valida
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			try {
				dos1 = new DataOutputStream(sc1.getOutputStream());
				dos2 = new DataOutputStream(sc2.getOutputStream());
				dis = new DataInputStream(sc2.getInputStream());
				
				dos1.writeBytes("Esperando a que jugador 2 haga su jugada \n\n");

				System.out.println("Esperando que jugador 2 haga su jugada"); // Mientras jugador 1 hace su jugada,
																				// aviso a jugador 2
				

				dos2.writeBytes("Introduce la fila y la columna donde quieres poner la pieza "
						+ this.getFichaJugador().getFicha() + "\n");

				dos2.writeBytes("Fila ->:\r");
				dos2.writeBytes("-1\r\n"); // Marca de fin de bucle
				
				System.out.println("Jugador 2 introduciendo fila");
				
				fila = dis.readInt();

//				if(fila < 1 || fila > 3) { // PARTE CLIENTE?
//					dos.writeBytes("Fila no valido\n");
//					tablero.mostrarACliente(sc);
//																				// Resolver numero fuera de rango con interfaz
//					ponerFicha(tablero, sc);
//				}

				dos2.writeBytes("Columna ->: \r\n");

				System.out.println("Jugador 2 introduciendo columna");
				
				columna = dis.readInt();

//				if(columna < 1 || columna > 3) { // PARTE CLIENTE?
//					dos.writeBytes("Columna no valido\n");
//					tablero.mostrarACliente(sc);
//					
//					this.ponerFicha(tablero, sc);
//				}

				if (tablero.getTablero(fila, columna).equals("-")) { // Si no hay una ficha en esa posicion, la pone. Evito que se pongan fichas superpuestas
					tablero.setTablero(fila, columna, getFichaJugador()); // Actualizo tablero
				} else {
					dos2.writeBytes("¡Ya hay una pieza en esa posicion! \n");
					tablero.mostrarACliente(sc2);
					System.out.println("Movimiento no valido de j2");	// INFO server
					this.ponerFicha(tablero, sc1, sc2); // Vuelvo a preguntar por una posicion valida
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
