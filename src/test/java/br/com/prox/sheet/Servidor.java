package br.com.prox.sheet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
public class Servidor {
   public static void main(String[] args) throws IOException {
	   
     ServerSocket servidor = new ServerSocket(12345);
     System.out.println("Porta 12345 aberta!");
     
     Socket cliente = servidor.accept();
     System.out.println("Nova conex�o com o cliente " +   
       cliente.getInetAddress().getHostAddress()
     );
     
     Scanner s = new Scanner(cliente.getInputStream());
     while (s.hasNextLine()) {
       System.out.println(s.nextLine());
     }
     
     s.close();
     servidor.close();
     cliente.close();
   }
 }