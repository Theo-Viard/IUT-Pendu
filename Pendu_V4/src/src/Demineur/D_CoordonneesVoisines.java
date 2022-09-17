package src.Demineur;
import java.util.List;
import java.util.Arrays;

public class D_CoordonneesVoisines {
    //* classe de constantes (ou attributs statiques) des coordonnées voisines d'une case.*/
    public int x;
    public int y;
    public static D_CoordonneesVoisines HAUT= new D_CoordonneesVoisines(0,-1);
    public static D_CoordonneesVoisines BAS = new D_CoordonneesVoisines(0,1);
    public static D_CoordonneesVoisines GAUCHE = new D_CoordonneesVoisines(-1, 0);
    public static D_CoordonneesVoisines DROITE = new D_CoordonneesVoisines(1,0);
    public static D_CoordonneesVoisines HAUT_GAUCHE = new D_CoordonneesVoisines(-1, -1);
    public static D_CoordonneesVoisines HAUT_DROITE = new D_CoordonneesVoisines(1, -1);
    public static D_CoordonneesVoisines BAS_GAUCHE = new D_CoordonneesVoisines(-1, 1);
    public static D_CoordonneesVoisines BAS_DROITE = new D_CoordonneesVoisines(1,1);
    public static List<D_CoordonneesVoisines> LISTE_COORD = Arrays.asList(D_CoordonneesVoisines.HAUT_GAUCHE, D_CoordonneesVoisines.HAUT, D_CoordonneesVoisines.HAUT_DROITE, D_CoordonneesVoisines.GAUCHE, D_CoordonneesVoisines.DROITE, D_CoordonneesVoisines.BAS_GAUCHE, D_CoordonneesVoisines.BAS, D_CoordonneesVoisines.BAS_DROITE);

    /**
     * Constructeur de la classe CoordonneesVoisines
     * @param x Coordonnée x d'une position sur le plateau
     * @param y Coordonnée y d'une position sur le plateau
     */
    public D_CoordonneesVoisines(int x,int y){
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX(){return this.x;}
    public int getY(){return this.y;}
}
