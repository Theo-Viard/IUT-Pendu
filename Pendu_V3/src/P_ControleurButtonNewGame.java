import javafx.event.*;
import javafx.scene.control.*;

import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

public class P_ControleurButtonNewGame implements EventHandler<ActionEvent>{
    
    private AppliPendu appli;
    
    public P_ControleurButtonNewGame(AppliPendu appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("ATTENTION");
        alert.setHeaderText("Vous allez quitter une partie en cours");
        alert.setContentText("Voulez vous vraiment quitter la partie ?");
        Optional<ButtonType> option = alert.showAndWait();

        if(option.get() == ButtonType.OK){
            int nivTemp = this.appli.getJeu().getMotNiveau();
            this.appli.setJeu(new P_Pendu());
            this.appli.getJeu().setNiveau(nivTemp);
            this.appli.getJeu().setMot();
            this.appli.getJeu().Encryptage();
            this.appli.setRoot(new P_VueJeu(this.appli));
            this.appli.getRootJeu().launch();
        }
    }
}