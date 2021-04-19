package blockchain;

public class Transaction {
	private int somme;
	private int payeur;
	private int receveur;
	
	public Transaction(int somme, int payeur, int receveur) {
		this.somme = somme;
		this.payeur = payeur;
		this.receveur = receveur;
	}
	
    public int getSomme() {
		return somme;
	}

	public int getPayeur() {
		return payeur;
	}

	public int getReceveur() {
		return receveur;
	}

	@Override
    public int hashCode() {
		// Le hashCode d'un int est le int lui meme
        return this.somme + this.payeur + this.receveur;
    }
}
