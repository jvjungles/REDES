package com.old;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * CETEJ33 - Java Aplicado A Redes De Computadores - JAVA_XXIV (2022_01)
 * 
 * Atividade 01 - Sockets Java 
 *
 * @author - Joao Jungles
 */
public class Cliente2 {

	public static void main(String[] args) {
		
		System.out.println("Inicio...\n");
		
		try {
            
			Socket socket = new Socket("127.0.0.1", 50000);            
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			DataOutputStream saida = new DataOutputStream(socket.getOutputStream());            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Digite o CPF: ");
            saida.writeUTF(br.readLine());            
            System.out.println(entrada.readUTF());
            
            socket.close();
            
        } catch(Exception e) {
            e.getMessage();
        }
		
		System.out.println("\nFim...");		
	}	
}
