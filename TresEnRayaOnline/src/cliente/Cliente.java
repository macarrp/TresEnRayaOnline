package cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente{
	public static void main(String [] args) {
		String host = "localhost";
		int puerto = 1337;
		
		Socket conexion = null;
		BufferedReader br = null;
		DataOutputStream dos = null;
		
		Scanner leo = new Scanner(System.in);
		
		try {
			conexion = new Socket(host, puerto); 
			br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			dos = new DataOutputStream(conexion.getOutputStream());
			
			System.out.println("Conectado al servidor " + conexion.getInetAddress()); // Para verificar que se ha hecho la conexion
			
			eligePosicion(br, dos, leo); // Primera ficha
			eligePosicion(br, dos, leo); // Segunda ficha
			eligePosicion(br, dos, leo); // Tercera ficha
			eligePosicion(br, dos, leo); // Cuarta ficha
			eligePosicion(br, dos, leo); // Quinta ficha
			eligePosicion(br, dos, leo); // Sexta lectura por si hay empate
			
			System.out.println("Partida terminada");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				dos.close();
				br.close();
				conexion.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void eligePosicion(BufferedReader br, DataOutputStream dos, Scanner leo) {
			int fila, columna;
			try {
				leeMensajesServidor(br);
				
				fila = leo.nextInt();	// Leo fila
				dos.writeInt(fila); 
//				if(fila < 1 || fila > 3) eligePosicion(br, dos, leo); 
								
				System.out.println(br.readLine()); // Digo que escriba columna
				
				columna = leo.nextInt();	// Leo columna
				dos.writeInt(columna);
//				if(columna < 1 || columna > 3) eligePosicion(br, dos, leo);
							
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static void leeMensajesServidor(BufferedReader br) {
		String linea;
		try {
			linea = br.readLine();
			while(!linea.equals("-1")) { // Condicion de parada para leer la posicion
				if(linea.equals("-2")) System.exit(0); 	// El servidor me enviar� -2 si el tablero est� lleno � si no hay mas datos que enviar 
														// Por lo que ya paro la aplicaci�n
				System.out.println(linea);	
				
				linea = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
