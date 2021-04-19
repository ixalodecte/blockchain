package blockchain;

import java.util.LinkedList;
import java.util.List;

class Jonction{
	private Bloc bloc;
	private Integer sel;
	private Integer hash;
	
	public Jonction(Bloc bloc, Integer sel, Integer hash) {
		super();
		this.bloc = bloc;
		this.sel = sel;
		this.hash = hash;
	}

	public Bloc getBloc() {
		return bloc;
	}

	public Integer getSel() {
		return sel;
	}

	public Integer getHash() {
		return hash;
	}
	

}

public class Blockchain {
	private List<Jonction> blockchain;
	public Blockchain() {
		this.blockchain = new LinkedList<Jonction>();
	}
	
	public boolean verif(Etat e, Transaction t) {
		Etat etatAfterTrans = e.applyTransaction(t);
		return etatAfterTrans == e;
	}
}
