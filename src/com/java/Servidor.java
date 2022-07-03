package com.java;

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
 * Fonte: http://clubes.obmep.org.br/blog/a-matematica-nos-documentos-cpf/
 */
public class Servidor {	

	public static void main(String[] args) {

		try {

			ServerSocket server = new ServerSocket(50000);
			System.out.println("Server started !!! \n");

			Socket socket = server.accept();
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			DataOutputStream saida = new DataOutputStream(socket.getOutputStream());

			String cpf = entrada.readUTF();			
			boolean valido = isValidCPFmeu(cpf);			
			String resultado = valido ? "TRUE" : "FALSE";
			System.out.println("Resultado.........: " + valido);
			
			if (valido) {				
				System.out.println("\nCPF Valido");
			}else {
				System.out.println("\nCPF Invalido");
			}			

			saida.writeUTF(resultado);
			socket.close();
			server.close();
			System.out.println("\nServer closed !!!");

		} catch (Exception e) {
			e.getMessage();
		}

	}
	
	private static boolean isValidCPFmeu(String cpf) {		
		
		System.out.println("CPF...............: " + cpf);
		
		if (!cpf.isEmpty() && cpf.length() == 11) {			
			
			Integer somax = calDigito(cpf);
			Integer restox = (somax % 11);
			Integer x = (restox == 0 || restox == 1) ? 0 : 11-restox;			
			System.out.println("Penultimo Digito..: " + x);			
			
			Integer somay = calDigito(cpf.substring(1,9)+x);
			Integer restoy = (somay % 11);
			Integer y = (restoy == 0 || restoy == 1) ? 0 : 11-restoy;
			System.out.println("Ultimo Digito.....: " + y);
			
			String valida = x.toString() + y.toString() ;
			
			System.out.println("Digitos validar...: " + valida);
			System.out.println("Digitos cpf.......: " + cpf.substring(9,11));	
			
			return valida.equals(cpf.substring(9,11));
		}
		return false;
	}
	
	private static int calDigito(String cpf) {		
		int soma= 0;					
		for (int j = 0; j < 10; j++) {				
			if (10-j!=1) {					
				soma += (Integer.parseInt(cpf.substring(0+j, 1+j)))*(10-j);						
			}			
		}		
		return soma;		
	}
}
