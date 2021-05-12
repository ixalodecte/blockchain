package blockchain;

import java.util.Arrays;

public final class Etat implements Cloneable {
	private Integer[] individus;

	public Etat(Integer[] individus) {

		this.individus = individus.clone();
	}
	
	public int getSolde(int individu) {
		return this.individus[individu];
	}
	
	private Integer[] getIndividus() {
		return this.individus;
	}
	
    public Etat applyTransaction(Transaction t){
    	Integer[] individus2 = this.individus.clone();
    	individus2[t.getPayeur()] -= t.getSomme();
    	individus2[t.getReceveur()] += t.getSomme();
    	Etat etat2 = new Etat(individus2);
    	
		return etat2;
    	
    }
    
    public boolean hasNegativeValue() {
    	return !Arrays.stream(individus).allMatch(x -> x > 0);
    }

    @Override
    public int hashCode() {
        return this.individus.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
    	System.out.println("okkkk");
    	Etat etat2 = (Etat) o;
    	Integer[] list2 = etat2.getIndividus();
    	for(int i=0;i<individus.length;i++)    //length is the property of the array  
    		System.out.println(individus[i]);    
    	for(int i=0;i<list2.length;i++)    //length is the property of the array  
    		System.out.println(list2[i]);    
    		
    	return Arrays.equals(this.individus, list2);
    }
    
    public Object clone()
    {
        Etat etat2 = new Etat(this.individus.clone());
        return etat2;
    }
    
}
