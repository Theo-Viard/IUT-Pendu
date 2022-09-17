import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
public class D_ControleurDifficulte implements EventHandler<ActionEvent> {
    private D_DemineurGraphique demineur;

    /**
     * Constructeur de la classe ControleurDifficulte
     * @param vue La vue du jeu du démineur
     */
    public D_ControleurDifficulte(D_DemineurGraphique vue){
        this.demineur = vue;
    }

    /**
     * En cas de choix de la difficulté par un clic sur le radioButton
     */
    @Override
    public void handle(ActionEvent event){
        RadioButton rb = (RadioButton) event.getTarget();
        switch(rb.getText()){
            case "Facile":
            this.demineur.setDifficulte(D_Plateau.FACILE);
            this.demineur.lancePartie();
            break;
            case "Normal":
            this.demineur.setDifficulte(D_Plateau.NORMAL);
            this.demineur.lancePartie();
            break;
            case "Difficile":
            this.demineur.setDifficulte(D_Plateau.DIFFICILE);
            this.demineur.lancePartie();
            break;
            case "Expert":
            this.demineur.setDifficulte(D_Plateau.EXPERT);
            this.demineur.lancePartie();
            break;
        }
    }

}
