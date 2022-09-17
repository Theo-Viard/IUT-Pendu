import java.util.ArrayList;
import java.util.Scanner;

public class ModelePendu {
    private String mot;
    private ArrayList<String> EncryptedWord;
    private int vies;
    private String niveau;

    public ModelePendu(){
        this.mot = "";
        this.vies = 10;
    }

    public ModelePendu(String niveau){
        this.mot = "";
        this.vies = 10;
        this.niveau = niveau;
    }

    public void gameStart(){
        Scanner input = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu du pendu !");
        while(this.mot == ""){
            System.out.println("Veuillez indiquer le nombre de lettres voulues (de 3-10 ou le mode max): ");
            System.out.print("3-10/max : ");
            this.niveau = input.nextLine();
        }
        this.mot = this.mot.toUpperCase();
        this.Encryptage();
        while(this.endGame() && this.vies > 0){
            System.out.println(this.EncryptedWord);
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

    public boolean endGame(){
        for(int i=0; i<this.mot.length();++i){
            if(!(String.valueOf(this.mot.charAt(i)).equals(this.EncryptedWord.get(i)))){
                return true;
            }
        }
        return false;
    }
    public void Encryptage(){
        this.mot = this.mot.toUpperCase();
        ArrayList<String> temp = new ArrayList<>();
        for(int i=0;i<this.mot.length();++i){
            temp.add("*");
        }
        this.EncryptedWord = temp;
    }
    public void getMot(){
        if(this.niveau.equals("3")){
            ArrayList<String> temp = banqueDeMots.trois();
            this.mot = temp.get((int)(Math.random() * temp.size()));
        }
        else if(this.niveau.equals("4")){
            ArrayList<String> temp = banqueDeMots.quatre();
            this.mot = temp.get((int)(Math.random() * temp.size()));
        }
        else if(this.niveau.equals("5")){
            ArrayList<String> temp = banqueDeMots.cinq();
            this.mot = temp.get((int)(Math.random() * temp.size()));
        }
        else if(this.niveau.equals("6")){
            ArrayList<String> temp = banqueDeMots.six();
            this.mot = temp.get((int)(Math.random() * temp.size()));
        }
        else if(this.niveau.equals("7")){
            ArrayList<String> temp = banqueDeMots.sept();
            this.mot = temp.get((int)(Math.random() * temp.size()));
        }
        else if(this.niveau.equals("8")){
            ArrayList<String> temp = banqueDeMots.huit();
            this.mot = temp.get((int)(Math.random() * temp.size()));
        }
        else if(this.niveau.equals("9")){
            ArrayList<String> temp = banqueDeMots.neuf();
            this.mot = temp.get((int)(Math.random() * temp.size()));
        }
        else if(this.niveau.equals("10")){
            ArrayList<String> temp = banqueDeMots.dix();
            this.mot = temp.get((int)(Math.random() * temp.size()));
        }
        else if(this.niveau.equals("max")){
            ArrayList<String> temp = banqueDeMots.max();
            this.mot = temp.get((int)(Math.random() * temp.size()));
        }
        else{
        System.out.println("Ce n'est pas un choix valide.");
        }

    }
    public void verification(String charact){
        Boolean life = false;
        charact = charact.toUpperCase();
        if(charact.length() == 1){
            if(this.mot.contains(charact)){
                for(int i=0;i<this.mot.length();++i){
                    if(String.valueOf(this.mot.charAt(i)).equals(charact)){
                        this.EncryptedWord.set(i, charact);
                        life = true;
                    }
                }
            }
        }
        if(!(life)){
            this.vies -= 1;
        }
    }

    public String getEncrypt(){
        String res = "";
        for(String i : this.EncryptedWord){
            res += i;
        }
        return res;
    }
    public int getVies(){
        return this.vies;
    }
    public String getMotFinal(){
        return this.mot;
    }
    public static void main(String[] args){
        ModelePendu game = new ModelePendu();
        game.gameStart();
    }
}
