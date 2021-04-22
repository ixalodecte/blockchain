package blockchain;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] tab_init = {4,5,8};
		Etat etat_init = new Etat(tab_init);
		Blockchain blockchain = new Blockchain(etat_init);
		Transaction t = new Transaction(4,1,2);
		Etat etat_after_trans = etat_init.applyTransaction(t);
		System.out.println(etat_after_trans.getSolde(0));
		System.out.println(etat_after_trans.getSolde(1));
		System.out.println(etat_after_trans.getSolde(2));

	}

}
