package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ecouteur  implements ActionListener {


    private ApplicationGraphique contexte;

    public Ecouteur(ApplicationGraphique ag) {
        setContexte(ag);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        contexte.incrémente();
    }

    public void setContexte(ApplicationGraphique contexte) {
        this.contexte = contexte;
    }

    public ApplicationGraphique getContexte() {
        return contexte;
    }
}
