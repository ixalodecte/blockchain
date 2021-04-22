package blockchain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Jonction{
	private Bloc bloc;
	private int sel;
	private int hash;
	
	public Jonction(Bloc bloc, int sel, int hash) {
		super();
		this.bloc = bloc;
		this.sel = sel;
		this.hash = hash;
	}

	public Bloc getBloc() {
		return bloc;
	}

	public int getSel() {
		return sel;
	}

	public int getHash() {
		return hash;
	}
}

public class Blockchain {
	private List<Jonction> blockchain;
	
	public Blockchain() {
		this.blockchain = new ArrayList<Jonction>();
	}
	
	public Blockchain(Bloc blocInitial) {
		this();
		Jonction jonctionInitial = new Jonction(blocInitial,0,0);
		this.blockchain.add(jonctionInitial);
	}
	
	public boolean addBlock(Bloc b, int sel) {
		if (this.verif(b) == false) {
			return false;
		}
		if (this.inserable(3, sel, b)) {
			return false;
		}
		Jonction j = new Jonction(b, sel, this.getHashNewNode(b, sel));
		this.blockchain.add(j);
		return true;
	}
	
	public int getHashNewNode(Bloc bloc, int sel) {
		return (new int[] {bloc.hashCode(), sel, this.getLastJonction().getHash()}).hashCode();

	}
	
	public Jonction getLastJonction() {
		return this.blockchain.get(this.blockchain.size() - 1);
	}
	
	public boolean inserable(int difficulte, int sel, Bloc bloc) {
		
		String hash = Integer.toBinaryString(
				this.getHashNewNode(bloc, sel)
				);
		return Integer.parseInt(hash.substring(0, difficulte)) == 0;
	}
	
	
	public boolean verif(Bloc b) {
		
		if (this.blockchain.isEmpty()){
			return true;
		}
		Etat etatAfterTrans = this
				.getLastJonction()
				.getBloc()
				.getEtatFinal()
				.applyTransaction(b.getTransactionEffectuee());
		return etatAfterTrans == b.getEtatFinal();
	}
}
