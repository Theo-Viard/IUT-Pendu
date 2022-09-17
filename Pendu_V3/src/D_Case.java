
import java.util.ArrayList;
import java.util.List;

public class D_Case {
    private boolean bombe, revelee, marquee;
    private int cooX, cooY;
    private List<D_Case> CasesVoisines;

    /**
     * Constructeur de la classe Case
     * @param x Coordonnée x de la position de la case sur le plateau
     * @param y Coordonnée y de la position de la case sur le plateau
     */
    public D_Case(int x, int y){
        this.bombe = false;
        this.revelee = false;
        this.marquee = false;
        this.cooX = x;
        this.cooY = y;
        this.CasesVoisines = new ArrayList<>();
    }
    // Getters
    public boolean estBombe(){return this.bombe;}
    public boolean estRevelee(){return this.revelee;}
    public boolean estMarquee(){return this.marquee;}
    public int getX(){return this.cooX;}
    public int getY(){return this.cooY;}
    public List<D_Case> getCasesVoisines(){return this.CasesVoisines;}
    // Setters
    public void ajouteBombe(){this.bombe = true;}
    public void reveler(){this.revelee = true;}
    public void marquer(){this.marquee = true;}
    public void demarquer(){ this.marquee = false;}
    

    // Methodes

     /**
     * Permet de réinitialiser les attributs de la case.
     * @return Void
    */
    public void enleve_init(){
        this.marquee = false;
        this.revelee = false;
        this.bombe = false;
        this.CasesVoisines.clear();
    }

    /**
     * Permet de récupérer les cases minées au voisinage de la case.
     * @return (List<Case>) La liste des cases voisines possèdant une bombe.
     */
    public List<D_Case> getBombesVoisines(){
        List<D_Case> lb = new ArrayList<>();
        for(D_Case c: this.CasesVoisines){
            if(c.estBombe()){
                lb.add(c);
            }
        }
        return lb;
    }

    /**
     * Ajoute une case voisine a cette même case
     * @param D_Case La case voisine a rajouter
     * @return Void
     */
    public void ajouteCaseVoisine(D_Case x){
        if(this.CasesVoisines.size() <= 8 && !(this.CasesVoisines.contains(x))){
            this.CasesVoisines.add(x);
        }
    }

    /**
     * Récupère le nombre de bombes parmis les cases voisines de la case
     * @return (int) Le nombre de cases possédant une bombe parmis les cases voisines ce la case
     */
    public int getNbBombesVoisines(){
        int cpt = 0;
        for(D_Case x : this.getCasesVoisines())
        if(x.estBombe()){
            cpt+=1;
        }
        return cpt;
    }

    /**
     * Retourne l'affichage en fonction des attributs de la case.
     * @return (String) La valeur de la case sous un format String.
     */
    public String getAffichage(){
        if(!(this.revelee) && !(this.marquee))
        return "";
        if(this.revelee && this.bombe)
        return "@";
        if(this.marquee)
        return "";
        if(this.getNbBombesVoisines() == 0)
        return " ";
        return this.getNbBombesVoisines() + "";
    }

    /**
     * Surchage la méthode equals de la superclasse Object et compare deux cases afin de savoir si elles possèdent la même position
     * @return (boolean) true si les cases ont la même position, false sinon.
     */
    @Override
    public boolean equals(Object o){
        if (o==null)
            return false;
        if (this==o)
            return true;
        if (o instanceof D_Case){
            D_Case laCase = (D_Case)o;
            return laCase.getX() == this.getX() && laCase.getY() == this.getY();
        }
        return false;

    }
}
