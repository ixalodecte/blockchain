package blockchain;

public class Transaction {
	private Integer somme;
	private Integer payeur;
	private Integer receveur;
	
	public Transaction(int somme, int payeur, int receveur) {
		this.somme = somme;
		this.payeur = payeur;
		this.receveur = receveur;
	}
	
    public Integer getSomme() {
		return somme;
	}

	public Integer getPayeur() {
		return payeur;
	}

	public Integer getReceveur() {
		return receveur;
	}

	@Override
    public int hashCode() {
        return this.somme.hashCode() + this.payeur.hashCode() + this.receveur.hashCode();
    }
}
