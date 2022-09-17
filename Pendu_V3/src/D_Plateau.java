import java.util.ArrayList;
import java.util.List;

public class D_Plateau {
    public final static int FACILE = 1;
    public final static int NORMAL = 2;
    public final static int DIFFICILE = 3;
    public final static int EXPERT = 4;

    private int niveau;
    private int nbLignes, nbCol, nbBombes;
    private List<List<D_Case>> lePlateau;

    /**
     * Constructeur de la classe Plateau
     * @param difficulte La difficulté choisie pour le jeu.
     */
    public D_Plateau(int difficulte){
        this.niveau = difficulte;
        switch(this.niveau){
            case(D_Plateau.FACILE):
            this.nbLignes = 10;
            this.nbCol = 10;
            this.nbBombes = 4;
            break;
            case(D_Plateau.NORMAL):
            this.nbLignes = 10;
            this.nbCol = 10;
            this.nbBombes = 8;
            break;
            case(D_Plateau.DIFFICILE):
            this.nbLignes =10;
            this.nbCol = 10;
            this.nbBombes = 12;
            break;
            case(D_Plateau.EXPERT):
            this.nbLignes = 10;
            this.nbCol = 10;
            this.nbBombes = 20;
            break;
    }
        this.lePlateau = new ArrayList<>();
        this.init();
    }
    /**
     * La préparation de la création du plateau.
     */
    public void init(){
        this.ajouteLesCases();
        this.ajoutelesBombes();
        this.ajouteLesCasesVoisines();
    }

    // Getters
    public int getNbTotalLignes(){return this.nbLignes;}
    public int getNbTotalColonnes(){return this.nbCol;}
    public int getNbTotalBombes(){return this.nbBombes;}
    public List<List<D_Case>> getPlateau(){return this.lePlateau;}

    /**
     * Récupère une case du plateau
     * @param i Coordonnée x de la case
     * @param j Coordonnée y de la case
     * @return (Case) La case aux coordonnées indiqués
     */
    public D_Case getCase(int i, int j){return this.lePlateau.get(i).get(j);}

    /**
     * Récupère les cases minées
     * @return (liste<case>) la liste des cases qui possèdent des bombes
     */
    public List<D_Case> getListeDesBombes(){
        List<D_Case> lesBombes = new ArrayList<>();
        for (int l=0;l<this.nbLignes;l++){
            for (int c=0;c<this.nbCol;c++){
                D_Case laCase = getCase(l, c);
                if(laCase.estBombe()){
                    lesBombes.add(laCase);
                }
            }
        }
        return lesBombes;
    }

    /**
     * Récupère les cases marquées
     * @return (liste<case>) La liste des cases qui sont marquées par un drapeau
     */
    public List<D_Case> getListeMarquees(){
        List<D_Case> lesMarquees = new ArrayList<>();
        for (int l=0;l<this.nbLignes;l++){
            for (int c=0;c<this.nbCol;c++){
                D_Case laCase = getCase(l, c);
                if(!(laCase.estMarquee())){
                    lesMarquees.add(laCase);
                }
            }
        }
        return lesMarquees;
    }

    /**
     * Récupère les cases qui ne sont pas révélées
     * @return (liste<case>) La liste des cases qui ne sont pas révélés
     */
    public List<D_Case> getListeCasesnonRevelees(){
        List<D_Case> lesRevelees = new ArrayList<>();
        for (int l=0;l<this.nbLignes;l++){
            for (int c=0;c<this.nbCol;c++){
                D_Case laCase = getCase(l, c);
                if(!(laCase.estRevelee())){
                    lesRevelees.add(laCase);
                }
            }
        }
        return lesRevelees;
    }

    /**
     * Récupère le nombre de cases qui sont marquées
     * @return (int) Le nombre de cases étant marquées
     */
    public int getNbCasesMarquees(){
        int nbCasesMarquees = 0;
        for (int l=0;l<this.nbLignes;l++){
            for (int c=0;c<this.nbCol;c++){
                D_Case laCase = getCase(l, c);
                if (laCase.estMarquee()){
                    nbCasesMarquees++;
                }
            }
        }
        return nbCasesMarquees;
    }

