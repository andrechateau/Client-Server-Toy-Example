package com.andrechateau.javagroup.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	private DatagramSocket socket;
	private InetAddress endereco;
	private byte[] buf;

	public Cliente() throws UnknownHostException, SocketException {
		socket = new DatagramSocket();
		endereco = InetAddress.getByName("localhost");
		System.out.println("Conectado com sucesso!");
	}

	public void start() throws IOException {
		Scanner teclado = new Scanner(System.in);
		System.out.println("Diga alguma coisa ao servidor: ");

		while (teclado.hasNextLine()) {
			String msg = teclado.nextLine();
			buf = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(buf, buf.length, endereco, 1500);
			socket.send(packet);
		}
	}

	public void close() {
		socket.close();
	}

	public static void main(String[] args) throws IOException {
		new Cliente().start();
	}
}