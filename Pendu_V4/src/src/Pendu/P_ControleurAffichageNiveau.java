package src.Pendu;
import javafx.event.*;
import javafx.scene.control.RadioButton;
import src.AppliPendu;

public class P_ControleurAffichageNiveau implements EventHandler<ActionEvent>{
    
    private AppliPendu appli;
    
    public P_ControleurAffichageNiveau(AppliPendu appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        RadioButton button = (RadioButton) (event.getSource());
        if(button.getText() == "Personalisé"){
            this.appli.getRootMenu().getComboBoxLevels().setVisible(true);
        }
        else{
            this.appli.getRootMenu().getComboBoxLevels().setVisible(false);
        }
    }
}