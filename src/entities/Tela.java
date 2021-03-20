package entities;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import entities.view.Painel1;

public class Tela extends JFrame {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 6522229125567045020L;

	public Tela() {
		setSize(new Dimension(540, 540));
		setTitle("Teste");
		setLocationRelativeTo(null);
		setResizable(false);
		setBackground(Color.white);
		
		
		
		
		
		add(new Painel1());
		
		setVisible(true);
	}	
}
