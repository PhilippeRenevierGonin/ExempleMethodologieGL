package thread;

import java.util.concurrent.TimeUnit;


public class Compte04 {

	private  double solde;
	private Object synchro = new Object();
	

	public /* synchronized */ void deposer(double somme) {
		// pour rendre la methode la moins "atomique" (en fait rapide) possible... et donc rendre les "interruptions" plus frequentes
		try {
			TimeUnit.MILLISECONDS.sleep((int) (Math.random()*10d));
			// ou alors utiliser Thread.yield( ) ...
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("nom du thread courrant = "+Thread.currentThread().getName());

		// solde = solde + somme;

		synchronized (synchro){
			solde = solde + somme;
		   }
	}

	public double getSolde() {
		return solde;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		long deb = System.currentTimeMillis();
		
		final Compte04 compte = new Compte04();
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
			threads[i].setName("thread "+(i+1));

			}


		for(int i = 0; i < nbThread; i++)
		{
			threads[i].start();
		}


		
		try {
			for(Thread th : threads)
			{
				// on rejoint chacun les uns apres les autres
				// on rejoint peut-etre des threads finis...
				th.join();
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		System.out.println("au final : "+compte.getSolde()+" , était attendu : "+nbThread*somme*nbOp+"=> t1.isAlive : "+threads[0].isAlive() +" ; t2.isAlive : "+ threads[1].isAlive() +" ; t3.isAlive :"+ threads[2].isAlive());
		deb = System.currentTimeMillis() - deb;
		System.out.println("temps mis = "+deb);

		
	}

}
