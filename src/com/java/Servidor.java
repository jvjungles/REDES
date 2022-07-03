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

			boolean up = true;
			while (true) {
				Socket socket = server.accept();
				DataInputStream entrada = new DataInputStream(socket.getInputStream());
				DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
	
				String cpf = entrada.readUTF();
				boolean valido = validaCpf(cpf);			
				String resultado = valido ? "TRUE" : "FALSE";
				System.out.println("Resultado.........: " + valido);
				
				if (valido) {				
					System.out.println("CPF Valido\n");
				}else {
					System.out.println("CPF Invalido\n");
				}			
	
				saida.writeUTF(resultado);
				socket.close();
				
				if (!up) {
					server.close();
					System.out.println("\nServer closed !!!");
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	private static boolean validaCpf(String cpf) {		
		
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

	private static boolean validString(String cpf) {
		return !cpf.isEmpty() && cpf.length() == 11 && cpf.matches("[0-9]+") && verificaIguais(cpf);
	}
	
	private static boolean verificaIguais(String text) {
		
		String[] listaCarga= new String[11];
		int cont = 0;
		for (int i = 0; i < text.length(); i++) {
			listaCarga[i] = text.substring(i, i+1);
		}
		for (int i = 0; i < listaCarga.length; i++) {
			for (int j = 0; j < text.length(); j++) {				
				if (i < 10 && listaCarga[i].equals(text.substring(i+1, i+2))) {
					cont++;
				};			
			}
		}
		return cont == 110 ? false : true;
	}
	
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
