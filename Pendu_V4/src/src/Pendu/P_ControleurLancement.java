package src.Pendu;
import javafx.event.*;
import src.AppliPendu;

public class P_ControleurLancement implements EventHandler<ActionEvent> {
    private AppliPendu main;

    public P_ControleurLancement(AppliPendu app){
        this.main = app;
    }

    @Override
    public void handle(ActionEvent event){
        P_VueJeu root = new P_VueJeu(this.main);
        this.main.launchJeu();
        root.setEncrypted(this.main.getJeu().getEncrypted());
        root.launch();
        this.main.setRoot(root);
    }
}
