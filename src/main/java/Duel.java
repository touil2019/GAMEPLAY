import java.util.Arrays;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Duel {

    private static final Logger LOGGER = LogManager.getLogger(Duel.class.getName());

    /*Ajout d'une methode jouer()
     Elle contient la logique du mode Duel
     Appel de trois focntions player et random dans la méthode jouer ()
    */

    public void jouer() {


        int longueurC = 3;
        int nbrCorrect = 0;
        boolean partieTermine = false;
        int nombreChoisiParIA[] = Fonction.random(longueurC);

        Scanner input = new Scanner(System.in);

        LOGGER.debug("Saisir un nombre :");
        String saisieJoueur = input.nextLine();
        int nombreChoisiParJoueur[] = Fonction.player(longueurC, saisieJoueur);
        int[] propositionIA = new int[longueurC];

        char[] tableauDeVerification = new char[longueurC];
        Borne[] borneDuRandom = Borne.initialiserLesBornes(longueurC);

        LOGGER.debug("Le nombre choisi par le joueur est : " + saisieJoueur);

        do {
            /*Generation d'un nombre aleatoire par l'IA avec enregistrement du nombre dans les
            tableaux en parametres de la Fonction.random
             */
            Fonction.random(propositionIA, borneDuRandom, tableauDeVerification);

            //Affichage de la proposition de l'IA
            LOGGER.debug("Proposition : " + Arrays.toString(propositionIA) + " -> Réponse : ");
            String verification = input.nextLine();

            /*Boucle avec condition permettant d'enregistrer la proposition de l'IA
            afin de remplir un tableau de verification suite à la saisie des reponses par l'utilisateur
            dans le but d'orienter et faire évoluer la reponse de l'IA
           */
            tableauDeVerification = verification.toCharArray();
            for (int index = 0; index < longueurC; index++) {
                if ('+' == tableauDeVerification[index]) {
                    borneDuRandom[index].setMinValue(propositionIA[index]);
                } else if ('-' == tableauDeVerification[index]) {
                    borneDuRandom[index].setMaxValue(propositionIA[index]);
                }
            }

            //Condition d'arrêt avec affichage
            if ("===".equals(verification)) {
                LOGGER.debug("Bravo, l'IA a gagne");
                partieTermine = true;
            }

            LOGGER.debug("Le nombre choisi par l'IA est : " + Arrays.toString(nombreChoisiParIA));
            LOGGER.debug("Saisir un nombre");
            String tentativeJoueur = input.nextLine();
            int tabtentative[] = Fonction.player(longueurC, tentativeJoueur);

            //Boucle avec condition afin de comparer le nomnbreChoisiParIA et le tableau du Joueur

            LOGGER.debug("Proposition : " + tentativeJoueur + " -> Réponse : ");
            nbrCorrect = 0;
            for (int index = 0; index < longueurC; index++) {
                if (nombreChoisiParIA[index] < tabtentative[index]) {
                    LOGGER.debug("-");
                } else if (nombreChoisiParIA[index] > tabtentative[index]) {
                    LOGGER.debug("+");
                } else {
                    LOGGER.debug("=");
                    nbrCorrect++;
                }
            }
            LOGGER.debug(" " );

            //condition d'arret du jeu avec affichage
            if (nbrCorrect == longueurC){
                LOGGER.debug("Bravo, le joueur a gagne");
                partieTermine = true;
            }
    } while (!partieTermine);//

        //Condition pour l' arret du jeu avec affichage
        if (nbrCorrect != longueurC){
            LOGGER.debug("Le joueur a perdu !");
        } else {
            LOGGER.debug("L'IA a perdu !");
        }

    }
}

