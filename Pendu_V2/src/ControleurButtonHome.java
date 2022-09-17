
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ControleurButtonHome implements EventHandler<ActionEvent>{
    
    private AppliPendu appli;
    
    public ControleurButtonHome(AppliPendu appli){
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
            this.appli.setMenu();
        }
    }
}