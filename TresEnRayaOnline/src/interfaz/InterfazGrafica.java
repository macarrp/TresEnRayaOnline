package interfaz;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfazGrafica extends JFrame {
	public InterfazGrafica() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mel_a\\git\\TresEnRayaOnline\\TresEnRayaOnline\\Icono3.ico"));
		setTitle("Tres en Raya # ONLINE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 400, 400, 400);
		setResizable(false);
		getContentPane().setLayout(new GridLayout(0, 3, 0, 0));
		
		JButton bt1 = new JButton("");
		bt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt1.setText("X");	
				
			}
		});
		getContentPane().add(bt1);
		
		JButton bt2 = new JButton("");
		bt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt2.setText("X");
			}
		});
		getContentPane().add(bt2);
		
		JButton bt3 = new JButton("");
		bt3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt3.setText("X");
			}
		});
		getContentPane().add(bt3);
		
		JButton bt4 = new JButton("");
		bt4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt4.setText("X");
			}
		});
		getContentPane().add(bt4);
		
		JButton bt5 = new JButton("");
		bt5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt5.setText("X");
			}
		});
		getContentPane().add(bt5);
		
		JButton bt6 = new JButton("");
		bt6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt6.setText("X");
			}
		});
		getContentPane().add(bt6);
		
		JButton bt7 = new JButton("");
		bt7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt7.setText("X");
			}
		});
		getContentPane().add(bt7);
		
		JButton bt8 = new JButton("");
		bt8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt8.setText("X");
			}
		});
		getContentPane().add(bt8);
		
		JButton bt9 = new JButton("");
		bt9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bt9.setText("X");
			}
		});
		getContentPane().add(bt9);
		
		
	}

	public void mostrarInterfaz() {
		this.setVisible(true);
	}
}
