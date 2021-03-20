package entities;

import java.util.ArrayList;
import java.util.List;

import services.Operacoes;

public class FBF {
	//  B|A~C&(B~A&C)
	private String enunciado;
	private List<Variavel> variaveis = new ArrayList<Variavel>();

	
	
	
	public FBF(String enunciado) {
		this.enunciado = enunciado;
	}
	//preenche a lista de variaveis
	public void inicializarFBF() {
		variaveis.clear();
		if(enunciado.contains("A")) 
			variaveis.add(new Variavel("A"));
		
		if(enunciado.contains("B")) 
			variaveis.add(new Variavel("B"));
		
		if(enunciado.contains("C")) 
			variaveis.add(new Variavel("C"));
		
		if(enunciado.contains("D")) 
			variaveis.add(new Variavel("D"));
		
		if(enunciado.contains("E")) 
			variaveis.add(new Variavel("E"));
		
	}
	
	public void processarFBF(){
		//transforma & e | em * e +
		enunciado = enunciado.replace('&', '*');
		enunciado = enunciado.replace('|', '+');
	
		Operacoes.processar(variaveis, enunciado);
	}
	public List<Variavel> getVariaveis() {
		return variaveis;
	}
	public void setVariaveis(List<Variavel> variaveis) {
		this.variaveis = variaveis;
	}
	
	
}
