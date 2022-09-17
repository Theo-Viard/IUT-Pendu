import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurStart implements EventHandler<ActionEvent>{
    
    private AppliPendu appli;
    
    public ControleurStart(AppliPendu appli){
        this.appli = appli;
    }
    
    @Override
    public void handle(ActionEvent event){
        this.appli.CreaGame();
    }
}