package blockchain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class HandlerClient implements Runnable {
	private final Socket socket;
	private HandlerClientMaster mineur;
	HandlerClient(Socket socket, HandlerClientMaster mineur) {
		this.socket = socket;
		this.mineur = mineur;
	}
	
	public void run() {
		try{
			String line = "";
			String[] parts;
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			int number = 0;
			Transaction t;
			
			while ((line = reader.readLine()) != null) {
				parts = line.split(" ");
				int[] numbers = new int[parts.length];
				for(int i = 0;i < parts.length;i++)
				{
				   numbers[i] = Integer.parseInt(parts[i]);
				   System.out.println("receive : " + parts[i]);
				}
				t = new Transaction(numbers[0], numbers[1], numbers[2]);
				mineur.addTransaction(t);
			}
			

		}
		catch(SocketException e) {
			System.out.println("helloa");

			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println("hellob");


			System.out.println(e);
		}
		try {
			System.out.println("helloc");

			socket.close(); 
		}
		catch(IOException e) {
			System.out.println(e);
		}
	}
	
}
