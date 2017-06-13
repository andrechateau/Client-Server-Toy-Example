package com.andrechateau.javagroup.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
	private DatagramSocket socket;
	private byte[] buf = new byte[256];

	public Servidor() throws SocketException {
		socket = new DatagramSocket(1500);
		System.out.println("Servidor iniciado na porta 1500");
	}

	public void start() throws IOException{
		while (!socket.isClosed()) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				packet = new DatagramPacket(buf, buf.length, address, port);
				String mensagem = new String(packet.getData(), 0, packet.getLength());
				String remetente = new String(packet.getAddress().getHostAddress());
				System.out.println(remetente + " diz: " + mensagem);
		}
		socket.close();
	}

	public static void main(String[] args) throws IOException {
		new Servidor().start();
	}
}