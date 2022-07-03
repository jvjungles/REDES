package com.java;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {	

	// Servidor - ServerSocket
	public static void main(String[] args) throws IOException{
		try {
			ServerSocket server = new ServerSocket(50000);
			System.out.println("Server started !!! \n");
			boolean up = true;
			while (true) {
				Socket socket = server.accept();
				DataInputStream entrada = new DataInputStream(socket.getInputStream());
				DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
	
				String cpf = entrada.readUTF();
				boolean valido = ValidaCPF.validaCpf(cpf);			
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
		} catch (IOException e) {
			
		}
	}
}
