package com.enedis.Mustapha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class Defenseur {



     /*Ajout d'une methode jouer()
     Elle contient la logique du mode com.enedis.Mustapha.Defenseur
     Appel de deux focntions player et random dans la méthode jouer ()
    */

    private static final Logger LOGGER = LogManager.getLogger(Defenseur.class.getName());

    //Creation d'un constructeur com.enedis.Mustapha.Jeu permettant à la fin de chaque mode le choix du joueur : rejouer, nouveau mode ou quitter

    private Jeu jeu;

    public Defenseur(Jeu j) {
        this.jeu = j;
    }

    public void jouer() {

        GetPropertyValues conf = new GetPropertyValues();

        int longueurC = conf.longueurC;
        int nombreEssai =conf.nombreEssai;
        boolean partieTermine = false;
        Borne[] borneDuRandom = Borne.initialiserLesBornes(longueurC);
        char[] tableauDeVerification = new char[longueurC];



        LOGGER.info("Saisir un nombre");
        Scanner input = new Scanner(System.in);
        String saisieJoueur = input.nextLine();
        int tabPlayer[] = Fonction.player(longueurC, saisieJoueur);
        int[] propositionIA = new int[longueurC];

        do{
            try{

            Fonction.random(propositionIA, borneDuRandom, tableauDeVerification);

            LOGGER.info("Proposition : " + Arrays.toString(propositionIA) + " -> Réponse : ");
            String verification = input.nextLine();

            tableauDeVerification = verification.toCharArray();
            for (int index = 0; index < longueurC; index++) {
                if('+' == tableauDeVerification[index]){
                    borneDuRandom[index].setMinValue(propositionIA[index]);
                    LOGGER.info("+ borneDuRandom[index]"+borneDuRandom[index]);
                } else if('-' == tableauDeVerification[index]){
                    borneDuRandom[index].setMaxValue(propositionIA[index]);
                    LOGGER.info("- borneDuRandom[index]"+borneDuRandom[index]);
                }
            }
            LOGGER.debug(" ");

            if ("====".equals(verification)){
                LOGGER.info("Bravo, tu as gagne");
                partieTermine = true;
            }
             /*Condition d'arret avec un boolean et decrementation du nombre d'essai
                */
            nombreEssai --;

            /* Affichage des exceptions */

        } catch(NumberFormatException e){
            LOGGER.error(" Vous ne pouvez pas saisir de lettre ");
            break;
        } catch (StringIndexOutOfBoundsException e){
            LOGGER.error(" Respecter le nombre de chiffres ");
        } catch (IllegalArgumentException e){
            LOGGER.error("La valeur liée doit être positive");
        }

        //  condition d'arrêt jusqu'a epuisement du nombre d'essai et l'inegalite entre nbrCorrect et longueur C
        } while(nombreEssai > 0 && partieTermine == false);
        if (
                partieTermine == false){
            LOGGER.info("VOUS AVEZ PERDU !");
        }

        this.jeu.menuPrincipal();
    }

}
