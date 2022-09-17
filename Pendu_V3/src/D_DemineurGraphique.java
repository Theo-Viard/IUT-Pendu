import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;;
public class D_DemineurGraphique extends Application {

    private int difficulte=1; //mode par défaut initialisé à FACILE
    private D_Plateau lePlateau;
    private Pane infos;
    private GridPane grille;
    private BorderPane lafenetre;
    private D_Chronometre chrono;
    private AppliPendu main;

    // Getters
    public D_Chronometre getChrono(){return this.chrono;}

    /**
     * Le pré-lancement de l'interface du démineur
     */
    @Override
    public void init(){this.chrono = new D_Chronometre();}

    /**
     * Création de la fenêtre de jeu
     * @return (VBox) La fênetre de jeu.
     */
    public VBox fenetre_jeu(){
        VBox hbox = new VBox(20);
        hbox.setAlignment(Pos.TOP_CENTER);
        this.grille = new GridPane();
        hbox.setSpacing(10);
        this.grille.setHgap(1);
        this.grille.setVgap(1);     
        for (int i = 0; i<this.lePlateau.getNbTotalLignes(); i++){
            for (int j=0; j<this.lePlateau.getNbTotalColonnes(); j++){
                D_Case laCase = this.lePlateau.getPlateau().get(i).get(j);
                D_Bouton b = new D_Bouton(laCase);

                b.setOnMouseClicked(new D_ControleurBouton(b, laCase, this, this.lePlateau));
                grille.add(b, i, j);
            }
        }
        this.infos = new VBox();
        HBox stats = new HBox(20);
        Button btn = new Button("Jeu du Pendu");
        btn.setOnAction(new ControleurDemineur(this));
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        stats.getChildren().addAll(this.chrono,spacer,btn);
        hbox.getChildren().addAll(stats,grille, infos);
        this.maj_des_infos();
        return hbox;
    }

    /**
     * Placement des interfaces de jeu.
     */
    public void mode_jeu(){
        this.lafenetre.setCenter(fenetre_jeu());
        this.lafenetre.getScene().getWindow().sizeToScene();
    }

    /**
     * Ajout de la fenêtre de chois de difficulté
     */
    public void mode_choix_difficultes(){
        this.lafenetre.setCenter(fenetre_difficultes());
    }

    public AppliPendu getMain(){
        return this.main;
    }
    public void setMain(AppliPendu x){this.main = x;}
    /**
     * Lancement de l'interface de jeu.
     */
    @Override
    public void start(Stage stage) {
        
        this.lafenetre = fenetre_difficultes();
        Scene scene = new Scene(this.lafenetre);
        stage.setTitle("Demineur");
        stage.setScene(scene);
        this.lafenetre.getScene().getWindow().sizeToScene();
        stage.show();
        }
    
    public void setDifficulte(int difficulte){
        this.difficulte = difficulte;
    }
    public void lancePartie(){
        if (this.lePlateau==null){
            this.lePlateau = new D_Plateau(this.difficulte);
            this.chrono.start();
        }
        else{
        this.lePlateau.init();
        this.chrono.resetTime();
        this.chrono.start();
        }
        this.mode_jeu();
    }

    /**
     * Supprime les cases voisines d'une case cliquée si elles n'ont aucune bombe voisines.
     */
    public void EmptyProxi(){
        Boolean edit = true;
        while(edit){
            edit=false;
            for(int l=0;l<this.lePlateau.getNbTotalLignes(); l++){
                for(int c=0;c<this.lePlateau.getNbTotalColonnes(); c++){
                    if(this.lePlateau.getCase(l, c).getAffichage().equals(" ")){
                        for(D_Case lacase :this.lePlateau.getCase(l, c).getCasesVoisines()){
                            if(!(lacase.estRevelee())){
                                if(lacase.estMarquee()){
                                    lacase.demarquer();
                                }
                                lacase.reveler();
                                edit = true;
                            }
                        }
                    }
                }
            }
        }
        maj_de_la_grille();
    }

    /**
     * Mets la grille a jour pour chaque bouton.
     */
    public void maj_de_la_grille(){
        for (Node b : this.grille.getChildren()){
            D_Bouton bb = (D_Bouton) b;
            bb.maj();
        }
    }

    /**
     * Réactive chaque bouton de la grille.
     */
    public void reactivation_des_boutons(){
        for (Node b : this.grille.getChildren()){
            D_Bouton bb = (D_Bouton) b;
            bb.reactive();
        }
    }

    /**
     * Création de la fenetre difficulté
     * @return (BorderPane) La fenêtre difficulté
     */
    public BorderPane fenetre_difficultes(){
        BorderPane bp = new BorderPane();
        HBox hb = new HBox();
        hb.setSpacing(15);
        ToggleGroup toggle = new ToggleGroup();
        RadioButton rbf = new RadioButton("Facile");
        RadioButton rbn = new RadioButton("Normal");
        RadioButton rbd = new RadioButton("Difficile");
        RadioButton rbe = new RadioButton("Expert");
        D_ControleurDifficulte cd = new D_ControleurDifficulte(this);
        
        rbf.setOnAction(cd);
        rbn.setOnAction(cd);
        rbd.setOnAction(cd);
        rbe.setOnAction(cd);

        rbf.setToggleGroup(toggle);
        rbn.setToggleGroup(toggle);
        rbd.setToggleGroup(toggle);
        rbe.setToggleGroup(toggle);

        hb.getChildren().addAll(rbf,rbn,rbd,rbe);

        TitledPane tp = new TitledPane("Choisissez un niveau de difficulté",hb);
        tp.setExpanded(true); //le titledPane reste ouvert
        tp.setCollapsible(false); //le titledPane ne pourra pas être fermé
        bp.setCenter(tp);
        return bp;
    }

    /**
     * Désactive tous les boutons de la grille
     */
    public void desactiver(){
        for (Node b : this.grille.getChildren()){
            b.setDisable(true);
        }
    }

    /**
     * Quitte le programme.
     */
    public void exit(){Platform.exit();}
    
    /**
     * Mets a jour les informations textuelles du jeu
     */
    public void maj_des_infos(){
        this.infos.getChildren().clear();
        Label label1 = new Label("Nombres de bombes : " + this.lePlateau.getNbTotalBombes());
        Label label2 = new Label("Nombres de cases marquées : " + this.lePlateau.getNbCasesMarquees());
        Label label3 = new Label("Nombres de cases découvertes : " + this.lePlateau.getNbCasesDecouvertes());
        this.infos.getChildren().addAll(label1, label2, label3);
    }
    /**
     * Lance l'application du jeu du démineur
     */
    public static void main(String args[]){
        Application.launch(args);
    }
}
