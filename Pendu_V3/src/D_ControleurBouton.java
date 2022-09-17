
import javafx.event.EventHandler; 
import javafx.scene.input.MouseEvent; 
import javafx.scene.input.MouseButton;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import java.util.Optional;

public class D_ControleurBouton implements EventHandler<MouseEvent>{
    
    private D_Bouton bouton;
    private D_Case laCase;
    private D_DemineurGraphique demineur;
    private D_Plateau lePlateau;

    /**
     * Constructeur de la classe ControleurBouton
     * @param bouton Le bouton auquel le controleur est attittré
     * @param laCase La case sur laquelle le bouton est actif
     * @param demineur La vue du jeu du démineur
     * @param lePlateau Le plateau de jeu
     */
    public D_ControleurBouton(D_Bouton bouton, D_Case laCase, D_DemineurGraphique demineur, D_Plateau lePlateau){
        this.bouton = bouton;
        this.laCase = laCase;
        this.demineur = demineur;
        this.lePlateau = lePlateau;
    }

    /**
     * En cas de clic, et suivant lequel, de souris, modifie le bouton et active différente fonctions suivant l'action entrainée.
     */
    @Override
    public void handle(MouseEvent e) {
        if (!this.laCase.estRevelee()){
            if (e.getButton() == MouseButton.PRIMARY)
            {
                this.laCase.reveler(); // On révèle la case ainsi que ses cases voisines si elles n'ont aucune bombe a proximité
                this.demineur.EmptyProxi(); 
                this.bouton.maj();               
                this.bouton.setDisable(true);
            }
            
            if(e.getButton() == MouseButton.SECONDARY){
                if (!this.laCase.estRevelee() && !this.laCase.estMarquee()){
                    if(this.lePlateau.getNbCasesMarquees() < this.lePlateau.getNbTotalBombes())
                    this.laCase.marquer(); // On peut marquer la case seulement si le nombre de drapeaux ne dépasse pas le nombre de bombes.
                    else{
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Attention!");
                        alert.setHeaderText("Le drapeau ne peut pas être planté");
                        alert.setContentText("Vous ne pouvez pas avoir plus \n de drapeaux que de bombes ");
                        alert.showAndWait();
                    }
                    this.bouton.maj();
                }
                else{
                    this.laCase.demarquer(); // Si un drapeau est déjà sur la case, alors on la démarque.
                    this.bouton.enleveImage();
                    this.bouton.maj();
                }
            }
        }
        if (this.laCase.estRevelee() && this.laCase.estBombe())
            this.bouton.ajouteImage("img/bombe.png"); // Impossible d'acceder aux images hors du src.
        else if (this.laCase.estMarquee()){
            this.bouton.setText("");
            this.bouton.ajouteImage("img/drapeau.png"); // Impossible d'acceder aux images hors du src.
        }
        this.demineur.maj_des_infos(); // On mets a jour toutes les informations
        
        if (this.lePlateau.perdu()){ // On regarde si le joueur a perdu juste après son action
            this.demineur.getChrono().stop();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Vous avez perdu !\nVoulez-vous rejouer ?",ButtonType.YES, ButtonType.NO);
            alert.setTitle("Attention");
            Optional<ButtonType> rep = alert.showAndWait();
            
            if (rep.isPresent() && rep.get()==ButtonType.YES){ // S'il veut rejouer, on redémare tout a zero.
                this.demineur.getChrono().resetTime();
                this.demineur.getChrono().start();
                this.demineur.reactivation_des_boutons();
                this.lePlateau.init();
                this.demineur.maj_de_la_grille();
                this.demineur.maj_des_infos();
            }
            else{
                this.demineur.exit();
            }            
        }
        else if(this.lePlateau.estGagnee()){ // A l'inverse on regarde s'il a gagné
            this.demineur.getChrono().stop();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Bravo vous avez gagné en "+this.demineur.getChrono().getText()+" !\nVoulez-vous rejouer ?",ButtonType.YES, ButtonType.NO);
            alert.setTitle("Félicitations");
            Optional<ButtonType> rep = alert.showAndWait();
            
            if (rep.isPresent() && rep.get()==ButtonType.YES){
                this.demineur.getChrono().resetTime();
                this.demineur.getChrono().start();
                this.demineur.reactivation_des_boutons();
                this.lePlateau.init();
                this.demineur.maj_de_la_grille();
                this.demineur.maj_des_infos();
            }
            else{
                this.demineur.exit();
            }
        }
    }
}
