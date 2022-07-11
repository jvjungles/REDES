package com.java;

public class ValidaCPF {
	// metodo que valida o cpf 
	public static boolean validaCpf(String cpf) {
		System.out.println("CPF...............: " + cpf);
		if (validString(cpf)) {			
			Integer xsoma = getDigito(cpf);
			Integer xresto = (xsoma % 11);
			Integer x = (xresto == 0 || xresto == 1) ? 0 : 11-xresto;			
			System.out.println("Penultimo Digito..: " + x);			
			Integer ysoma = getDigito(cpf.substring(1,9)+x);
			Integer yresto = (ysoma % 11);
			Integer y = (yresto == 0 || yresto == 1) ? 0 : 11-yresto;
			System.out.println("Ultimo Digito.....: " + y);			
			String valida = x.toString() + y.toString() ;				
			System.out.println("Digitos validar...: " + valida);
			System.out.println("Digitos cpf.......: " + cpf.substring(9,11));			
			return valida.equals(cpf.substring(9,11));
		}
		return false;
	}	
	// valida string digitada
	private static boolean validString(String cpf) {
		return !cpf.isEmpty() && cpf.length() == 11 && cpf.matches("[0-9]+") && verificaIguais(cpf);
	}	
	// verifica se todos os caracteres sao iguais
	private static boolean verificaIguais(String text) {		
		String[] listaCarga= new String[11];
		int cont = 0;
		for (int i = 0; i < text.length(); i++) {
			listaCarga[i] = text.substring(i, i+1);
		}
		for (int i = 0; i < listaCarga.length; i++) {
			for (int j = 0; j < text.length(); j++) {				
				if (i < 10 && listaCarga[i].equals(text.substring(i+1, i+2))) {cont++;};			
			}
		}
		return cont == 110 ? false : true;
	}	
	// get digitos finais
	private static int getDigito(String cpf) {		
		int soma= 0;					
		for (int j = 0; j < 10; j++) {				
			if (10-j!=1) {					
				soma += (Integer.parseInt(cpf.substring(0+j, 1+j)))*(10-j);						
			}			
		}		
		return soma;		
	}
}
