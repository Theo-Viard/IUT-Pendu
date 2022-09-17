import javafx.event.*;
import javafx.scene.control.Button;

public class ControleurDemineur implements EventHandler<ActionEvent>{
    private AppliPendu appli;
    private D_DemineurGraphique Demi;
    
    public ControleurDemineur(AppliPendu appli){
        this.appli = appli;
    }

    public ControleurDemineur(D_DemineurGraphique dem){
        this.Demi = dem;
    }
    
    @Override
    public void handle(ActionEvent event){
        Button button = (Button) (event.getSource());
        if(button.getText() == "Jeu du démineur"){
        D_DemineurGraphique Dem = new D_DemineurGraphique();
        Dem.init();
        Dem.setMain(this.appli);
        Dem.start(this.appli.getStage()); 
        }
        else{
            this.appli = this.Demi.getMain();
            this.appli.init();
            this.appli.start(this.appli.getStage());
        }
    }
}