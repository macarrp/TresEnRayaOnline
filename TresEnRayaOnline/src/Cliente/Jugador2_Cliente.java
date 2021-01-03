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
			
			eligePosicion(br, dos, conexion, leo); // Primera ficha
			eligePosicion(br, dos, conexion, leo); // Segunda ficha
			eligePosicion(br, dos, conexion, leo); // Tercera ficha
			eligePosicion(br, dos, conexion, leo); // Cuarta ficha
			eligePosicion(br, dos, conexion, leo); // Quinta ficha
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void eligePosicion(BufferedReader br, DataOutputStream dos, Socket conexion, Scanner leo) {
			String linea;
			try {
				linea = br.readLine();
				
				while(!linea.equals("-1")) { 
					System.out.println(linea);				
					linea = br.readLine();
				}
				dos.writeInt(leo.nextInt()); // Leo fila
				
				System.out.println(br.readLine());
				dos.writeInt(leo.nextInt()); // Leo columna
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

	}
}
