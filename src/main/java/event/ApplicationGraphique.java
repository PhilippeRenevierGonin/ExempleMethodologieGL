package event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationGraphique {

    public static void main(String [] args) {
        new ApplicationGraphique();
    }


    JFrame fenetre ;
    JButton bouton;
    JLabel texte;
    int cpt = 0;

    public ApplicationGraphique() {
        fenetre = new JFrame("exemple d'événement");
        bouton = new JButton("cliquez sur moi");
        texte = new JLabel("message central");
        texte.setFont(texte.getFont().deriveFont(72f));

        fenetre.add("North", bouton);
        fenetre.add("Center", texte);


        /*
        bouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicSurLeBouton();
            }
        });

         */
        bouton.addActionListener(new Ecouteur(this));

        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.pack();
        fenetre.setLocation(2000, 100);
        fenetre.setVisible(true);
    }


    public void incrémente() {
        cpt++;
        texte.setText("message central "+cpt);
    }

    protected void clicSurLeBouton() {
        incrémente();
    }
}
