import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControleurButtonInfo implements EventHandler<ActionEvent>{
    
    
    public ControleurButtonInfo(AppliPendu appli){
    }
    
    @Override
    public void handle(ActionEvent event){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Explication du jeu");
        alert.setHeaderText("Le jeu du pendu consiste à trouver un mot avant que le bonhomme ne se pende");
        alert.setContentText("Vous avez 10 lettres avant que le jeu ne se termine");
        alert.showAndWait();
    }
}