package blockchain;

import java.util.concurrent.Callable;

public class SelFinder  implements Callable{
	private int start;
	private int step;
	private Blockchain blockchain;
	private Mineur mineur;
	private Bloc bloc;
	private int difficulte;
	
	public SelFinder(int start, int step, Blockchain blockchain, Bloc bloc, int difficulte){
		super();
		this.start = start;
		this.step = step;
		this.blockchain = blockchain;
		this.mineur = mineur;
		this.bloc = bloc;
		this.difficulte = difficulte;
	}
	
	public Integer call() {
		System.out.println("lance");
		int i = this.start;
		while(!Thread.interrupted()) {
			if (blockchain.inserable(difficulte , i, bloc)) {
				return i;
			}
			i += this.step;
		}
		return -1;
	}
	
}
