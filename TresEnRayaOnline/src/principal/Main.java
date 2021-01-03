package principal;

import cliente.Cliente_J2;
import servidor.Servidor;

public class Main {
	public static void main (String[] args) {
//		InterfazGrafica interfaz = new InterfazGrafica();
//		interfaz.mostrarInterfaz();
		
		Servidor j1 = new Servidor(1337);
		j1.start();
		
		Cliente_J2 j2 = new Cliente_J2("localhost", 1337);
		j2.start();
		
	}
}
