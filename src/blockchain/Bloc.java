package blockchain;

public class Bloc {
	private Etat etatFinal;
	private Transaction transactionEffectuee;
	
	public Bloc(Etat etatFinal, Transaction transactionEffectuee) {
		this.etatFinal = etatFinal;
		this.transactionEffectuee = transactionEffectuee;
	}

	public Etat getEtatFinal() {
		return etatFinal;
	}

	public Transaction getTransactionEffectuee() {
		return transactionEffectuee;
	}
}
