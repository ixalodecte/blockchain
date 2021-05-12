package blockchain;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		Integer[] tab = new Integer[] {5,4,6};
		Etat e = new Etat(tab);
		Blockchain b = new Blockchain(e);
		Mineur mineur = new Mineur(b,3,6000, "127.0.0.1", 6500, 10, 8);
		mineur.manageRequest();

	}

}
