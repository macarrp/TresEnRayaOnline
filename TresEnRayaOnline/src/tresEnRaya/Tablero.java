package tresEnRaya;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Tablero {
	private String[][] tablero;
	private Jugador j1, j2;

	// Inicializa el tablero pasados como parámetros dos jugadores
	public Tablero(Jugador j1, Jugador j2) {
		this.tablero = new String[3][3];
		limpiar();
		this.j1 = j1;
		this.j2 = j2;
	}
	
	public Jugador getJ1() {
		return j1;
	}
	
	public Jugador getJ2() {
		return j2;
	}
	
	// Pone una ficha en el tablero una vez pasados su fila y su columna
	// El rango de la fila y de la columna debe estar entre 1 y 3
	public void setTablero(int fila, int columna, Ficha ficha) {
			tablero[fila-1][columna-1] = ficha.getFicha();	
	}
	
	// Devuelve una cadena pasados su fila y su columna
	// El rango de la fila y de la columna debe estar entre 1 y 3
	public String getTablero(int fila, int columna){
		return tablero[fila-1][columna-1];
	}
	
	// Muestra el estado actual del tablero
	public void mostrar() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print('[' + tablero[i][j] + ']');
			}
			System.out.println();
		}
		System.out.println();
	}
	
	// Muestra al cliente el estado actual del tablero
	public void mostrarACliente(Socket conexion) {
		DataOutputStream dos = null;
		String enviado = "";
		try {
			dos = new DataOutputStream(conexion.getOutputStream());
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					dos.writeBytes('[' + tablero[i][j] + ']');
					enviado =  enviado + ('[' + tablero[i][j] + ']');
				}
				dos.writeBytes("\n");
				dos.flush();
				enviado = "";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Limpia el tablero de modo que no haya fichas en el tablero
	public void limpiar() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tablero[i][j] = "-";
			}
		}
	}
	
	// Devuelve cierto si el tablero esta lleno y falso en caso contrario
	public boolean tableroLleno() {
		return numFichasEnTablero() == 9;
	}
	
	public int numFichasEnTablero() {
		int lleno = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(!tablero[i][j].equals("-")) lleno++;
			}
		}
		return lleno;
	}

	// Devuelve cierto si el jugador ha ganado y devuelve falso en caso contrario
	public boolean hayGanador(Jugador jugador) {
		return ganaPorFilas(jugador) || ganaPorColumnas(jugador) || ganaPorDiagonal(jugador);
	}

	// Devuelve cierto si el jugador ha ganado por filas y falso en caso contrario
	public boolean ganaPorFilas(Jugador jugador) {
		String fila = "", fila2 = "", fila3 = "";
		String ficha = jugador.getFichaJugador().getFicha();
		String filaFichas = ficha + ficha + ficha;
		
		// Primera fila
		for (int i = 0; i < 3; i++) {
			fila = fila + tablero[0][i];
		}
		if(fila.equals(filaFichas)) return true;
			

		// Segunda fila
		for (int i = 0; i < 3; i++) {
			fila2 = fila2 + tablero[1][i];
		}
		if(fila2.equals(filaFichas)) return true;
			

		// Tercera fila
		for (int i = 0; i < 3; i++) {
			fila3 = fila3 + tablero[2][i];
		}
		if(fila3.equals(filaFichas)) return true;
		
		// Si ninguna fila coincide entonces es falso
		return false;
	}

	// Devuelve cierto si el jugador ha ganado por columnas y falso en caso contrario
	public boolean ganaPorColumnas(Jugador jugador) {
		String columna = "", columna2 = "", columna3 = "";
		String ficha = jugador.getFichaJugador().getFicha();
		String columnaFichas = ficha + ficha + ficha;
		
		// Primera columna
		for (int i = 0; i < 3; i++) {
			columna = columna + tablero[i][0];
		}
		if(columna.equals(columnaFichas)) return true;
			

		// Segunda columna
		for (int i = 0; i < 3; i++) {
			columna2 = columna2 + tablero[i][1];
		}
		if(columna2.equals(columnaFichas)) return true;
			

		// Tercera columna
		for (int i = 0; i < 3; i++) {
			columna3 = columna3 + tablero[i][2];
		}
		if(columna3.equals(columnaFichas)) return true;
			
		// Si ninguna columna coincide entonces es falso
		return false;
	}

	// Devuelve cierto si el jugador ha ganado por las diagonales y falso en caso contrario
	public boolean ganaPorDiagonal(Jugador jugador) {
		String ficha = jugador.getFichaJugador().getFicha();
		String fichas = ficha + ficha + ficha;
		String diagonal1 = tablero[0][0] + tablero[1][1] + tablero[2][2];
		String diagonal2 = tablero[0][2] + tablero[1][1] + tablero[2][0];
		
		if(diagonal1.equals(fichas)) return true;
		if(diagonal2.equals(fichas)) return true;
		
		return false;
	}
}
