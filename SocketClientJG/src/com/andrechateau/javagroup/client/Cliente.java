package com.andrechateau.javagroup.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	Socket servidor;

	public void start() {
		try {
			servidor = new Socket("127.0.0.1", 1500);
			System.out.println("Conectado com sucesso!");
			System.out.println("Diga alguma coisa ao servidor: ");
			Scanner teclado = new Scanner(System.in);
			PrintStream saida = new PrintStream(servidor.getOutputStream());

			while (teclado.hasNextLine()) {
				saida.println(teclado.nextLine());
			}

			saida.close();
			teclado.close();
			servidor.close();
		} catch (ConnectException e) {
			System.out.println("Conexão recusada. O servidor está online?");
		} catch (UnknownHostException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Cliente().start();
	}
}
