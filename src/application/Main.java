package application;

import java.util.List;

import javax.swing.JTextField;

import entities.Tela;

public class Main {

	public static Tela tela = new Tela();
	public static void main(String[] args) {
		/*E = &
		 *OU = |
		 *NÃO = - (Colocar antes da proposição)
		 *condicional = ~
		 *bi-condicional = %
		 */
	}
	
	
	public static boolean checaEnunciado(JTextField jText) {
		int nAParent = 0;
		int nFParent = 0;
		
		if(jText.getText().isBlank()) {
			jText.setText("A FBF está vazia");	
			return false; 
		}
		
		for (int i = 0; i < jText.getText().length(); i++) {
			if(jText.getText().charAt(i) == '(')
				nAParent++;
			if(jText.getText().charAt(i) == ')')
				nFParent++;
				
			if((jText.getText().length() != 1) && (i != jText.getText().length()-1) && (jText.getText().charAt(i) == 'A' || jText.getText().charAt(i) == 'B'
				|| jText.getText().charAt(i) == 'C' || jText.getText().charAt(i) == 'D'
				|| jText.getText().charAt(i) == 'E') && (jText.getText().charAt(i+1) != '&' &&
				jText.getText().charAt(i+1) != '|' && jText.getText().charAt(i+1) != '~'
				&& jText.getText().charAt(i+1) != '%' && jText.getText().charAt(i+1) != '(' && jText.getText().charAt(i+1) != ')')){
				jText.setText("A FBF digitada não é válida");
				return false;
			}
			
			if(jText.getText().charAt(i) != 'A' && jText.getText().charAt(i) != 'B'
				&& jText.getText().charAt(i) != 'C' && jText.getText().charAt(i) != 'D'
				&& jText.getText().charAt(i) != 'E' && jText.getText().charAt(i) != '&' &&
				jText.getText().charAt(i) != '|' && jText.getText().charAt(i) != '~'
				&& jText.getText().charAt(i) != '%' && jText.getText().charAt(i) != '('
				&& jText.getText().charAt(i) != ')' && jText.getText().charAt(i) != '-') {
				jText.setText("A FBF digitada não é válida");
				return false;
			}
		}
		if(jText.getText().charAt(0) != 'A' && jText.getText().charAt(0) != 'B' &&
				jText.getText().charAt(0) != 'C' && jText.getText().charAt(0) != 'D' &&
						jText.getText().charAt(0) != 'E' &&
								jText.getText().charAt(0) != '(' &&
										jText.getText().charAt(0) != '-') {
			jText.setText("A FBF digitada não é válida");
			return false;
		}
		if(nAParent != nFParent) {
			jText.setText("A FBF digitada não é válida");
			return false;
		}
		if( (jText.getText().contains("B") && !jText.getText().contains("A")) ||
			(jText.getText().contains("C") && !jText.getText().contains("B")) ||
			(jText.getText().contains("D") && !jText.getText().contains("C")) ||
			(jText.getText().contains("E") && !jText.getText().contains("D"))) {
			jText.setText("A FBF digitada não é válida");
			return false;
		}
		return true;
		
	}
	
	public static String printSolucoes(List<String> passoAPasso, String enunciado) {
		StringBuilder sb = new StringBuilder();
		int vControle = 1;
		sb.append("<html>");
		sb.append(vControle+": "+enunciado+" = ");
		for (int i = 0; i < passoAPasso.size(); i++) {
			passoAPasso.set(i, passoAPasso.get(i).replace('0', 'F'));
			passoAPasso.set(i, passoAPasso.get(i).replace('1', 'V'));
			passoAPasso.set(i, passoAPasso.get(i).replace('*', '&'));
			passoAPasso.set(i, passoAPasso.get(i).replace('+', '|'));
			sb.append(passoAPasso.get(i));
			if(passoAPasso.get(i).contains("FIM") && i != passoAPasso.size()-1) {
				sb.append("<br><br>");
				sb.append(++vControle+": "+enunciado+" = ");
			}
			else
				if(i != passoAPasso.size()-1)
					sb.append(" = ");
		}
		sb.append("</html");
		return sb.toString();
	}
	
}