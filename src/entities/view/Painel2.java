package entities.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import entities.Variavel;
import services.Operacoes;

public class Painel2 extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1960628858830049130L;
	
	
	private JButton voltar = new JButton("Voltar");
	private JButton resolucao = new JButton("Resolução");
	private JTable table;
	
	
	public Painel2(List<Variavel> variaveis, String enunciado) {
	    GridBagLayout gridBagLayout = new GridBagLayout();
	    setLayout(gridBagLayout);
	   
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		
		
		
		
		
		setBackground(Color.white);
		
		LinkedHashMap<Integer[], Integer> resultados = Operacoes.getResultados();
		
		List<Integer[]> valoresPossiveis = Operacoes.getValoresPossiveis();
		List<String> passoAPasso = Operacoes.getPassoAPasso();
		
		
		String[] columns = new String[variaveis.size()+1];
		
		for (int i = 0; i < variaveis.size(); i++) {
			columns[i] = variaveis.get(i).getNome();
		}
		columns[variaveis.size()] = enunciado;		
		
		String[][] data = new String [valoresPossiveis.size()][variaveis.size()+1];
		
		for (int i = 0; i < valoresPossiveis.size(); i++) {
			for (int j = 0; j < variaveis.size(); j++) {
				if(valoresPossiveis.get(i)[j] == 1) 
					data[i][j] = "V";
				else 
					data[i][j] = "F";
			}
			if(resultados.get(valoresPossiveis.get(i)) == 1)
				data[i][variaveis.size()] = "V";
			else
				data[i][variaveis.size()] = "F";
		}
		
		table = new JTable(data, columns);
		table.setEnabled(false);
		
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int x=0;x<variaveis.size()+1;x++){
	         table.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
	        }
		
		JScrollPane sPane =  new JScrollPane(table);
		if(variaveis.size() == 1)
			sPane.setPreferredSize(new Dimension(420,55));
		if(variaveis.size() == 2)
			sPane.setPreferredSize(new Dimension(470,87));
		if(variaveis.size() == 3)
			sPane.setPreferredSize(new Dimension(470,151));
		if(variaveis.size() == 4)
			sPane.setPreferredSize(new Dimension(470,279));
		if(variaveis.size() == 5)
			sPane.setPreferredSize(new Dimension(500,399));
		sPane.setEnabled(false);
		add(sPane);
		
		
		
		gridBagConstraints.gridy++;
		add(Box.createVerticalStrut(25), gridBagConstraints);
		
		gridBagConstraints.gridy++;
		gridBagConstraints.gridwidth = 1;
		add(voltar, gridBagConstraints);
		gridBagConstraints.gridy++;
		add(Box.createVerticalStrut(15), gridBagConstraints);
		gridBagConstraints.gridy++;
		add(resolucao, gridBagConstraints);
		gridBagConstraints.gridy++;
		add(Box.createVerticalStrut(10), gridBagConstraints);
		

	}


	public JButton getVoltar() {
		return voltar;
	}


	public JButton getResolucao() {
		return resolucao;
	}

	
}
