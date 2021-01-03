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
		
		noseque(br, dos, conexion, leo); 
		
	}
	
	public static void noseque(BufferedReader br, DataOutputStream dos, Socket conexion, Scanner leo) {
		try {
			conexion = new Socket("localhost", 5050);
			br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			dos = new DataOutputStream(conexion.getOutputStream());
			String linea = br.readLine();
			
			while(linea != null) { // Mandar null o -1
				System.out.println(linea);				
				linea = br.readLine();
			}
			
			dos.writeInt(leo.nextInt()); // Leo fila
			dos.writeInt(leo.nextInt()); // Leo columna
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
