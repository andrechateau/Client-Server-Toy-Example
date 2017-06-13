package com.andrechateau.javagroup.server;

import java.io.InputStream;
import java.util.Scanner;

public class Ouvinte implements Runnable {
	private InputStream entrada;
	private String nome;

	public Ouvinte(InputStream entrada, String nome) {
		this.entrada = entrada;
		this.nome = nome;
	}

	public void run() {
		// recebe msgs e imprime na tela
		Scanner s = new Scanner(this.entrada);
		while (s.hasNextLine()) {
			System.out.println(nome + " diz: " + s.nextLine());
		}
	}
}