package blockchain;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Mineur {
	private Blockchain blockchain;
	private int difficulte;
	private LinkedList<Transaction> bufferTransaction;
	
	private int port;
	private int poolSize;
	private final ExecutorService pool;
	private final ExecutorService poolSelFinder;
	private final CompletionService<Integer> completionSelFinder;
	private final ServerSocket serverSocketClient;
	//private final Socket serverSocketServer;
	private int lastCommit;
	private Bloc blocToAdd;
	private Future<Integer> sel;
	
	private int poolSizeSelFinder;
	public Mineur(Blockchain blockchain, 
			int difficulte, 
			int portClient, 
			String ipServer, 
			int portServer, 
			int poolSize,
			int poolSizeSelFinder) throws IOException {
		
		super();
		this.blockchain = blockchain;
		this.difficulte = difficulte;
		this.poolSize = poolSize;
		this.serverSocketClient = new ServerSocket(portClient);
		//this.serverSocketServer = new Socket(ipServer,portServer);
		this.pool = Executors.newFixedThreadPool(poolSize);
		
		this.bufferTransaction = new LinkedList<Transaction>();
		this.lastCommit = 0;
		this.blocToAdd = null;
		this.sel = null;
		
		this.poolSizeSelFinder = poolSizeSelFinder;
		this.poolSelFinder = Executors.newFixedThreadPool(poolSizeSelFinder);
		this.completionSelFinder = new ExecutorCompletionService<Integer>(this.poolSelFinder);
	}
	
	
	public void manageRequest() throws InterruptedException, ExecutionException {
		
		// Lancement du thread qui attend des
		// connexions avec les clients
		HandlerClientMaster h = new HandlerClientMaster(poolSize,pool, this.serverSocketClient, this);
		Thread th = new Thread(h);
		th.start();
		
		// Boucle principale
		while(true) {
			//System.out.println(this.sel);
			this.sel = this.completionSelFinder.poll();
			if (this.sel != null ) {
				
				int selInt = sel.get();

				
				// On verifie par sécurité que nos SelFinder ne disent pas n'importe quoi
				if (this.blockchain.inserable(difficulte, selInt, blocToAdd)){
					this.selFinderStop();

					this.blockchain.addBlock(blocToAdd, selInt);
					this.lastCommit++;
					this.sel = null;
					System.out.println(this);
					this.setAddBlock();
				}
			}
		}
	}
	
	
	public void selFinderStart(Bloc b) {
		for(int i=0; i<this.poolSizeSelFinder; i++) { 
			this.completionSelFinder.submit(new SelFinder(i, poolSizeSelFinder, blockchain, b, difficulte));
		}
	}
	
	public void selFinderStop() {
		this.poolSelFinder.shutdownNow();
	}
	
	
	public void addTransaction(Transaction t) {
		System.out.println("nouvelle transaction");
		this.bufferTransaction.add(t);
	
		if (blocToAdd == null) {
			this.setAddBlock();
		}
	}
	
	public void setAddBlock() {
		Transaction t;
		while(!this.bufferTransaction.isEmpty()) {
			t = this.bufferTransaction.removeFirst();
			Etat etat = blockchain.getEtat();
			Bloc b = new Bloc(etat.applyTransaction(t), t);
			
			// On decide de vérifier si la transaction est réalisable maintenant.
			// Sinon, on l'anule
			boolean ok = blockchain.verif(b);
			if (ok) {
				this.blocToAdd = b;
				System.out.println("calcule du sel");
				selFinderStart(blocToAdd);
			}
		}
	}
	
	public String toString() {
		return this.blockchain.toString();
		
	}
}
