package interfaz;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import tresEnRaya.Jugador;
import tresEnRaya.Tablero;
import javax.swing.JSplitPane;

public class InterfazGrafica extends JFrame {
	private Jugador j1, j2;
	private Tablero t;
	
	public InterfazGrafica(Tablero t) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazGrafica.class.getResource("/interfaz/Icono3.ico")));
		setTitle("Tres en Raya # ONLINE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 437, 407);
		setResizable(false);
		getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
		
		this.t = t;
		j1 = t.getJ1(); j2 = t.getJ2();
		
		JButton bt1 = new JButton("");
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt1.setText(fichaJugador());	
			}
		});
		getContentPane().add(bt1);
		
		JButton bt2 = new JButton("");
		bt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt2.setText(fichaJugador());
			}
		});
		getContentPane().add(bt2);
		
		JButton bt3 = new JButton("");
		bt3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt3.setText(fichaJugador());
			}
		});
		getContentPane().add(bt3);
		
		JButton bt4 = new JButton("");
		bt4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt4.setText(fichaJugador());
			}
		});
		getContentPane().add(bt4);
		
		JButton bt5 = new JButton("");
		bt5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt5.setText(fichaJugador());
			}
		});
		getContentPane().add(bt5);
		
		JButton bt6 = new JButton("");
		bt6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt6.setText(fichaJugador());
			}
		});
		getContentPane().add(bt6);
		
		JButton bt7 = new JButton("");
		bt7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt7.setText(fichaJugador());
			}
		});
		getContentPane().add(bt7);
		
		JButton bt8 = new JButton("");
		bt8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt8.setText(fichaJugador());
			}
		});
		getContentPane().add(bt8);
		
		JButton bt9 = new JButton("");
		bt9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt9.setText(fichaJugador());
			}
		});
		getContentPane().add(bt9);
		
		
	}

	public void mostrar() {
		this.setVisible(true);
	}
	
	private int getTurno() {
		if(t.numFichasEnTablero() % 2 == 0) return 0; 	// Si el numero de fichas es par, le toca a jugador 1
		else return 1;									// Si no, le toca al jugador 2
	}
	
	private String fichaJugador() {
		if(getTurno() == 0) return "X"; 	// Jugador 1
		else return "O";					// Jugador 2	
	}
}
