package blockchain;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class HandlerClientMaster implements Runnable {
	private int poolSize;
	private final ExecutorService pool;
	private final ServerSocket serverSocketClient;
	private Mineur mineur;
	
	public HandlerClientMaster(int poolSize, ExecutorService pool, ServerSocket serverSocketClient, Mineur mineur) {
		super();
		this.poolSize = poolSize;
		this.pool = pool;
		this.serverSocketClient = serverSocketClient;
		this.mineur = mineur;
	}
	
	



	@Override
	public void run() {
		
		while(true) {
			try{
				for(; ; ) { 
					pool.execute(new HandlerClient(serverSocketClient.accept(), this));
					System.out.println("hello");
				}
			} catch(IOException ex) { pool.shutdown(); }
		}
	}
	
	public void addTransaction(Transaction t) {
		mineur.addTransaction(t);
	}


}
