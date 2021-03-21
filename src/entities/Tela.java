package entities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;

import application.Main;
import entities.view.Painel1;
import entities.view.Painel2;
import entities.view.Painel3;


public class Tela extends JFrame {
	private Painel1 painel1 = new Painel1();
	private Painel2 painel2;
	private Painel3 painel3;

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 6522229125567045020L;

	public Tela() {
		setSize(new Dimension(540, 540));
		setTitle("Calculador de FBFs");
		setLocationRelativeTo(null);
		setResizable(false);
		setBackground(Color.white);
		Image icon = Toolkit.getDefaultToolkit().getImage("src/icon.png");
		setIconImage(icon);
		add(painel1);
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Dispose Java (TM) Platform SE binary.
                dispose();
                // Close the Java.exe I'm not sure.
                System.exit(0);
            }
        });
    
		
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
					Main.tela.repaint();
					painel2 = new Painel2(variaveis, painel1.getCampoFBF().getText());
					
					Main.tela.add(painel2);
					Main.tela.revalidate();	
					Main.tela.repaint();
					
					painel2.getVoltar().addActionListener( new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Main.tela.remove(painel2);
							Main.tela.revalidate();
							Main.tela.repaint();
							painel2 = null;
					
							
							Main.tela.add(painel1);
							Main.tela.revalidate();
							Main.tela.repaint();	
							
						}
					});
					
					painel2.getResolucao().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							Main.tela.remove(painel2);
							Main.tela.validate();
							Main.tela.repaint();
							painel3 = new Painel3(painel2.getPassoAPasso(), painel1.getCampoFBF().getText());
							
							Main.tela.add(painel3);
							Main.tela.revalidate();	
							Main.tela.repaint();
							
							painel3.getVoltar2().addActionListener(new ActionListener() {
								
								@Override
								public void actionPerformed(ActionEvent e) {
									Main.tela.remove(painel3);
									Main.tela.revalidate();
									Main.tela.repaint();
									painel3 = null;
							
									
									Main.tela.add(painel2);
									Main.tela.revalidate();
									Main.tela.repaint();	
								}
							});
							
						}
					});
					
			}
		});
		
		setVisible(true);	
		
		
	}

	public Painel1 getPainel1() {
		return painel1;
	}
	
}
