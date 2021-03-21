package entities.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import application.Main;

public class Painel3 extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7718783202986591628L;
	private JButton voltar2 = new JButton("Voltar");
	private JLabel resolucao = new JLabel();

	public Painel3(List<String> passoAPasso, String enunciado) {
		setBackground(Color.white);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		
		setLayout(gridBagLayout);
		
		add(voltar2, gridBagConstraints);
		
		gridBagConstraints.gridy++;
		add(Box.createVerticalStrut(15), gridBagConstraints);
		
		resolucao.setText(Main.printSolucoes(passoAPasso, enunciado));
		resolucao.setAutoscrolls(true);
		
		JScrollPane sPane = new JScrollPane(resolucao);
		sPane.setPreferredSize(new Dimension(420,420));
		
		gridBagConstraints.gridy++;
		add(sPane, gridBagConstraints);
		
	}

	public JButton getVoltar2() {
		return voltar2;
	}
	
	
}
