package Cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Jugador2_Cliente {
	public static void main(String[] args) {
		Socket conexion = null;
		BufferedReader br = null;
		DataOutputStream dos = null;
		
		int scanLeido, leido;
		Scanner leo = new Scanner(System.in);
		
		try {
			conexion = new Socket("localhost", 5050);
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
				linea = br.readLine();
				
				while(!linea.equals("-1")) { // Condicion de parada para leer la posicion
					if(linea.equals("-2")) System.exit(0); // El servidor me enviará -2 si el tablero está lleno
					
					System.out.println(linea);	
					
					linea = br.readLine();
				}
				fila = leo.nextInt();
				if(fila < 1 || fila > 3) eligePosicion(br, dos, leo);
				else dos.writeInt(leo.nextInt()); // Leo fila
				
				System.out.println(br.readLine()); // Digo que escriba columna
				
				columna = leo.nextInt();
				if(columna < 1 || columna > 3) eligePosicion(br, dos, leo);
				else dos.writeInt(leo.nextInt());
				dos.writeInt(leo.nextInt()); // Leo columna
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

	}
}
