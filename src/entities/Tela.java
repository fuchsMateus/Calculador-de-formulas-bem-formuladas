package entities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

import application.Main;
import entities.view.Painel1;
import entities.view.Painel2;


public class Tela extends JFrame {
	private Painel1 painel1 = new Painel1();
	private Painel2 painel2;

		
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
		add(painel1);
	
		painel1.getProcessar().addActionListener( new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent e) {
					//checa se o enunciado está correto
					boolean enunciadoCorreto = true;
					enunciadoCorreto = Main.checaEnunciado(painel1.getCampoFBF());
					if(enunciadoCorreto == false) {
						return;
					}
					///////////////
					FBF fbf = new FBF(painel1.getCampoFBF().getText());
					fbf.inicializarFBF();
					
					fbf.processarFBF();
					
					
					List<Variavel>variaveis  = fbf.getVariaveis();
					Main.tela.remove(painel1);
					Main.tela.validate();
					painel2 = new Painel2(variaveis, painel1.getCampoFBF().getText());
					Main.tela.add(painel2);
					Main.tela.revalidate();
					
					
				
			}
		});
		
		
		setVisible(true);	
		
		
	}

	public Painel1 getPainel1() {
		return painel1;
	}
	
}
