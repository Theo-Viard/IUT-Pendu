import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.Node;

import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class FenetreJeu extends BorderPane{
    private AppliPendu main;
    private String enc;
    private Timer timer;
    private String time;

    public FenetreJeu(AppliPendu app, Button switchB, Image avanCPendu){
        this.main = app;
        this.time = "";
        this.timer = new Timer();
        this.timer.schedule(new TimerTask(){
            @Override
            public void run(){
                addSec();
            }
        },0,1000);
        this.enc = this.main.getJeu().getEncrypt();
        this.setTop(borderTop());
        this.setLeft(borderCenter(avanCPendu));
        this.setRight(borderRight());
        this.setBottom(borderBottom());
    }
    public void addSec(){
        this.main.addSec();
        this.time = this.main.getTime();
    }
    public Timer getTimer(){
        return this.timer;
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
        Button temp = this.main.getSettings();
        temp.setDisable(true);
        Button tempHome = this.main.getHome();
        tempHome.setDisable(false);
        top.getChildren().addAll(titre, spacer, tempHome, this.main.getInfo(), temp);
        top.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
        top.setPadding(new Insets(10,10,10,10));
        return top;
    }

    public VBox borderCenter(Image avanceePendu){
        VBox box = new VBox(20);
        Text mot = new Text(this.enc);
        mot.setFont(new Font(35));
        ImageView pendu = new ImageView();
        pendu.setImage(avanceePendu);
        ProgressBar pgr = new ProgressBar();
        box.getChildren().addAll(mot,pendu,pgr);
        box.setAlignment(Pos.TOP_CENTER);
        return box;
    }
    public VBox borderRight(){
        VBox box = new VBox(20);
        Label niveau = new Label("Niveau Difficile");
        Text chrono = new Text(String.valueOf(this.time));
        Button newword = new Button("Nouveau Mot");
        box.getChildren().addAll(niveau,chrono,newword);
        return box;
    }
    public VBox borderBottom(){
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

        for(int ti=0; ti<box.getChildren().size();++ti){
            Node enfants1 = box.getChildren().get(ti);
            HBox menu = (HBox) enfants1;
            for(int tu=0;tu<menu.getChildren().size();++tu){
                Node btn = menu.getChildren().get(tu);
                if(this.main.getTouches().contains(((Button) btn).getText())){
                    btn.setDisable(true);
                }
            }
        }
        return box;
    }
}
