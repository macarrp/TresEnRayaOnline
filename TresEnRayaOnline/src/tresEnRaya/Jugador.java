package tresEnRaya;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
	public void ponerFicha(Tablero tablero, Socket sc1, Socket sc2) {
		DataOutputStream dos1 = null; // Canal para enviar datos al cliente 1
		DataOutputStream dos2 = null; // Canal para enviar datos al cliente 2
		DataInputStream dis = null;
		int fila, columna; // Las posiciones donde se colocaran las fichas

		if (this.getFichaJugador().getFicha().equals("X")) { 	// JUGADOR 1
			try {
				dis = new DataInputStream(sc1.getInputStream());
				dos1 = new DataOutputStream(sc1.getOutputStream());
				dos2 = new DataOutputStream(sc2.getOutputStream());
				
				dos2.writeBytes("Esperando a que jugador 1 haga su jugada \n\n"); 	// Mientras jugador 1 hace su jugada,
				dos2.flush();														// aviso a jugador 2

				System.out.println("Esperando a que jugador 1 haga su jugada"); // Info server

				dos1.writeBytes("Introduce la fila y la columna donde quieres poner la pieza " + this.getFichaJugador().getFicha() + "\n");

				dos1.writeBytes("Fila ->:\r");
				dos1.writeBytes("-1\r\n"); // Marca de fin de bucle
				dos1.flush();
				
				
				System.out.println("Jugador 1 introduciendo fila");
				
				fila = dis.readInt();

				System.out.println("Jugador 1 introduciendo columna");
				
				dos1.writeBytes("Columna ->:\r");
				dos1.flush();
				columna = dis.readInt();

				if (tablero.getTablero(fila, columna).equals("-")) { // Si no hay una ficha en esa posicion, la pone. Evito que se pongan fichas superpuestas
					tablero.setTablero(fila, columna, getFichaJugador()); // Actualizo el tablero
				} else {
					dos1.writeBytes("�Ya hay una pieza en esa posicion! \n"); // Si se intenta, le avisa
					dos1.flush();
					tablero.mostrarACliente(sc1);
					System.out.println("Movimiento no valido de j1"); // Info server
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

				System.out.println("Esperando que jugador 2 haga su jugada"); 	// Mientras jugador 1 hace su jugada,
				dos1.flush();													// aviso a jugador 2
				

				dos2.writeBytes("Introduce la fila y la columna donde quieres poner la pieza "
						+ this.getFichaJugador().getFicha() + "\n");

				dos2.writeBytes("Fila ->:\r");
				dos2.writeBytes("-1\r\n"); // Marca de fin de bucle
				dos2.flush();
				
				System.out.println("Jugador 2 introduciendo fila");
				
				fila = dis.readInt();

				dos2.writeBytes("Columna ->: \r\n");
				dos2.flush();
				
				System.out.println("Jugador 2 introduciendo columna");
				
				columna = dis.readInt();			

				if (tablero.getTablero(fila, columna).equals("-")) { // Si no hay una ficha en esa posicion, la pone. Evito que se pongan fichas superpuestas
					tablero.setTablero(fila, columna, getFichaJugador()); // Actualizo tablero
				} else {
					dos2.writeBytes("�Ya hay una pieza en esa posicion! \n");
					dos2.flush();
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
