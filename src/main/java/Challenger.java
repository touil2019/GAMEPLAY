import java.util.Random;
import java.util.Scanner;

public class Challenger {

    /* Ajouter une méthode jouer()
     Elle contient la logique du mode challenger (La même chose que ce qu'on a fait dans Main.java)
     Copier les deux méthodes player et random
     tu appelles ces deux méthodes dans la méthode jouer ()
    */
    public void jouer() {

        int longueurC = 3;
        int nombreEssai = 5;
        int nbrCorrect = 0;

    int tabPc[] = Fonction.random(longueurC);

        do{
            System.out.println("Saisir un nombre");
            Scanner input = new Scanner(System.in);
            String saisieJoueur = input.nextLine();
            int tabPlayer[] = Fonction.player(longueurC, saisieJoueur);


            System.out.print("Proposition : " + saisieJoueur + " -> Réponse : ");
            nbrCorrect = 0;
            for (int index = 0; index < longueurC; index++) {
                if(tabPc[index] < tabPlayer[index]){
                    System.out.print("-");
                } else if(tabPc[index] > tabPlayer[index]){
                    System.out.print("+");
                } else {
                    System.out.print("=");
                    nbrCorrect ++;
                }
            }
            nombreEssai --;
            System.out.println();
            if (nbrCorrect == longueurC){
                System.out.print("Bravo, tu as gagne");
            }
        } while(nombreEssai > 0 && nbrCorrect != longueurC);

        if (nbrCorrect != longueurC){
            System.out.print("VOUS AVEZ PERDU !");
        }
    }
}
