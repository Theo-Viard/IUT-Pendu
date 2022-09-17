import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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

public class FenetreMenu extends BorderPane{
    private AppliPendu main;
    private RadioButton btn1;
    private RadioButton btn2;
    private RadioButton btn3;
    private RadioButton btn4;
    private TextField txtF;


    public FenetreMenu(AppliPendu app){
        super();
        this.main = app;
        this.setTop(borderTop());
        this.setCenter(borderCenter());
    }

    public HBox borderTop(){
        HBox top = new HBox(20);
        VBox titre = new VBox(10);
        Text title = new Text("Jeu du Pendu");
        title.setFont(new Font(35));
        Label credits = new Label("by LSeas");
        titre.getChildren().addAll(title, credits);
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Button tempHome = this.main.getHome();
        tempHome.setDisable(true);
        Button tempSettings = this.main.getSettings();
        tempSettings.setDisable(false);
        top.getChildren().addAll(titre, spacer, tempHome, this.main.getInfo(), tempSettings);
        top.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
        top.setPadding(new Insets(10,10,10,10));
        return top;
    }
    public VBox borderCenter(){
        VBox box = new VBox(20);
        Button game = new Button("Lancer le jeu");
        game.setOnAction(new ControleurStart(main));
        ToggleGroup group = new ToggleGroup();
        this.btn1 = new RadioButton("Facile");
        this.btn1.setToggleGroup(group);
        this.btn1.setSelected(true);
        this.btn2 = new RadioButton("Medium");
        this.btn2.setToggleGroup(group);
        this.btn3 = new RadioButton("Expert");
        this.btn3.setToggleGroup(group);
        this.btn4 = new RadioButton("Personalisé");
        this.btn4.setToggleGroup(group);
        this.txtF = new TextField();
        this.txtF.setPromptText("Un nombre entre 3 et 10");
        box.getChildren().addAll(game, this.btn1, this.btn2, this.btn3, this.btn4, this.txtF);
        return box;
    }
    public String getNiveau(){
        if(this.btn1.isSelected()){
            return "0";
        }
        if(this.btn2.isSelected()){
            return "1";
        }
        if(this.btn3.isSelected()){
            return "2";
        }
        return this.txtF.getText();
    }
}