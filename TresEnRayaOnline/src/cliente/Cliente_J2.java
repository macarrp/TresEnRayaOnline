package cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente_J2 extends Thread{
	String host;
	int puerto;
	
	public Cliente_J2(String host, int puerto) {
		this.host = host;
		this.puerto = puerto;
	}
	
	public void run() {
		Socket conexion = null;
		BufferedReader br = null;
		DataOutputStream dos = null;
		
		Scanner leo = new Scanner(System.in);
		
		try {
			conexion = new Socket(host, puerto);
			br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			dos = new DataOutputStream(conexion.getOutputStream());
			
			eligePosicion(br, dos, leo); // Primera ficha
			eligePosicion(br, dos, leo); // Segunda ficha
			eligePosicion(br, dos, leo); // Tercera ficha
			eligePosicion(br, dos, leo); // Cuarta ficha
			eligePosicion(br, dos, leo); // Quinta ficha
			eligePosicion(br, dos, leo); // Sexta lectura por si hay empate
			
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
			String linea;
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
				if(linea.equals("-2")) System.exit(0); 	// El servidor me enviará -2 si el tablero está lleno
														// Por lo que ya paro la aplicación
				System.out.println(linea);	
				
				linea = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
