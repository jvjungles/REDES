package com.java;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

	// Cliente - Socket
	public static void main(String[] args) {
		
		System.out.println("Inicio...\n");
		System.out.println("------------------------------------------");
		System.out.println("Client started !!!");
		System.out.println("------------------------------------------\n");
		
		try {	
			boolean up = true;			
			while (true) {
				System.out.println("------------------------------------------");
				Socket socket = new Socket("127.0.0.1", 50000);            
				DataInputStream entrada = new DataInputStream(socket.getInputStream());
				DataOutputStream saida = new DataOutputStream(socket.getOutputStream());            
	            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	            	            
	            System.out.println("-------------------------------");	            
	            System.out.println("Digite o CPF: ");
	            saida.writeUTF(br.readLine());
	            
	            if (entrada.readUTF().equals("TRUE")) {
	            	System.out.println("\nCPF Valido\n");
				}else {
					System.out.println("\nCPF Invalido\n");
				}	            
	            System.out.println("-------------------------------");
	            
	            if (!up) {
	            	socket.close();
					System.out.println("\nClient closed !!!");					
				}	            
	            System.out.println("------------------------------------------"); 
			}
        } catch(IOException e) {
            e.getMessage();
        }		
		System.out.println("\nFim...");		
	}	
}
