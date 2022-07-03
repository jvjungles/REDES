package com.old;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * CETEJ33 - Java Aplicado A Redes De Computadores - JAVA_XXIV (2022_01)
 * 
 * Atividade 01 - Sockets Java
 *
 * @author - Joao Jungles
 * 
 *         Fontes: https://gist.github.com/rafaelregis/3860801#file-client-java
 *         https://gist.github.com/chikadance/11131088
 *         https://github.com/filipecancio/servidorzinho/blob/main/src/dev/servidorzinho/Servidor.java
 *         https://www.youtube.com/watch?v=MXuhueBAsnc
 *         https://www.youtube.com/watch?v=-xKgxqG411c
 */
public class Servidor6 {

	

	public static void main(String[] args) {

		//try {

//			ServerSocket server = new ServerSocket(50000);
//			System.out.println("Server started !!!");
//
//			Socket socket = server.accept();
//			DataInputStream entrada = new DataInputStream(socket.getInputStream());
//			DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
//
//			String cpf = entrada.readUTF();
//			System.out.println(cpf);

			boolean cpfValido = isValidCPFmeu("04252308992");
			
//			String resultado = cpfValido ? "TRUE" : "FALSE";
//			
//			if (cpfValido) {
//				resultado = "TRUE";
//			}else {
//				resultado = "FALSE";
//			}
//			
//
//			saida.writeUTF(resultado);
//			socket.close();
//			server.close();

//		} catch (Exception e) {
//			e.getMessage();
//		}

	}


	
	private static boolean isValidCPFmeu(String cpf) {
		
		System.out.println("CPF:"+ cpf);
		
		if (!cpf.isEmpty() && cpf.length() == 11) {			
			
			Integer soma = calcularDigitoMeu(cpf);
			Integer resto = (soma % 11);
			Integer x = resto == 0 ? 0 : 11-resto;			
			System.out.println("Penultimo Digito: "+ x);			
			
			Integer soma2 = calcularDigitoMeu(cpf.substring(1,9)+x);
			Integer resto2 = (soma2 % 11);
			Integer y = 11-resto2;
			System.out.println("Ultimo Digito: "+ y);
			
			String valida = x.toString() + y.toString() ;
			
			System.out.println("Digitos validar: " + valida);
			System.out.println("Digitos cpf: " + cpf.substring(9,11));
			System.out.println("Resultado: " + valida.equals(cpf.substring(9,11)));	
			
			return valida.equals(cpf.substring(9,11));
		}
		return false;
	}
	
	
	
	private static int calcularDigitoMeu(String cpf) {
		
		int soma= 0;		
			
			for (int j = 0; j < 10; j++) {
				
				if (10-j!=1) {
					
//					System.out.println("---------------------------");
//					
//						System.out.println(10-j);
//						System.out.println(Integer.parseInt(cpf.substring(0+j, 1+j)));
//						
//					System.out.println("---------------------------");
//					
//						System.out.println((Integer.parseInt(cpf.substring(0+j, 1+j)))*(10-j));
						soma += (Integer.parseInt(cpf.substring(0+j, 1+j)))*(10-j);
						
//					System.out.println("---------------------------");
//					
//						System.out.println(soma);
//					
//					System.out.println("---------------------------");
//					System.out.println("\n\n");			
				
			}			
		}	
		
		return soma;		
	}
	
//	private static boolean isValidCPF(String cpf) {
//		
//		Integer penultimo = 0;
//		Integer ultimo = 0;
//				
//		System.out.println("init");
//		if (!cpf.isEmpty() && cpf.length() == 11) {
//			
//			for (int j = 0; j < 10; j++) {
//				if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
//					return false;
//			}				
//
//			penultimo = calcularDigito(cpf.substring(0, 9), pesoCPF);
//			ultimo = calcularDigito(cpf.substring(0, 9) + penultimo, pesoCPF);
//			
//		}else {
//			return false;
//		}
//			
//
//		System.out.println("end");
//		return cpf.equals(cpf.substring(0, 9) + penultimo.toString() + ultimo.toString());
//	}
//
//	private static String padLeft(String text, char character) {
//		return String.format("%11s", text).replace(' ', character);
//	}
//
//	private static int calcularDigito(String str, int[] peso) {
//		int soma = 0;
//		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
//			digito = Integer.parseInt(str.substring(indice, indice + 1));
//			soma += digito * peso[peso.length - str.length() + indice];
//		}
//		soma = 11 - soma % 11;
//		return soma > 9 ? 0 : soma;
//	}
}
