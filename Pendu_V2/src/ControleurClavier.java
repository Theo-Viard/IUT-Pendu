import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class ControleurClavier implements EventHandler<ActionEvent>{
    private AppliPendu appli;
    
    public ControleurClavier(AppliPendu appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        Button button = (Button) (event.getSource());
        this.appli.getJeu().verification(button.getText());
        this.appli.setImage(new Image("img/pendu_" + String.valueOf(10 - this.appli.getJeu().getVies()) + ".png",300, 500, false, false));
        this.appli.ajt(button);
        this.appli.reload();
    }
}
