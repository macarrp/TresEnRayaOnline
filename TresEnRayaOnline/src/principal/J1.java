package principal;

import interfaz.InterfazGrafica;
import servidor.Servidor;

public class J1 {
	public static void main (String[] args) {
//		InterfazGrafica interfaz = new InterfazGrafica();
//		interfaz.mostrarInterfaz();
		
		Servidor j1 = new Servidor(1337);
		j1.start();
		
	}
}
