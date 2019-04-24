import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class Defenseur {

    private static final Logger LOGGER = LogManager.getLogger(Defenseur.class.getName());

    public void jouer() {

        int longueurC = 3;
        int nombreEssai = 8;
        boolean partieTermine = false;
        Borne[] borneDuRandom = Borne.initialiserLesBornes(longueurC);


        LOGGER.debug("Saisir un nombre");
        Scanner input = new Scanner(System.in);
        String saisieJoueur = input.nextLine();
        int tabPlayer[] = Fonction.player(longueurC, saisieJoueur);
        int[] propositionIA = new int[longueurC];

        char[] tableauDeVerification = new char[longueurC];

        do{
            Fonction.random(propositionIA, borneDuRandom, tableauDeVerification);

            LOGGER.debug("Proposition : " + Arrays.toString(propositionIA) + " -> Réponse : ");
            String verification = input.nextLine();

            tableauDeVerification = verification.toCharArray();
            for (int index = 0; index < longueurC; index++) {
                if('+' == tableauDeVerification[index]){
                    borneDuRandom[index].setMinValue(propositionIA[index]);
                    LOGGER.debug("+ borneDuRandom[index]"+borneDuRandom[index]);
                } else if('-' == tableauDeVerification[index]){
                    borneDuRandom[index].setMaxValue(propositionIA[index]);
                    LOGGER.debug("- borneDuRandom[index]"+borneDuRandom[index]);
                }
            }
            LOGGER.debug(" ");

            if ("===".equals(verification)){
                LOGGER.debug("Bravo, tu as gagne");
                partieTermine = true;
            }
            nombreEssai --;
        } while(nombreEssai > 0 && partieTermine == false);

        if (
                partieTermine == false){
            LOGGER.debug("VOUS AVEZ PERDU !");
        }
    }
}
