package thread;

import java.util.concurrent.TimeUnit;

public class Compte02 {

	private double solde;
	public  void deposer(double somme) {

		/*
		// pour rendre la methode la moins "atomique" (en fait rapide) possible... et donc rendre les "interruptions" plus frequentes
		try {
			TimeUnit.MILLISECONDS.sleep((int) (Math.random()*10d));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		solde = solde + somme;
		/*
			solde : 300
			thread 1 :
				solde : 300
				300 + 10 (résultat n'est pas encore affecté)


			--> on passe sur thread 2
				solde : 300
				300 + 10
				solde <- 310

			--> on revient sur thread 1
				solde <- 310
				--> conséquence : une opération
		 */
	}

	public double getSolde() {
		return solde;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Compte02 compte = new Compte02();
		final int nbOp = 100;
		final int somme = 10;
		int nbThread = 3;
		Thread[] threads = new Thread[nbThread];

		for(int i = 0; i < nbThread; i++)
			{
			threads[i] = new Thread() {
				public void run() {
					for (int i = 0; i < nbOp; i++) {
						compte.deposer(somme);
					}
				}
			};
			}


		for(int i = 0; i < nbThread; i++)
		{
			threads[i].start();
		}

		/*
		// synchronsitation manuelle des threads
		while (threads[0].isAlive() || threads[1].isAlive() || threads[2].isAlive())
		{
			try {
				System.out.println("=> t1.isAlive : "+threads[0].isAlive() +" ; t2.isAlive : "+ threads[1].isAlive() +" ; t3.isAlive :"+ threads[2].isAlive());
				TimeUnit.MILLISECONDS.sleep(10);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 */

		try {
			for(Thread th : threads)
			{
				// on rejoint chacun les uns apres les autres
				// on rejoint peut-etre des threads finis...
				th.join();
				System.out.println("fin du thread : "+th.getName());
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("fini => t1.isAlive : "+threads[0].isAlive() +" ; t2.isAlive : "+ threads[1].isAlive() +" ; t3.isAlive :"+ threads[2].isAlive());
		System.out.println("au final : "+compte.getSolde()+" , était attendu : "+nbThread*somme*nbOp+"=> t1.isAlive : "+threads[0].isAlive() +" ; t2.isAlive : "+ threads[1].isAlive() +" ; t3.isAlive :"+ threads[2].isAlive());


	}

}
