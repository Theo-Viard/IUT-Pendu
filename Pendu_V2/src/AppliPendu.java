import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AppliPendu extends Application{
    private Scene scene;
    private Button launch;
    private Button home;
    private Button info;
    private Button settings;
    private Image penduImg;
    private ModelePendu jeu;
    private ArrayList<String> click;
    private int time;

    public static void main(String[] args) {
        launch(AppliPendu.class, args);
    }
    @Override
    public void init(){
        this.launch = new Button();
        this.home = new Button();
        this.home.setOnAction(new ControleurButtonHome(this));
        this.home.setGraphic(new ImageView(new Image("./img/home.png",40,40,false,false)));
        this.info = new Button();
        this.info.setOnAction(new ControleurButtonInfo(this));
        this.info.setGraphic(new ImageView(new Image("./img/info.png",40,40,false,false)));
        this.settings = new Button();
        this.settings.setGraphic(new ImageView(new Image("./img/parametres.png",40,40,false,false)));
        this.penduImg = new Image("img/pendu_1.png", 300, 500, false, false);
        this.click = new ArrayList<>();
    }
    
    @Override
    public void start(Stage stage) {
        Pane root = new FenetreMenu(this);
        this.scene = new Scene(root,700,900);
        stage.setScene(scene);
        stage.setTitle("The Pendu");
        stage.show();
    }
    public void addSec(){
        this.time +=1;
    }
    public String getTime(){
        return String.valueOf(this.time);
    }
    public Button getHome(){
        return this.home;
    }
    public Button getInfo(){
        return this.info;
    }
    public Button getSettings(){
        return this.settings;
    }
    public Button CreaBut(){
        Button x = new Button();
        x.setOnAction(new ControleurClavier(this));
        return x;
    }
    public void CreaGame(){
        this.jeu = new ModelePendu(chooselvl(getRootMenu().getNiveau()));
        this.jeu.getMot();
        this.jeu.Encryptage();
        this.reload();
    }
    private static String chooselvl(String niv){
        if(niv.equals("0")){
            return String.valueOf((int) Math.floor(Math.random() * (6 - 3 + 1) + 3));
        }
        else if(niv.equals("1")){
            return String.valueOf((int) Math.floor(Math.random() * (10 - 6 + 1) + 6));
        }
        else if(niv.equals("2")){
            return "max";
        }
        else{
            return niv;
        }
    }
    public ModelePendu getJeu(){
        return this.jeu;
    }
    public void setImage(Image img){
        this.penduImg = img;
    }
    public FenetreMenu getRootMenu(){
        return (FenetreMenu) scene.getRoot();
    }
    public FenetreJeu getRootJeu(){
        return (FenetreJeu) scene.getRoot();
    }
    public void setMenu(){
        Pane root = new FenetreMenu(this);
        this.scene.setRoot(root);
    }
    public void reload(){
        if(this.jeu.getVies() != 0){
            this.getRootJeu().getTimer().cancel();
            Pane root = new FenetreJeu(this, this.launch, this.penduImg);
            this.scene.setRoot(root);
        }
        else{
            Pane root = new FenetreJeu(this, this.launch, new Image("img/pendu_10.png", 300, 500, false, false));
            this.scene.setRoot(root);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Partie Perdue");
            alert.setHeaderText("Vous avez perdu, le mot était : " + this.jeu.getMotFinal());
            alert.setContentText("Recommencer une nouvelle partie ?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get() == ButtonType.OK){
                this.click = new ArrayList<>();
                this.init();
                Pane root2 = new FenetreMenu(this);
                this.scene.setRoot(root2);
            }
            else{
                System.exit(0);
            }
        }
        if(this.jeu.getEncrypt().equals(this.jeu.getMotFinal())){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Partie Gagnée !");
            alert.setHeaderText("Vous avez pris : " + this.time + " secondes");
            alert.setContentText("Recommencer une nouvelle partie ?");
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get() == ButtonType.OK){
                this.click = new ArrayList<>();
                this.time = 0;
                this.getRootJeu().getTimer().cancel();
                this.init();
                Pane root2 = new FenetreMenu(this);
                this.scene.setRoot(root2);
            }
            else{
                System.exit(0);
            }
        }
    }
    public void ajt(Button touche){
        this.click.add(touche.getText());
    }
    public ArrayList<String> getTouches(){
        return this.click;
    }
}
