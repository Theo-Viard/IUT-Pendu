import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class P_ControleurTemps implements EventHandler<ActionEvent>{
    private Text temps;
    private long duree;
    private long tempsCourant;

    public P_ControleurTemps(Text temps){
        this.duree = 0;
        this.tempsCourant = -1; // le Chrono n'est pas encore lancé
        this.temps = temps;
    }
    @Override
    public void handle(ActionEvent t) {
        // on récupère l'heure en millisecondes
        long heureDuSysteme = System.currentTimeMillis();
        if (this.tempsCourant != -1){ 
            // calcul du tps écoulé depuis la dernière frame
            long tempsEcoule = heureDuSysteme - this.tempsCourant; 
            this.duree += tempsEcoule;
            this.temps.setText(duree/1000 +"");
        }
        this.tempsCourant = heureDuSysteme;
        }
}

