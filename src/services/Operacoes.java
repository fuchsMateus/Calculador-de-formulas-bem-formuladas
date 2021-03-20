package services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import entities.Variavel;

public class Operacoes {
	
	private static LinkedHashMap<Integer[], Integer> resultados = new LinkedHashMap<Integer[], Integer>();
	private static List<Integer[]> valoresPossiveis = new ArrayList<>();
	private static List<String> passoAPasso =  new ArrayList<String>();

	public static void processar(List<Variavel> variaveis, String enunciado) {
		
		//processa a fbf para cada possibilidade
				for(Integer i = 0; i < (int) Math.pow(2, variaveis.size()); i++) {
					if(i == 0) {
						for (Variavel variavel : variaveis) {
							variavel.setValor(0);
						}
						Operacoes.getResultados(variaveis, enunciado);
					}
					else {
						String combinacao = Integer.toBinaryString(i);
						
						while(combinacao.length() != variaveis.size()){
							combinacao = "0"+combinacao;
						}
						
						for (Variavel variavel : variaveis) {
							variavel.setValor(Integer.valueOf(combinacao.substring(0, 1)));
							combinacao = combinacao.substring(1);
						}
						Operacoes.getResultados(variaveis, enunciado);
					}
			}			
	}
	
	private static void getResultados(List<Variavel> variaveis, String enunciado){
		Integer valorFinal;
		Integer valoresPossiveisVetor[] = new Integer[variaveis.size()];
		
		//passo1: substituir as letras por seus respectivos valores
		if(enunciado.contains("A"))
			enunciado = enunciado.replace('A', String.valueOf(variaveis.get(0).getValor()).charAt(0));
		if(enunciado.contains("B"))
			enunciado = enunciado.replace('B', String.valueOf(variaveis.get(1).getValor()).charAt(0));
		if(enunciado.contains("C"))
			enunciado = enunciado.replace('C', String.valueOf(variaveis.get(2).getValor()).charAt(0));
		if(enunciado.contains("D"))
			enunciado = enunciado.replace('D', String.valueOf(variaveis.get(3).getValor()).charAt(0));
		if(enunciado.contains("E"))
			enunciado = enunciado.replace('E', String.valueOf(variaveis.get(4).getValor()).charAt(0));
		
		for (int i = 0; i < variaveis.size(); i++) {
			valoresPossiveisVetor[i] = variaveis.get(i).getValor();
		}
		
		valoresPossiveis.add(valoresPossiveisVetor);
		passoAPasso.add(enunciado);
		//passo2: processar todos os parenteses
		enunciado = Operacoes.resolverComParenteses(enunciado);
		
		
		while(enunciado.length() != 1) {
			//transforma todas as negações
			if(enunciado.contains("-")) {
				for(int i = 0; i < enunciado.length(); i++) {
					if(enunciado.charAt(i) == '-') {
						if(enunciado.charAt(i+1) == '0') {
							enunciado = enunciado.substring(0, i+1) + "1" + enunciado.substring(i+2);
							enunciado = enunciado.substring(0, i) + enunciado.substring(i+1);
							i = 0;
						}
						else if(enunciado.charAt(i+1) == '1') {
							enunciado = enunciado.substring(0, i+1) + "0" + enunciado.substring(i+2);
							enunciado = enunciado.substring(0, i) + enunciado.substring(i+1);
							i = 0;
						}
					}
				}
				passoAPasso.add(enunciado);
			}
			
			//faz todas as somas
			if(enunciado.contains("+")) {
				for(int i = 0; i < enunciado.length(); i++) {
					if(enunciado.charAt(i) == '+') {
						
						String somaS = String.valueOf(Math.round(Evaluator.eval(enunciado.substring(i-1,i+2))));
						int soma = Integer.valueOf(somaS);
						if(soma > 1)
							soma = 1;
						enunciado = enunciado.substring(0, i-1)+String.valueOf(soma)+
								enunciado.substring(i+2);
						i = 0;
					}
				}
				passoAPasso.add(enunciado);
			}
			
			//faz todas as multiplicações
			if(enunciado.contains("*")) {
				for(int i = 0; i < enunciado.length(); i++) {
					if(enunciado.charAt(i) == '*') {
						
						String multiS = String.valueOf(Math.round(Evaluator.eval(enunciado.substring(i-1,i+2))));
						int multi = Integer.valueOf(multiS);
						enunciado = enunciado.substring(0, i-1)+String.valueOf(multi)+
								enunciado.substring(i+2);
						i = 0;
					}	
				}
				passoAPasso.add(enunciado);
			}
			
			//resolve a condicional sem parenteses
			boolean temCondicional = false;
			if(enunciado.contains("~")) {
				for(int i = 0; i < enunciado.length(); i++) {
					if(enunciado.charAt(i) == '~') {
						enunciado = enunciado.substring(0, i)+"+"+enunciado.substring(i+1);
						enunciado = "-"+enunciado;
					} 	
				}
				temCondicional = true;
				passoAPasso.add(enunciado);
			}
			if(temCondicional)
				continue;
			
			//resolve todas as bi-condicionais
			boolean temBiCondicional = false;
			if(enunciado.contains("%")) {
				for(int i = 0; i < enunciado.length(); i++) {
					if(enunciado.charAt(i) == '%') {
						String a = enunciado.substring(0, i);
						String b = enunciado.substring(i+1);
			
						enunciado = "("+a+"~"+b+")*("+b+"~"+a+")";
					} 	
				}
				enunciado = Operacoes.resolverComParenteses(enunciado);
				temBiCondicional = true;
				passoAPasso.add(enunciado);
			}
			if(temBiCondicional)
				continue;
		}
		valorFinal = Integer.valueOf(enunciado);
		passoAPasso.add(enunciado);
		passoAPasso.add("FIM");
		resultados.put(valoresPossiveisVetor, valorFinal);
		
	}
	
