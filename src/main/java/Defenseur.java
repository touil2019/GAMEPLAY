import java.util.Arrays;
import java.util.Scanner;

public class Defenseur {

    public void jouer() {

        int longueurC = 3;
        int nombreEssai = 8;
        boolean partieTermine = false;
        Borne[] borneDuRandom = Borne.initialiserLesBornes(longueurC);


        System.out.println("Saisir un nombre");
        Scanner input = new Scanner(System.in);
        String saisieJoueur = input.nextLine();
        int tabPlayer[] = Fonction.player(longueurC, saisieJoueur);
        int[] propositionIA = new int[longueurC];

        char[] tableauDeVerification = new char[longueurC];

        do{
            Fonction.random(propositionIA, borneDuRandom, tableauDeVerification);

            System.out.print("Proposition : " + Arrays.toString(propositionIA) + " -> Réponse : ");
            String verification = input.nextLine();

            tableauDeVerification = verification.toCharArray();
            for (int index = 0; index < longueurC; index++) {
                if('+' == tableauDeVerification[index]){
                    borneDuRandom[index].setMinValue(propositionIA[index]);
                    System.out.println("+ borneDuRandom[index]"+borneDuRandom[index]);
                } else if('-' == tableauDeVerification[index]){
                    borneDuRandom[index].setMaxValue(propositionIA[index]);
                    System.out.println("- borneDuRandom[index]"+borneDuRandom[index]);
                }
            }
            System.out.println();

            if ("===".equals(verification)){
                System.out.print("Bravo, tu as gagne");
                partieTermine = true;
            }
            nombreEssai --;
        } while(nombreEssai > 0 && partieTermine == false);

        if (
                partieTermine == false){
            System.out.print("VOUS AVEZ PERDU !");
        }
    }
}
