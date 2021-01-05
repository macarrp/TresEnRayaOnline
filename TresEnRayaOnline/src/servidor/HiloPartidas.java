package servidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tresEnRaya.Partida;

public class HiloPartidas extends Thread{
	private ServerSocket server;
	private int numPartida;
	
	public HiloPartidas(ServerSocket server, int numeroPartida) {
		this.server = server;
		this.numPartida = numeroPartida;
	}
	
	public int getNumPartida() {
		return numPartida++;
	}
	
	public void run() {
//		try {
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
