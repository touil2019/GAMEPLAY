package com.enedis.Mustapha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

public class Challenger {

    /* Ajout d'une methode jouer()
     Elle contient la logique du mode com.enedis.Mustapha.Challenger
     Appel de deux méthodes player et random dans la méthode jouer ()
    */

    private static final Logger LOGGER = LogManager.getLogger(Challenger.class.getName());

    //Creation d'un constructeur com.enedis.Mustapha.Jeu permettant à la fin de chaque mode le choix du joueur : rejouer, nouveau mode ou quitter

    private Jeu jeu;

    public Challenger(Jeu j) { this.jeu = j;

    }

    public void jouer() {

        GetPropertyValues conf = new GetPropertyValues();

        int longueurC = conf.longueurC;
        int nombreEssai =conf.nombreEssai;
        int modeDev = conf.modeDev;
        int nbrCorrect = 0;

    int tabPc[] = Fonction.random(longueurC);

    if (modeDev == 1){
        System.out.println("le code scret de l'ordinateur est : "+ Arrays.toString(tabPc));
    }


        do{
            //Declaration de la saisie avec appel de la fonction player

            LOGGER.info("Saisir un nombre");
            Scanner input = new Scanner(System.in);
            String saisieJoueur;
            int tabPlayer[];
            try{
                saisieJoueur = input.nextLine();
                tabPlayer = Fonction.player(longueurC, saisieJoueur);

            //Boucle permettant de restituer la position correct d'un nombre saisi par le joueur

                LOGGER.info("Proposition : " + saisieJoueur + " -> Réponse : ");
                nbrCorrect = 0;
                for (int index = 0; index < longueurC; index++) {
                    if(tabPc[index] < tabPlayer[index]){
                        LOGGER.info("-");
                    } else if(tabPc[index] > tabPlayer[index]){
                        LOGGER.info("+");
                    } else {
                        LOGGER.info("=");
                        nbrCorrect ++;
                    }
                }

                /*Condition d'arret et decrementation du nombre d'essai en comparaison de la longueur du tableau
                et nbrCorrect
                */
                nombreEssai --;
                LOGGER.debug(" ");
                if (nbrCorrect == longueurC){
                    LOGGER.info(" Bravo, tu as gagne ");
                }

                /* Affichage des exceptions */

            } catch(NumberFormatException e){
                LOGGER.error(" Vous ne pouvez pas saisir de lettre ");
                break;
            } catch (StringIndexOutOfBoundsException e){
                LOGGER.error(" Respecter le nombre de chiffres ");
            }

        //  condition d'arrêt jusqu'a epuisement du nombre d'essai et l'inegalite entre nbrCorrect et longueur C

        } while(nombreEssai > 0 && nbrCorrect != longueurC);


        if (nbrCorrect != longueurC){
            LOGGER.info(" VOUS AVEZ PERDU ! ");
        }
        this.jeu.menuPrincipal();

    }

}