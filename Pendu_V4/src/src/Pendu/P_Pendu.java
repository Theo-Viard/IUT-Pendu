
package src.Pendu;
import java.util.*;

public class P_Pendu {
    private String mot;
    private ArrayList<String> motEncrypte;
    private int niveau;
    private int vies;
    private String theme;
    
    public P_Pendu(){
        this.mot = "";
        this.motEncrypte = new ArrayList<>();
        this.niveau = -1;
        this.vies = 10;
        this.theme = "Normal";
    }
    public String getMotMain(){
        return this.mot;
    }
    public String getDifficulte(){
        if(this.niveau == 0)
        return "Facile";
        if(this.niveau == 1)
        return "Medium";
        if(this.niveau == 2)
        return "Expert";
        return "Personnalisé";
    }
    public void Encryptage(){
        this.mot = this.mot.toUpperCase();
        for(int i=0;i<this.mot.length();i++){
            this.motEncrypte.add("*");
        }
    }
    public void setNiveau(int niv){
        this.niveau = niv;
    }

    public int getMotNiveau(){
        if(this.niveau == 0){
            return ((int) Math.floor(Math.random() * (5 - 3 + 1) + 3));
        }
        if(this.niveau == 1){
            return ((int) Math.floor(Math.random() * (8 - 5 + 1) + 3));
        }
        if(this.niveau == 2){
            return ((int) Math.floor(Math.random() * (13 - 8 + 1) + 8));
        }
        return this.niveau;
    }
    
    public ArrayList<String> getDicoTheme(){
        if(this.theme == "Normal"){
            return P_banqueDeMots.normalMode();
        }
        return null;
    }
    public void setMot(){
        int nbLettres = getMotNiveau();
        ArrayList<String> Dictionnaire = getDicoTheme();
        while(this.mot == ""){
            String motTemp = Dictionnaire.get(((int) Math.floor(Math.random() * (Dictionnaire.size()))));
            if(motTemp.length() == nbLettres){
                this.mot = motTemp;
            }
        }
    }
    public void verification(String charact){
        Boolean life = false;
        String CharTemp = charact.toUpperCase();
        if(charact.length() == 1){
            if(this.mot.contains(CharTemp)){
                for(int i=0;i<this.mot.length();++i){
                    if(String.valueOf(this.mot.charAt(i)).equals(CharTemp)){
                        this.motEncrypte.set(i, CharTemp);
                        life = true;
                    }
                }
            }
        }
        if(!(life)){
            this.vies -= 1;
        }
    }


    public boolean verifEnd(){
        for(String x : this.motEncrypte){
            if(x == "*"){
                return false;
            }
        }
        return true;
    }
    public String getEncrypted(){
        String res = "";
        for(String mot : this.motEncrypte){
            res += mot;
        }
        return res;
    }
    public void gameStart(){
        try{
            Scanner input = new Scanner(System.in);
            System.out.println("Bienvenue dans le jeu du pendu !");
            System.out.println("Veuillez indiquer le niveau souhaité ");
            System.out.println("0 - Facile");
            System.out.println("1 - Moyen");
            System.out.println("2 - Difficile");
            System.out.println("3-13 - Nombres de lettres");
            this.niveau = Integer.valueOf(input.nextLine());
            this.setMot();
            this.Encryptage();
            while(this.vies > 0 && !this.verifEnd()){
                System.out.println(this.motEncrypte);
                System.out.println("Entrez une lettre : ");
                verification(input.nextLine());
            }
            if(this.vies == 0){
                System.out.println("Dommage tu n'étais pas loin. Le mot était : " + this.mot);
            }
            else{
                System.out.println("Félicitations ! Le mot était bien : " + this.mot);
            }
            input.close();
        }
        catch(NumberFormatException ex){
            System.out.println("Ce n'est pas un nombre valide !");
        }
    }
    public int getVies(){
        return this.vies;
    }

    public static void main(String[] args){
        P_Pendu game = new P_Pendu();
        game.gameStart();
    }

}