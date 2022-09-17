package src.Pendu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import src.Demineur.D_Chronometre;

/**
 * Contrôleur du chronomètre
 */
public class D_ControleurChronometre implements EventHandler<ActionEvent> {
    /**
     * temps enregistré lors du dernier événement
     */
    private long tempsPrec;
    /**
     * temps écoulé depuis le début de la mesure
     */
    private long tempsEcoule;
    /**
     * Vue du chronomètre
     */
    private D_Chronometre chrono;

    /**
     * Constructeur du contrôleur du chronomètre
     * noter que le modèle du chronomètre est tellement simple
     * qu'il est inclus dans le contrôleur
     * @param chrono Vue du chronomètre
     */
    public D_ControleurChronometre (D_Chronometre chrono){
        this.chrono = chrono;
        this.tempsPrec = -1;
        this.tempsEcoule = 0;
    }

    /**
     * Actions à effectuer tous les pas de temps
     * essentiellement mesurer le temps écoulé depuis la dernière mesure
     * et mise à jour de la durée totale
     * @param actionEvent événement Action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        // on récupère l'heure en millisecondes
        long temps = System.currentTimeMillis();
        if (this.tempsPrec != -1){ 
            // calcul du tps écoulé depuis la dernière frame
            long tempsEcoule = temps - this.tempsPrec; 
            this.tempsEcoule += tempsEcoule;
            this.chrono.setTime(this.tempsEcoule);
        }
        this.tempsPrec = temps;
        }
    

    /**
     * Remet la durée à 0
     */
    public void reset(){
        this.tempsEcoule = 0;
        this.tempsPrec = -1;
    }
}
