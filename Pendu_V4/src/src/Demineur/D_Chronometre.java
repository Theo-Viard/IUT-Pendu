package src.Demineur;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;
import src.Pendu.D_ControleurChronometre;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


/**
 * Permet de gérer un Text associé à une Timeline pour afficher un temps écoulé
 */
public class D_Chronometre extends Text{
    /**
     * timeline qui va gérer le temps
     */
    private Timeline timeline;
    /**
     * la fenêtre de temps
     */
    private KeyFrame keyFrame;
    /**
     * le contrôleur associé au chronomètre
     */
    private D_ControleurChronometre actionTemps;

    /**
     * Constructeur permettant de créer le chronomètre
     * avec un text initialisé à "0:0"
     * Ce constructeur créer la Timeline, la KeyFrame et le contrôleur
     */
    public D_Chronometre(){
        this.setText("0:0");
        this.setFont(Font.font("Arial",FontWeight.BOLD,24.0));
        this.actionTemps = new D_ControleurChronometre(this);
        long tps = 100; //temps choisi
        this.keyFrame = new KeyFrame(Duration.millis(tps), this.actionTemps);
        this.timeline = new Timeline(keyFrame);
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Permet au controleur de mettre à jour le text
     * la durée est affichée sous la forme m:s
     * @param tempsMillisec la durée depuis à afficher
     */
    public void setTime(long tempsMillisec){
        long secondes = (tempsMillisec/1000)%60;
        long minutes = ((tempsMillisec/1000)%3600)/60;
        this.setText(""+minutes+":"+secondes);
    }

    /**
     * Permet de démarrer le chronomètre
     */
    public void start(){
        this.timeline.play();
    }

    /**
     * Permet d'arrêter le chronomètre
     */
    public void stop(){
        this.timeline.pause();
    }

    /**
     * Permet de remettre le chronomètre à 0
     */
    public void resetTime(){
        this.actionTemps.reset();
    }
}
