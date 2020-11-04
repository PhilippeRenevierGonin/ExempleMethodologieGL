package threadevent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fenetre implements ActionListener{
	JLabel txt ;
	int compteur = 0;
	JButton pause, stop, relancer;
	private Compteur threadCompteur;
	
	public Fenetre() {
		JFrame f =  new JFrame("fenetre");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		txt = new JLabel(""+compteur);
		txt.setFont(txt.getFont().deriveFont(72f));
		f.getContentPane().add("North", txt);
		
		pause = new JButton("pause");
		pause.setFont(txt.getFont());
		pause.addActionListener(this);
		f.getContentPane().add("West", pause);

		stop = new JButton("stop");
		stop.setFont(txt.getFont());
		stop.addActionListener(this);
		f.getContentPane().add("East", stop);
		
		relancer = new JButton("relancer");
		relancer.setFont(txt.getFont());
		relancer.addActionListener(this);
		f.getContentPane().add("South", relancer);
		
		f.pack();
		f.setLocation(2000,500);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(pause.getText())) {
			if (threadCompteur != null) {
				if (! threadCompteur.isPause()) {
					threadCompteur.setPause(true);
				}
				else {
					threadCompteur.arreterLaPause();
				}
			}
		}
		else if (e.getActionCommand().equals(stop.getText())) {
			// pour arreter : threadCompteur.setRunning(false);
			if (threadCompteur != null) { threadCompteur.interruption(); }
		}
		else if (e.getActionCommand().equals(relancer.getText())) {
			// pour arreter : threadCompteur.setRunning(false);
			compteur = 0;
			txt.setText("0");
			if (threadCompteur != null) { threadCompteur.relancer(); }
		}	
	}

	public void incrementeCompteur() {
		compteur++;
		txt.setText(""+compteur);
	}
	
	public void setCompeur(Compteur t) {
		this.threadCompteur = t;
	}
	

	public static void main(String[] args) {
		Fenetre f = new Fenetre();
		Compteur t = new Compteur(f);
		f.setCompeur(t);
	}
}
