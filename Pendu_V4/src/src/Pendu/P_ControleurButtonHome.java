package src.Pendu;
import java.util.Optional;

import javafx.event.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import src.AppliPendu;

public class P_ControleurButtonHome implements EventHandler<ActionEvent>{
    
    private AppliPendu appli;
    
    public P_ControleurButtonHome(AppliPendu appli){
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
            this.appli.setRoot(new P_VueMenu(this.appli));
        }
    }
}