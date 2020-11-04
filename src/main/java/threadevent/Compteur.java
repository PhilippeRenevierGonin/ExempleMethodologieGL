package threadevent;

import java.util.concurrent.TimeUnit;

public class Compteur implements Runnable{
	private Fenetre parent;
	private boolean running = true;
	private boolean pause;
	private Thread t;


	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}


	public boolean isPause() {
		return pause;
	}


	public void setPause(boolean pause) {
		this.pause = pause;
	}


	private Object unObj = new Object();


	public Compteur(Fenetre f) {
		this.parent = f;
		relancer();
	}


	@Override
	public void run() {
			while(running) {
				
				try {
					// System.out.println("---------------- test pause !!! --------------------");
					testPause();
					
					// System.out.println("---------------- incrementeCompteur !!! --------------------");
					parent.incrementeCompteur();
					
					// System.out.println("---------------- sleep !!! --------------------");
					TimeUnit.MILLISECONDS.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					running = false;
				}

			}
		System.out.println("*********** RUN FINI ***********");
	}


	public void testPause() throws InterruptedException {
		System.out.println("testPause "+pause+ " / "+Thread.currentThread().getName());
				synchronized (unObj) {
					while (isPause()) { unObj.wait(); }
				}
		System.out.println("testPause fini");
	}

	public void arreterLaPause() {
		System.out.println("arreterLaPause "+Thread.currentThread().getName());
		synchronized (unObj) {
			setPause(false);
			unObj.notify();
		}
		System.out.println("arreterLaPause fini");
	}


	public void interruption() {
		running = false;
	}


	public void relancer() {
		if ((t==null) || (! t.isAlive())) {
			running = true;
			pause = false;
			t = new Thread(this);
			t.setName("thread Compteur");
			t.start();
		}
	}

}
