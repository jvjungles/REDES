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
public class Servidor2 {

	private static final int[] pesoCPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };

	public static void main(String[] args) {

		try {

			ServerSocket server = new ServerSocket(50000);
			System.out.println("Server started !!!");

			Socket socket = server.accept();
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			DataOutputStream saida = new DataOutputStream(socket.getOutputStream());

			String cpf = entrada.readUTF();
			System.out.println(cpf);

			boolean cpfValido = isValidCPF(cpf);
			System.out.println(cpfValido);

			String resultado = cpfValido ? "TRUE" : "FALSE";
			
			if (cpfValido) {
				resultado = "TRUE";
			}else {
				resultado = "FALSE";
			}
			

			saida.writeUTF(resultado);
			socket.close();
			server.close();

		} catch (Exception e) {
			e.getMessage();
		}

	}

	private static boolean isValidCPF(String cpf) {
		
//		Os primeiros oito dígitos, ABCDEFGH, formam o número-base definido pela Receita Federal no momento da inscrição.
//		O nono dígito, I, define a Região Fiscal responsável pela inscrição.
//		O penúltimo, J, é o dígito verificador dos nove primeiros.
//		O último, K, é o dígito verificador dos noves anteriores a ele.
		
		//http://clubes.obmep.org.br/blog/a-matematica-nos-documentos-cpf/
		
		Integer penultimo = 0;
		Integer ultimo = 0;
				
		System.out.println("init");
		if (!cpf.isEmpty() && cpf.length() == 11) {
			
			for (int j = 0; j < 10; j++) {
				if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
					return false;
			}				

			penultimo = calcularDigito(cpf.substring(0, 9), pesoCPF);
			ultimo = calcularDigito(cpf.substring(0, 9) + penultimo, pesoCPF);
			
		}else {
			return false;
		}
			

		System.out.println("end");
		return cpf.equals(cpf.substring(0, 9) + penultimo.toString() + ultimo.toString());
	}

	private static String padLeft(String text, char character) {
		return String.format("%11s", text).replace(' ', character);
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
}
