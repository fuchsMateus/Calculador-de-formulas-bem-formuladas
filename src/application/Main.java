package application;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

import entities.FBF;
import entities.Tela;
import entities.Variavel;
import services.Operacoes;

public class Main {

	public static void main(String[] args) {
		/*E = &
		 *OU = |
		 *N�O = - (Colocar antes da proposi��o)
		 *condicional = ~
		 *bi-condicional = %
		 */
			
		Tela tela = new Tela();
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("REGRAS:\nE = &\r\n"
				+ "OU = |\r\n"
				+ "N�O = - (Colocar antes da proposi��o)\r\n"
				+ "Condicional = ~\r\n"
				+ "bi-condicional = %\n\nUse o m�ximo de par�nteses poss�veis"
				+ "\npara formular"
				+ "\n-------------------------------------");
		//checa se o enunciado est� correto
		boolean enunciadoCorreto = true;
		String enunciado;
		do {
			System.out.println("Digite a FBF: ");
			enunciado = sc.nextLine();
			enunciado = enunciado.toUpperCase();
			enunciado = enunciado.replace(" ", "");
			enunciadoCorreto = checaEnunciado(enunciado);
		} while (enunciadoCorreto == false);

		///////////////
		FBF fbf = new FBF(enunciado);
		fbf.inicializarFBF();
			
			
		fbf.processarFBF();
		LinkedHashMap<Integer[], Integer> resultados = Operacoes.getResultados();
		
		List<Integer[]> valoresPossiveis = Operacoes.getValoresPossiveis();
		List<Variavel> variaveis = fbf.getVariaveis();
		List<String> passoAPasso = Operacoes.getPassoAPasso();
		
		
		//printar o resultado
		StringBuilder result = new StringBuilder();
		result.append("\n");
		for (Variavel variavel : variaveis) {
			result.append(variavel.getNome()+" ");
		}
		result.append("\n");
		for (int i = 0; i < resultados.size(); i++) {
			for (int j = 0; j < valoresPossiveis.get(0).length; j++) {
				result.append(valoresPossiveis.get(i)[j]+"|");
			}
			result.append("= "+resultados.get(valoresPossiveis.get(i))+"\n");
		}
		String resultado = result.toString().replace('0', 'F');
		resultado = resultado.replace('1', 'V');
		System.out.println(resultado);
		////////////////////////////////////////////////

		System.out.print("Digite 1 para ver o passo a passo ou 0 para encerrar o programa: ");
		int escolha = sc.nextInt();
		sc.close();
		if(escolha == 1) {
			printSolucoes(passoAPasso, enunciado);
		}
	
	}
	
	
	public static boolean checaEnunciado(String enunciado) {
		int nAParent = 0;
		int nFParent = 0;
		for (int i = 0; i < enunciado.length(); i++) {
			if(enunciado.charAt(i) == '(')
				nAParent++;
			if(enunciado.charAt(i) == ')')
				nFParent++;
			
			if(enunciado.length() == 0) {
				System.out.println("A FBF digitada n�o � v�lida\n");
				return false;
			}
				
			if((enunciado.length() != 1) && (i != enunciado.length()-1) && (enunciado.charAt(i) == 'A' || enunciado.charAt(i) == 'B'
				|| enunciado.charAt(i) == 'C' || enunciado.charAt(i) == 'D'
				|| enunciado.charAt(i) == 'E') && (enunciado.charAt(i+1) != '&' &&
				enunciado.charAt(i+1) != '|' && enunciado.charAt(i+1) != '~'
				&& enunciado.charAt(i+1) != '%' && enunciado.charAt(i+1) != '(' && enunciado.charAt(i+1) != ')')){
				System.out.println("A FBF digitada n�o � v�lida\n");
				return false;
			}
			
			if(enunciado.charAt(i) != 'A' && enunciado.charAt(i) != 'B'
				&& enunciado.charAt(i) != 'C' && enunciado.charAt(i) != 'D'
				&& enunciado.charAt(i) != 'E' && enunciado.charAt(i) != '&' &&
				enunciado.charAt(i) != '|' && enunciado.charAt(i) != '~'
				&& enunciado.charAt(i) != '%' && enunciado.charAt(i) != '('
				&& enunciado.charAt(i) != ')' && enunciado.charAt(i) != '-') {
				System.out.println("A FBF digitada n�o � v�lida\n");
				return false;
			}
		}
		if(enunciado.charAt(0) != 'A' && enunciado.charAt(0) != 'B' &&
			enunciado.charAt(0) != 'C' && enunciado.charAt(0) != 'D' &&
			enunciado.charAt(0) != 'E' &&
			enunciado.charAt(0) != '(' &&
			enunciado.charAt(0) != '-') {
			System.out.println("A FBF digitada n�o � v�lida\n");
			return false;
		}
		if(nAParent != nFParent) {
			System.out.println("A FBF digitada n�o � v�lida\n");
			return false;
		}
		if( (enunciado.contains("B") && !enunciado.contains("A")) ||
			(enunciado.contains("C") && !enunciado.contains("B")) ||
			(enunciado.contains("D") && !enunciado.contains("C")) ||
			(enunciado.contains("E") && !enunciado.contains("D"))) {
			System.out.println("A FBF digitada n�o � v�lida\n");
			return false;
		}
		return true;
		
	}
	
	public static void printSolucoes(List<String> passoAPasso, String enunciado) {
		int vControle = 1;
		System.out.print(vControle+": "+enunciado+" = ");
		for (int i = 0; i < passoAPasso.size(); i++) {
			passoAPasso.set(i, passoAPasso.get(i).replace('0', 'F'));
			passoAPasso.set(i, passoAPasso.get(i).replace('1', 'V'));
			passoAPasso.set(i, passoAPasso.get(i).replace('*', '&'));
			passoAPasso.set(i, passoAPasso.get(i).replace('+', '|'));
			System.out.print(passoAPasso.get(i));
			if(passoAPasso.get(i).contains("FIM") && i != passoAPasso.size()-1) {
				System.out.println("\n");
				System.out.print(++vControle+": "+enunciado+" = ");
			}
			else
				if(i != passoAPasso.size()-1)
				System.out.print(" = ");
		}
	}
	
}