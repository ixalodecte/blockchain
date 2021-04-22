package blockchain;

public final class Etat implements Cloneable{
	private int[] individus;

	public Etat(int[] individus) {
		this.individus = individus.clone();
	}
	
	public int getSolde(int individu) {
		return this.individus[individu];
	}
	
	private int[] getIndividus() {
		return this.individus;
	}
	
    public Etat applyTransaction(Transaction t){
    	int[] individus2 = this.individus.clone();
    	individus2[t.getPayeur()] -= t.getSomme();
    	individus2[t.getReceveur()] += t.getSomme();
    	Etat etat2 = new Etat(individus2);
    	
		return etat2;
    	
    }

    @Override
    public int hashCode() {
        return this.individus.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
    	Etat etat2 = (Etat) o;
    	int[] list2 = etat2.getIndividus();
    	return this.individus.equals(list2);
    }
    
    public Object clone()
    {
        Etat etat2 = new Etat(this.individus.clone());
        return etat2;
    }
    
}
