package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Jugador2_Cliente {
	public static void main(String[] args) {
		Socket conexion = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		String linea;
		int scanLeido;
		Scanner leo = new Scanner(System.in);
		
		try {
			conexion = new Socket("localhost", 5050);
			
			dis = new DataInputStream(conexion.getInputStream());
			
			while((linea = dis.readLine()) != null) {
				System.out.println(linea);
				linea = dis.readLine();
			}
			
			scanLeido = leo.nextInt();
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
