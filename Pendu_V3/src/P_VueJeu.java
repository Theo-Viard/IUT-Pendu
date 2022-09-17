import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.Duration;
import javafx.scene.paint.Color;

public class P_VueJeu extends BorderPane{

    private AppliPendu main;
    private ImageView img;
    private Text motEncrypt;
    private Text temps;
    private VBox clavier;
    private Border colorB;

    public P_VueJeu(AppliPendu appli){
        super();
        this.temps = new Text("0");
        this.main = appli;
        this.colorB = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5)));
        this.img = new ImageView(new Image("./img/pendu_0.png",300,500,false,false)); // Impossible d'acceder aux images hors du src.
        this.motEncrypt = new Text(this.main.getJeu().getEncrypted());
    }
    public void launch(){
        this.setTop(BorderTop());
        this.clavier = BorderBottom();
        this.setCenter(BorderCenter());
        this.setRight(BorderRight());
        this.creerTimeline();
    }

    public void creerTimeline(){
        P_ControleurTemps controleur = new P_ControleurTemps(this.temps);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), controleur);
        Timeline timeline =new Timeline(keyFrame);
        this.main.setTimeline(timeline);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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
        Home.setOnAction(new P_ControleurButtonHome(this.main));
        Infos.setOnAction(new P_ControleurButtonInfo(this.main));
        Home.setDisable(false);
        Settings.setDisable(true);
        box.getChildren().addAll(titre, spacer, Home, Infos, Settings);
        box.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
        box.setPadding(new Insets(10,10,10,10));
        return box;
    }
    public VBox BorderCenter(){
        VBox box = new VBox(20);
        VBox bannerxd = new VBox();
        box.setAlignment(Pos.CENTER);
        this.motEncrypt.setFont(new Font(35));
        bannerxd.getChildren().add(this.img);
        bannerxd.setBorder(this.colorB);
        bannerxd.setAlignment(Pos.CENTER);
        box.getChildren().addAll(this.motEncrypt, bannerxd, this.clavier);
        return box;
    }
    public VBox BorderRight(){
        VBox box = new VBox(20);
        HBox timer = new HBox();
        Text difficulte = new Text(this.main.getJeu().getDifficulte());
        Button newWord = new Button("Nouveau Mot");
        Label tempsR = new Label(" Secondes écoulées");
        timer.getChildren().addAll(this.temps, tempsR);
        newWord.setOnAction(new P_ControleurButtonNewGame(this.main));
        box.getChildren().addAll(difficulte, timer, newWord);
        return box;
    }
    public VBox BorderBottom(){
        VBox box = new VBox(20);
        
        HBox l1 = new HBox(20);
        Button a = this.main.CreaBut();
        a.setText("A");
        Button b = this.main.CreaBut();
        b.setText("B");
        Button c = this.main.CreaBut();
        c.setText("C");
        Button d = this.main.CreaBut();
        d.setText("D");
        Button e = this.main.CreaBut();
        e.setText("E");
        Button f = this.main.CreaBut();
        f.setText("F");
        Button g = this.main.CreaBut();
        g.setText("G");
        Button h = this.main.CreaBut();
        h.setText("H");
        l1.getChildren().addAll(a,b,c,d,e,f,g,h);

        HBox l2 = new HBox(20);
        Button i = this.main.CreaBut();
        i.setText("I");
        Button j = this.main.CreaBut();
        j.setText("J");
        Button k = this.main.CreaBut();
        k.setText("K");
        Button l = this.main.CreaBut();
        l.setText("L");
        Button m = this.main.CreaBut();
        m.setText("M");
        Button n = this.main.CreaBut();
        n.setText("N");
        Button o = this.main.CreaBut();
        o.setText("O");
        Button p = this.main.CreaBut();
        p.setText("P");
        
        l2.getChildren().addAll(i,j,k,l,m,n,o,p);

        HBox l3 = new HBox(20);
        Button q = this.main.CreaBut();
        q.setText("Q");
        Button r = this.main.CreaBut();
        r.setText("R");
        Button s = this.main.CreaBut();
        s.setText("S");
        Button t = this.main.CreaBut();
        t.setText("T");
        Button u = this.main.CreaBut();
        u.setText("U");
        Button v = this.main.CreaBut();
        v.setText("V");
        Button w = this.main.CreaBut();
        w.setText("W");
        Button x = this.main.CreaBut();
        x.setText("X");
        l3.getChildren().addAll(q,r,s,t,u,v,w,x);

        HBox l4 = new HBox(20);
        Button y = this.main.CreaBut();
        y.setText("Y");
        Button z = this.main.CreaBut();
        z.setText("Z");
        l4.getChildren().addAll(y,z);
        box.getChildren().addAll(l1,l2,l3,l4);
        l1.setAlignment(Pos.BOTTOM_CENTER);
        l2.setAlignment(Pos.BOTTOM_CENTER);
        l3.setAlignment(Pos.BOTTOM_CENTER);
        l4.setAlignment(Pos.BOTTOM_CENTER);

        return box;
    }
    public void setAll(ImageView t1, String motEnc, Border x){
        this.img = t1;
        this.setEncrypted(motEnc);
        this.colorB = x;
        this.setCenter(BorderCenter());
    }
    public void setEncrypted(String t1){
        this.motEncrypt = new Text(t1);
    }
    public String getTimer(){
        return this.temps.getText();
    }
}