	public static String resolverComParenteses(String enunciado) {
		while(enunciado.contains("(")) {
			int abreP = enunciado.lastIndexOf("(");
			int fechaP = abreP + enunciado.substring(abreP).indexOf(")");
			
			//parte para trabalhar com o que está dentro dos parenteses
			String enunciadoEntreParenteses = enunciado.substring(abreP+1, fechaP);
			
			//transforma todas as negações
			if(enunciadoEntreParenteses.contains("-")) {
				for(int i = 0; i < enunciadoEntreParenteses.length(); i++) {
					if(enunciadoEntreParenteses.charAt(i) == '-') {
						if(enunciadoEntreParenteses.charAt(i+1) == '0') {
							enunciadoEntreParenteses = enunciadoEntreParenteses.substring(0, i+1) + "1" + enunciadoEntreParenteses.substring(i+2);
							enunciadoEntreParenteses = enunciadoEntreParenteses.substring(0, i) + enunciadoEntreParenteses.substring(i+1);
							i = 0;
						}
						else if(enunciadoEntreParenteses.charAt(i+1) == '1') {
							enunciadoEntreParenteses = enunciadoEntreParenteses.substring(0, i+1) + "0" + enunciadoEntreParenteses.substring(i+2);
							enunciadoEntreParenteses = enunciadoEntreParenteses.substring(0, i) + enunciadoEntreParenteses.substring(i+1);
							i = 0;
						}
					}
				}
				passoAPasso.add(enunciado.substring(0, abreP+1)+enunciadoEntreParenteses+enunciado.substring(fechaP));
			}
			//faz todas as somas
			if(enunciadoEntreParenteses.contains("+")) {
				for(int i = 0; i < enunciadoEntreParenteses.length(); i++) {
					if(enunciadoEntreParenteses.charAt(i) == '+') {
						
						String somaS = String.valueOf(Math.round(Evaluator.eval(enunciadoEntreParenteses.substring(i-1,i+2))));
						int soma = Integer.valueOf(somaS);
						if(soma > 1)
							soma = 1;
						enunciadoEntreParenteses = enunciadoEntreParenteses.substring(0, i-1)+String.valueOf(soma)+
								enunciadoEntreParenteses.substring(i+2);
						i = 0;
					}
				}
				passoAPasso.add(enunciado.substring(0, abreP+1)+enunciadoEntreParenteses+enunciado.substring(fechaP));
			}
			
			//faz todas as multiplicações
			if(enunciadoEntreParenteses.contains("*")) {
				for(int i = 0; i < enunciadoEntreParenteses.length(); i++) {
					if(enunciadoEntreParenteses.charAt(i) == '*') {
						
						String multiS = String.valueOf(Math.round(Evaluator.eval(enunciadoEntreParenteses.substring(i-1,i+2))));
						int multi = Integer.valueOf(multiS);
						enunciadoEntreParenteses = enunciadoEntreParenteses.substring(0, i-1)+String.valueOf(multi)+
								enunciadoEntreParenteses.substring(i+2);
						i = 0;
					}	
				}
				passoAPasso.add(enunciado.substring(0, abreP+1)+enunciadoEntreParenteses+enunciado.substring(fechaP));
			}
					
			//resolve todas as condicionais
			boolean temCondicional = false;
			if(enunciadoEntreParenteses.contains("~")) {
				for(int i = 0; i < enunciadoEntreParenteses.length(); i++) {
					if(enunciadoEntreParenteses.charAt(i) == '~') {
						enunciadoEntreParenteses = enunciadoEntreParenteses.substring(0, i)+"+"+enunciadoEntreParenteses.substring(i+1);
						enunciadoEntreParenteses = "-"+enunciadoEntreParenteses;
					} 	
				}
				enunciado = enunciado.substring(0, abreP+1)+enunciadoEntreParenteses+enunciado.substring(fechaP);
				passoAPasso.add(enunciado);
				temCondicional = true;
			}
		
			if(temCondicional)
				continue;
			
			//resolve todas as bi-condicionais
			boolean temBiCondicional = false;
			if(enunciadoEntreParenteses.contains("%")) {
				for(int i = 0; i < enunciadoEntreParenteses.length(); i++) {
					if(enunciadoEntreParenteses.charAt(i) == '%') {
						String a = enunciadoEntreParenteses.substring(0, i);
						String b = enunciadoEntreParenteses.substring(i+1);
						//1%0   (1~0)*(0~1)
						enunciadoEntreParenteses = "("+a+"~"+b+")*("+b+"~"+a+")";
					} 	
				}
				enunciado = enunciado.substring(0, abreP+1)+enunciadoEntreParenteses+enunciado.substring(fechaP);
				passoAPasso.add(enunciado);
				temBiCondicional = true;
			}
		
			if(temBiCondicional)
				continue;
			
			//retira os parenteses
			enunciado = enunciado.substring(0, abreP)+enunciadoEntreParenteses+enunciado.substring(fechaP+1);
			passoAPasso.add(enunciado);
		}
		return enunciado;
	}

	public static LinkedHashMap<Integer[], Integer> getResultados() {
		return resultados;
	}

	public static List<Integer[]> getValoresPossiveis() {
		return valoresPossiveis;
	}

	public static List<String> getPassoAPasso() {
		return passoAPasso;
	}

	

	
	
	
}

