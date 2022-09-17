package src.Demineur;
import javafx.scene.control.*; 
import javafx.scene.image.*;

public class D_Bouton extends Button{
    
    private D_Case laCase;

    /**
     * Constructeur de la classe Bouton
     * @param laCase Une case assignée a ce bouton
     */
    public D_Bouton(D_Case laCase){
        super();
        this.setPrefWidth(100);
        this.setPrefHeight(100);
        this.laCase = laCase;
        try{
        if (this.laCase.estRevelee() && this.laCase.estBombe())
            this.ajouteImage("./img/bombe.jpg");
        else if (laCase.estRevelee() && laCase.estBombe())
            this.ajouteImage("./img/bombe.jpg");
        }
        catch(Exception e){
            this.setText(laCase.getAffichage());
        }
    }

    /**
     * Ajoute une image au bouton
     * @param fichierImage Lien vers l'image a ajouter
     */
    public void ajouteImage(String fichierImage){
        try{
                Image image = new Image(fichierImage);
                ImageView iv = new ImageView(image);
                iv.setFitWidth(60);
                iv.setPreserveRatio(true);
                this.setText("");
                this.setGraphic(iv);
            }
        catch(Exception e){
            this.setText(laCase.getAffichage());
        }
    }

    /**
     * Enlève l'image assignée a un bouton
     */
    public void enleveImage(){this.setGraphic(null);}

    /**
     * Mets a jour le bouton et sa texture
     */
    public void maj(){
        this.setText(this.laCase.getAffichage());
        switch(this.laCase.getAffichage()){
            case "1":
            this.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-weight: bold;");
            break;
            case "2":
            this.setStyle("-fx-text-fill: lime; -fx-font-size: 25px;-fx-font-weight: bold;");
            break;
            case "3":
            this.setStyle("-fx-text-fill: gold; -fx-font-size: 25px;-fx-font-weight: bold;");
            break;
            case "4":
            this.setStyle("-fx-text-fill: goldenrod; -fx-font-size: 25px;-fx-font-weight: bold;");
            break;
            case "5":
            this.setStyle("-fx-text-fill: indianred; -fx-font-size: 25px;-fx-font-weight: bold;");
            break;
            case "6":
            this.setStyle("-fx-text-fill: orangered; -fx-font-size: 25px;-fx-font-weight: bold;");
            break;
            case "7":
            this.setStyle("-fx-text-fill: crimson; -fx-font-size: 25px;-fx-font-weight: bold;");
            break;
            case "8":
            this.setStyle("-fx-text-fill: red; -fx-font-size: 25px;-fx-font-weight: bold;");
            break;
        }
        if (this.laCase.estRevelee())
            this.setDisable(true);
        else
            this.setDisable(false);
        this.setText(this.laCase.getAffichage());
        if(this.isDisabled())
        this.enleveImage();
    }

    /**
     * Réactive le bouton
     */
    public void reactive(){
        this.setDisable(false);
        this.laCase.enleve_init();
        this.setText("");
        this.setGraphic(null);
    }
}
