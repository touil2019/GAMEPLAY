import java.util.Arrays;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Duel {

    private static final Logger LOGGER = LogManager.getLogger(Duel.class.getName());

    /**
     *
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

        System.out.println("Le nombre choisi par le joueur est : " + saisieJoueur);

        do {
            //Generation d'un nombre aleatoire par l'IA avec enregistrement du nombre dans plusieurs tableaux
            Fonction.random(propositionIA, borneDuRandom, tableauDeVerification);

            //Affichage de la proposition de l'IA
            System.out.print("Proposition : " + Arrays.toString(propositionIA) + " -> Réponse : ");
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
                System.out.println("Bravo, l'IA a gagne");
                partieTermine = true;
            }

            System.out.println("Le nombre choisi par l'IA est : " + Arrays.toString(nombreChoisiParIA));
            System.out.println("Saisir un nombre");
            String tentativeJoueur = input.nextLine();
            int tabtentative[] = Fonction.player(longueurC, tentativeJoueur);

            //Boucle avec condition afin de comparer le nomnbreChoisiParIA et le tableau du Joueur
            System.out.print("Proposition : " + tentativeJoueur + " -> Réponse : ");
            nbrCorrect = 0;
            for (int index = 0; index < longueurC; index++) {
                if (nombreChoisiParIA[index] < tabtentative[index]) {
                    System.out.print("-");
                } else if (nombreChoisiParIA[index] > tabtentative[index]) {
                    System.out.print("+");
                } else {
                    System.out.print("=");
                    nbrCorrect++;
                }
            }
            System.out.println();

            //condition d'arret du jeu avec affichage
            if (nbrCorrect == longueurC){
                System.out.println("Bravo, le joueur a gagne");
                partieTermine = true;
            }
    } while (!partieTermine);//

        //Condition pour l' arret du jeu avec affichage
        if (nbrCorrect != longueurC){
            System.out.print("Le joueur a perdu !");
        } else {
            System.out.print("L'IA a perdu !");
        }

    }
}

