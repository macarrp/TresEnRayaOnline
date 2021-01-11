package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import tresEnRaya.Partida;

public class AtenderPeticion extends Thread{
	private Socket sc;
	private int idPartida;
	private Random r = new Random();
	
	public AtenderPeticion(Socket sc) {
		this.sc = sc;
	}
	
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(sc.getInputStream());
			DataOutputStream dos = new DataOutputStream(sc.getOutputStream());
			String linea = dis.readLine();
			Partida p = new Partida();
			
			if(linea.equals("CREAR")) {
				idPartida = r.nextInt(9); // Numero aleatorio del 0 al 9
				p.crearPartida(idPartida, sc);
			}
			else {
				idPartida = dis.readInt();
				p.unirsePartida(idPartida, sc);
			}
			
			if(p.hayJugadoresEmpezarPartida()) p.iniciarPartida();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
