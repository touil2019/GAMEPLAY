package com.enedis.Mustapha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Ajout d'une methode jouer() Elle contient la logique du mode com.enedis.Mustapha.Challenger
 *Appel de deux méthodes player et random dans la méthode jouer ()
 * @author MUSTAPHA TOUIL
 * @version
 */

public class Challenger {



    private static final Logger LOGGER = LogManager.getLogger(Challenger.class.getName());

    /**
     * Creation d'un constructeur com.enedis.Mustapha.
     * Jeu permettant à la fin de chaque mode le choix du joueur : rejouer, nouveau mode ou quitter
     * @param Jeu j
     */

    private Jeu jeu;

    public Challenger(Jeu j) {
        this.jeu = j;

    }

     public void jouer() {

        GetPropertyValues conf = new GetPropertyValues();

        int longueurC = conf.longueurC;
        int nombreEssai = conf.nombreEssai;
        int modeDev = conf.modeDev;
        int nbrCorrect = 0;

        int tabPc[] = Fonction.random(longueurC);

        if (modeDev == 1) {
            System.out.println("le code secret de l'ordinateur est : " + Arrays.toString(tabPc));
        }


        do {
            //Declaration de la saisie avec appel de la fonction player


            Scanner input = new Scanner(System.in);
            int tabPlayer[];
            try {
                tabPlayer = Fonction.recupererPropositionJoueur(longueurC, input);

                 //fonction permettant de restituer la position correct d'un nombre saisi par le joueur


                nbrCorrect = 0;

                String resultat = Fonction.verifierPropositionJoueur(tabPc, tabPlayer, longueurC);
                LOGGER.info("Proposition : " + Arrays.toString(tabPlayer) + " -> Réponse : " + resultat);

                /*Condition d'arret et decrementation du nombre d'essai en comparaison de la longueur du tableau
                et nbrCorrect
                */
                nombreEssai--;

                if ("====".equals(resultat)) {
                    nbrCorrect = 4;
                    LOGGER.info(" Bravo, tu as gagne ");
                }

                /* Affichage des exceptions */

            } catch (NumberFormatException e) {
                LOGGER.error(" Vous ne pouvez pas saisir de lettre ");
            } catch (StringIndexOutOfBoundsException e) {
                LOGGER.error(" Respecter le nombre de chiffres ");
            } catch (IllegalArgumentException e) {
                LOGGER.error("La valeur liée doit être positive");
            }

            //  condition d'arrêt jusqu'a epuisement du nombre d'essai ou l'inegalite entre nbrCorrect et longueur C

        } while (nombreEssai > 0 && nbrCorrect != longueurC);


        if (nbrCorrect != longueurC) {
            LOGGER.info(" VOUS AVEZ PERDU ! ");
        }
        this.jeu.menuPrincipal();

    }

}
