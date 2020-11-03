package thread;

import java.util.concurrent.TimeUnit;

public class Compte01 {

	private double solde;
	
	
	public  void deposer(double somme) {
		// pour rendre la methode la moins "atomique" (en fait rapide) possible... et donc rendre les "interruptions" plus frequentes
		/*
		try {
			TimeUnit.MILLISECONDS.sleep((int) (Math.random()*10d));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**/
		solde = solde + somme;
	}

	public double getSolde() {
		return solde;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Compte01 compte = new Compte01();
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


		System.out.println("au final : "+compte.getSolde()+" , était attendu : "+nbThread*somme*nbOp+"=> t1.isAlive : "+threads[0].isAlive() +" ; t2.isAlive : "+ threads[1].isAlive() +" ; t3.isAlive :"+ threads[2].isAlive());


	}

}
