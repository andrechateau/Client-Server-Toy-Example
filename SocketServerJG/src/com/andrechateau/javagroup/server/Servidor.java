package com.andrechateau.javagroup.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
	public void start() {
		ServerSocket servidor;
		List<Socket> clientes = new ArrayList<Socket>();
		try {
			// Criando um servidor que ouve a porta 1500
			servidor = new ServerSocket(1500);
			System.out.println("Servidor iniciado na porta 1500");
			while (!servidor.isClosed()) {
				// O servidor espera por um cliente requisitar uma conexão
				// Quando encontra uma requisição gera um socket.
				Socket cliente = servidor.accept();
				// adiciona cliente numa lista por controle.
				clientes.add(cliente);
				// Adiciona o stream de entrada na Thread
				String nome = cliente.getInetAddress().getHostAddress() + " (" + clientes.size() + ")";
				Ouvinte r = new Ouvinte(cliente.getInputStream(), nome);
				new Thread(r).start();
			}

			servidor.close();
			for (Socket cliente : clientes) {
				cliente.close();
			}

		} catch (java.net.BindException e) {
			System.out.println("Servidor não pode ser iniciado, porta ocupada!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Servidor().start();
	}
}