    /**
     * Récupère le nombre de cases découvertes
     * @return (int) Le nombre de cases découvertes
     */
    public int getNbCasesDecouvertes(){
        int nbCasesRevelees = 0;
        for (int l=0;l<this.nbLignes;l++){
            for (int c=0;c<this.nbCol;c++){
                D_Case laCase = getCase(l, c);
                if (laCase.estRevelee()){
                    nbCasesRevelees++;
                }
            }
        }
        return nbCasesRevelees;
    }

    /**
     * Ajoute les cases dans la grille
     */
    private void ajouteLesCases(){
        for (int l=0;l<this.nbLignes;l++){
            this.lePlateau.add(new ArrayList<>());
            for (int c=0;c<this.nbCol;c++){
                D_Case la_case = new D_Case(l,c);
                this.lePlateau.get(l).add(la_case);
            }
        }
    }

    /**
     * Ajoute les cases voisines aux cases existantes sur le plateau.
     */
    private void ajouteLesCasesVoisines() {
        int i = 0;
        int j = 0;
        for (int ligne = 0; ligne < this.nbLignes; ++ligne) {
            for(int colonne = 0; colonne < this.nbCol; ++colonne) {
                for(D_CoordonneesVoisines coord : D_CoordonneesVoisines.LISTE_COORD) {
                    i = ligne + coord.getX();
                    j = colonne + coord.getY();
                    if(!(i < 0 || j < 0 || i >= this.nbLignes || j >= this.nbCol)) { // conditions rassemblant toutes possibilités de valeurs qui nous ferait sortir des coordonnées du plateau
                        this.getCase(ligne, colonne).ajouteCaseVoisine(this.getCase(i, j));
                    }
                }
            }               
        }
    }

    /**
     * Ajoute aléatoirement des bombes sur des cases du plateau
     */
    private void ajoutelesBombes(){
        int effectue = 0;
        while (effectue<this.nbBombes){
            int x =(int)(Math.random()*this.nbLignes);
            int y =(int)(Math.random()*this.nbCol);
            D_Case laCase = this.getCase(x,y);
            laCase.ajouteBombe();
            effectue++;
        }
    }
    /**
     * Regarde si le plateau/joueur a perdu
     * @return (boolean) true si le plateau possède une bombe révelée, false sinon.
     */
    public boolean perdu(){
        for (int l=0;l<this.nbLignes;l++){
            for (int c=0;c<this.nbCol;c++){
                D_Case laCase = getCase(l, c);
                if (laCase.estRevelee() && laCase.estBombe()){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Regarde sur le plateau/joueur a gagné
     * @return (boolean) true si les conditions de victoire sont remplies, false sinon
     */
    public boolean estGagnee(){
        boolean res = true;
        boolean conditionfacultative = true;
        List<D_Case> listeBombes = this.getListeDesBombes();
        List<D_Case> CasesnonRevelees = this.getListeCasesnonRevelees();
        for (int l=0;l<this.nbLignes;l++){
            for (int c=0;c<this.nbCol;c++){
                D_Case laCase = getCase(l, c);
                if (laCase.estRevelee() && laCase.estBombe()){ // Condition de perte, donc false
                    return false;
                }
            }
        }
        if (CasesnonRevelees.equals(listeBombes)){ // Si le contenu de la liste est identique aux cases restantes.
            return true;
        }
        for(D_Case la_bombe:listeBombes){
        if (CasesnonRevelees.contains(la_bombe)){ // Tant qu'il reste une case non révelée non minée, return false
            res = false;
        }
    }
        for(D_Case la_bombe:listeBombes){
            if (((!(la_bombe.estMarquee())) && (!(la_bombe.estRevelee())))){ // Si toutes les bombes sont marquées
                if (!(CasesnonRevelees.equals(listeBombes))){ // si le contenu de la liste est identique
                    conditionfacultative = false;
                }
            }
        }
        return res || conditionfacultative;
    }

}

