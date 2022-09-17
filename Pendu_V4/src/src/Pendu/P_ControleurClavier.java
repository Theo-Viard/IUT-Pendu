package src.Pendu;
import javafx.event.*;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import src.AppliPendu;

public class P_ControleurClavier implements EventHandler<ActionEvent>{
    private AppliPendu appli;
    
    public P_ControleurClavier(AppliPendu appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        Button button = (Button) (event.getSource());
        int vi = this.appli.getJeu().getVies();
        this.appli.getJeu().verification(button.getText());
        if(this.appli.getJeu().getVies() != vi)
        this.appli.setActu(new Image("img/pendu_" + String.valueOf(10 - this.appli.getJeu().getVies()) + ".png",300, 500, false, false), false);
        else
        this.appli.setActu(new Image("img/pendu_" + String.valueOf(10 - this.appli.getJeu().getVies()) + ".png",300, 500, false, false), true);
        if(this.appli.getJeu().getVies() == 0){
            this.appli.jeuPerdu();
        }
        else if(!(this.appli.getJeu().getEncrypted().contains("*"))){
            this.appli.win();
        }
        button.setDisable(true);
    }
}
