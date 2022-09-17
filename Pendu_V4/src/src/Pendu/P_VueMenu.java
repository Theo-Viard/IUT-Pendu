package src.Pendu;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import src.*;

public class P_VueMenu extends BorderPane {
    private AppliPendu main;
    private RadioButton btn1;
    private RadioButton btn2;
    private RadioButton btn3;
    private RadioButton btn4;
    private ComboBox<String> perso;

    public P_VueMenu(AppliPendu app){
       super();
       this.main = app;
       this.setTop(BorderTop());
       this.setCenter(borderCenter());
       
    }
    
    public HBox BorderTop(){
        HBox box = new HBox(10);
        VBox titre = new VBox(10);
        Text title = new Text("Jeu du Pendu");
        Label credits = new Label("by Théo Viard 1A2B");
        title.setFont(new Font(35));
        titre.getChildren().addAll(title, credits);
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        // Boutons d'action
        Button Home = this.main.getHome();
        Button Settings = this.main.getSettings();
        Button Infos = this.main.getInfo();
        Button Demineur = new Button("Jeu du démineur");
        Demineur.setOnAction(new ControleurDemineur(this.main));
        Infos.setOnAction(new P_ControleurButtonInfo(this.main));
        Home.setDisable(true);
        Settings.setDisable(false);
        box.getChildren().addAll(titre, spacer, Home, Infos, Settings, Demineur);
        box.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
        box.setPadding(new Insets(10,10,10,10));
        return box;
    }

    public VBox borderCenter(){
        VBox box = new VBox(20);
        Button game = new Button("Lancer le jeu");
        game.isFocused();
        game.setOnAction(new P_ControleurLancement(this.main));
        TitledPane group = new TitledPane();
        group.setText("Choix de la difficulté");
        VBox groupTemp = new VBox();
        ToggleGroup grp = new ToggleGroup();
        this.btn1 = new RadioButton("Facile");
        this.btn1.setSelected(true);
        this.btn2 = new RadioButton("Medium");
        this.btn3 = new RadioButton("Expert");
        this.btn4 = new RadioButton("Personalisé");
        this.perso = new ComboBox<>();
        this.perso.getItems().addAll("4","5","6","7","8","9","10","11","12");
        this.perso.setVisible(false);
        this.btn1.setOnAction(new P_ControleurAffichageNiveau(this.main));
        this.btn2.setOnAction(new P_ControleurAffichageNiveau(this.main));
        this.btn3.setOnAction(new P_ControleurAffichageNiveau(this.main));
        this.btn4.setOnAction(new P_ControleurAffichageNiveau(this.main));
        this.btn1.setToggleGroup(grp);
        this.btn2.setToggleGroup(grp);
        this.btn3.setToggleGroup(grp);
        this.btn4.setToggleGroup(grp);
        groupTemp.getChildren().addAll(this.btn1, this.btn2, this.btn3, this.btn4, this.perso, game);
        group.setCollapsible(false);
        group.setContent(groupTemp);
        box.getChildren().addAll(group);
        return box;
    }
    public ComboBox<String> getComboBoxLevels(){
        return this.perso;
    }
    public int getNiveau(){
        if(this.btn1.isSelected()){
            return 0;
        }
        if(this.btn2.isSelected()){
            return 1;
        }
        if(this.btn3.isSelected()){
            return 2;
        }
        try{
        return Integer.valueOf(this.perso.getValue());
        }
        catch(NumberFormatException ex){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("ATTENTION");
            alert.setHeaderText("Vous n'avez pas sélectionné de niveau");
            alert.setContentText("Le niveau Facile sera sélectionné par défaut");
            alert.showAndWait();
        }
        return 0;
    }
}
