package entities.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Painel1 extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1960628858830049130L;
	private JLabel titulo = new JLabel("Calculador de FBFs");
	private JLabel regras = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Conectivo Lógico: NÃO = -<br>"
										+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Conectivo Lógico: E = &<br>"
										+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Conectivo Lógico: OU = |"
										+ "<br>&nbsp;&nbsp;Conectivo Lógico: Condicional = ~<br>"
										+"Conectivo Lógico: Bicondicional = %"+"</html>"); 
	
	private JLabel obs = new JLabel("<html>Use o máximo de parênteses possíveis para evitar ambiguidades<br>"
			+ "São permitidas no máximo 5 variáveis proposicionais (A, B, C, D, E)</html>");
	
	private JTextField campoFBF = new JTextField();
	
	private JButton processar = new JButton();
	
	private JButton negacao = new JButton("-");
	private JButton conjuncao = new JButton("&");
	private JButton disjuncao = new JButton("|");
	private JButton condicional = new JButton("~");
	private JButton bicondicional = new JButton("%");
	
	public Painel1() {
	    GridBagLayout gridBagLayout = new GridBagLayout();
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 10;
		gridBagConstraints.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints.anchor = gridBagConstraints.CENTER;
		
		
		setLayout(gridBagLayout);
		
		titulo.setFont(new Font("serif", Font.PLAIN, 40));
		add(titulo, gridBagConstraints);
		
		gridBagConstraints.gridy++;
		add(Box.createVerticalStrut(20), gridBagConstraints);
		
		gridBagConstraints.gridy++;
		regras.setFont(new Font("serif", Font.PLAIN, 20));
		add(regras, gridBagConstraints);
		
		gridBagConstraints.gridy++;
		add(Box.createVerticalStrut(20), gridBagConstraints);
		
		gridBagConstraints.gridy++;
		obs.setFont(new Font("serif", Font.PLAIN, 14));
		add(obs, gridBagConstraints);
		
		gridBagConstraints.gridy++;
		add(Box.createVerticalStrut(25), gridBagConstraints);
		
		gridBagConstraints.gridy++;
		campoFBF.setPreferredSize(new Dimension(320,25));
		campoFBF.setHorizontalAlignment(JTextField.CENTER);
		campoFBF.addKeyListener(new KeyAdapter() {

			  public void keyTyped(KeyEvent e) {
			    char keyChar = e.getKeyChar();
			    if (Character.isLowerCase(keyChar)) {
			      e.setKeyChar(Character.toUpperCase(keyChar));
			    }
			  }

			});
		
		add(campoFBF, gridBagConstraints);
		
		
		gridBagConstraints.gridy++;
		
		gridBagConstraints.gridwidth = 1;
		
		
		add(Box.createHorizontalStrut(94), gridBagConstraints);
		gridBagConstraints.gridx++;
		add(negacao, gridBagConstraints);
		gridBagConstraints.gridx++;
		add(conjuncao, gridBagConstraints);
		gridBagConstraints.gridx++;
		add(disjuncao, gridBagConstraints);
		gridBagConstraints.gridx++;
		add(condicional, gridBagConstraints);
		gridBagConstraints.gridx++;
		add(bicondicional, gridBagConstraints);
		gridBagConstraints.gridy++;
		
		gridBagConstraints.gridx = 0;
	
		add(Box.createVerticalStrut(40), gridBagConstraints);
		
		gridBagConstraints.gridwidth = 10;
		gridBagConstraints.gridy++;
		processar.setText("Processar");
		add(processar, gridBagConstraints);
		
		setBackground(Color.white);
	}
}
