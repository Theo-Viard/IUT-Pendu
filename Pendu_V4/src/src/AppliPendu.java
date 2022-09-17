package src;

import java.util.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.control.Alert.*;
import javafx.stage.*;
import src.Pendu.*;
import javafx.animation.*;
import javafx.application.*;

public class AppliPendu extends Application{
    private Stage staj;
    private Scene scene;
    private P_Pendu jeu;
    private Button home;
    private Button info;
    private Button settings;
    private ArrayList<String> click;
    private Timeline chrono;

    public static void main(String[] args) {
        launch(AppliPendu.class, args);
    }
    @Override
    public void init(){
        this.click = new ArrayList<>();
        this.jeu = new P_Pendu();
        this.home = new Button();
        this.info = new Button();
        this.settings = new Button();
        this.home.setGraphic(new ImageView(new Image("./img/home.png", 40, 40, false, false)));
        this.info.setGraphic(new ImageView(new Image("./img/info.png",40,40, false, false)));
        this.settings.setGraphic(new ImageView(new Image("./img/parametres.png",40,40, false, false)));
    }
    
    @Override
    public void start(Stage stage) {
        this.staj = stage;
        Pane root = new P_VueMenu(this);
        this.scene = new Scene(root,700,900);
        stage.setScene(scene);
        stage.setTitle("The Pendu");
        stage.show();
    }
    public Button getHome() {
        return this.home;
    }
    public Stage getStage(){
        return this.staj;
    }
    public Button getSettings() {
        return this.settings;
    }
    public Button getInfo() {
        return this.info;
    }
    public Button CreaBut(){
        Button x = new Button();
        x.setOnAction(new P_ControleurClavier(this));
        x.setMinWidth(35);
        x.setMaxWidth(35);
        x.setStyle("-fx-background-radius: 15px;");
        return x;
    }
    public void setRoot(BorderPane root){
        this.scene.setRoot(root);
        this.scene.getWindow().sizeToScene();
    }
    public P_VueMenu getRootMenu(){
        return (P_VueMenu) scene.getRoot();
    }
    public P_VueJeu getRootJeu(){
        return (P_VueJeu) scene.getRoot();
    }
    public ArrayList<String> getTouches(){
        return this.click;
    }
    public P_Pendu getJeu(){
        return this.jeu;
    }
    public void setActu(Image t1, boolean x){
        if(x)
        this.getRootJeu().setAll((new ImageView(t1)), this.getJeu().getEncrypted(),new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
        else
        this.getRootJeu().setAll((new ImageView(t1)), this.getJeu().getEncrypted(),new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
    }
    
    public void jeuPerdu(){
        this.chrono.stop();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Partie Perdue");
        alert.setHeaderText("Vous avez perdu, le mot était : " + this.jeu.getMotMain());
        alert.setContentText("Recommencer une nouvelle partie ?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            this.click = new ArrayList<>();
            this.init();
            Pane root2 = new P_VueMenu(this);
            this.scene.setRoot(root2);
        }
        else{
            System.exit(0);
        }
    }
    public void win(){
        this.chrono.stop();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Partie Gagnée");
        alert.setHeaderText("Vous avez gagné, vous avez pris " + this.getRootJeu().getTimer() + " secondes");
        alert.setContentText("Recommencer une nouvelle partie ?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            this.click = new ArrayList<>();
            this.init();
            Pane root2 = new P_VueMenu(this);
            this.scene.setRoot(root2);
        }
        else{
            System.exit(0);
        }
    }
    public void launchJeu(){
        this.jeu = new P_Pendu();
        this.jeu.setNiveau(this.getRootMenu().getNiveau());
        this.jeu.setMot();
        this.jeu.Encryptage();
    }

    public void setTimeline(Timeline x){
        this.chrono = x;
    }
    public void setJeu(P_Pendu uwu){
        this.jeu = uwu;
    }
}