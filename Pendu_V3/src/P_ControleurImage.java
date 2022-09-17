import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

public class P_ControleurImage implements EventHandler<ActionEvent>{
    private ImageView imageView;
    private String imageSuivante;

    public P_ControleurImage(ImageView imageView, String imageSuivante){
        this.imageView = imageView;
        this.imageSuivante = imageSuivante;
    }
    @Override
    public void handle(ActionEvent t) {
        this.imageView.setImage(new Image(imageSuivante));
    }
}
