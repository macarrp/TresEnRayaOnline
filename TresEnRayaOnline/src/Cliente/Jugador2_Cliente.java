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
		String linea;
		int scanLeido, leido;
		Scanner leo = new Scanner(System.in);
		
		try {
			conexion = new Socket("localhost", 5050);
			
			br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
			dos = new DataOutputStream(conexion.getOutputStream());
			
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
				linea = br.readLine();
			}
			
			scanLeido = leo.nextInt(); // Leo fila
			dos.writeInt(scanLeido);
			
			scanLeido = leo.nextInt(); // Leo columna
			dos.writeInt(scanLeido);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
