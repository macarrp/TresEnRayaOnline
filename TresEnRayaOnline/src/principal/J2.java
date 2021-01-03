package principal;

import cliente.Cliente;

public class J2 {
	public static void main (String[] args) {
//		InterfazGrafica interfaz = new InterfazGrafica();
//		interfaz.mostrarInterfaz();
		
		Cliente j2 = new Cliente("localhost", 1337);
		j2.start();
		
	}
}
