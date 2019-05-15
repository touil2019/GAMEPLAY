package com.enedis.Mustapha;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Ajout d'une methode jouer() dans la classe Duel
 * Elle contient la logique du mode com.enedis.Mustapha.Duel
 * Appel de trois focntions player et random dans la méthode jouer ()
 * @author MUSTAPHA TOUIL
 * @version
 */

public class Duel {

    private static final Logger LOGGER = LogManager.getLogger(Duel.class.getName());

    /**
     * Creation d'un constructeur com.enedis.Mustapha.Jeu permettant à la fin de
     * chaque mode le choix du joueur : rejouer, nouveau mode ou quitter
     * @param Jeu j
   */
    private Jeu jeu;

    public Duel(Jeu j) { this.jeu = j; }


    public void jouer() {

        GetPropertyValues conf = new GetPropertyValues();

        int longueurC = conf.longueurC;
        int modeDev = conf.modeDev;
        int nbrCorrect = 0;
        boolean partieTermine = false;
        int nombreChoisiParIA[] = Fonction.random(longueurC);
        char[] tableauDeVerification = new char[longueurC];

        Scanner input = new Scanner(System.in);
        int saisieJoueur[];


        saisieJoueur = Fonction.recupererPropositionJoueur(longueurC, input);

        int[] propositionIA = new int[longueurC];


                Borne[] borneDuRandom = Borne.initialiserLesBornes(longueurC);

                if (modeDev == 1) {
                    System.out.println("Le nombre choisi par le joueur est : " + Arrays.toString(saisieJoueur));
                }

                do {
                    try{

                /*Generation d'un nombre aleatoire par l'IA avec enregistrement du nombre dans les
                tableaux en parametres de la com.enedis.Mustapha.Fonction.random
                 */
                Fonction.random(propositionIA, borneDuRandom, tableauDeVerification);

                //Verification de la proposition de l'IA
                String verification;
                verification = Fonction.verifierPropositionIa(propositionIA, longueurC, input);
                tableauDeVerification = verification.toCharArray();

                if ("====".equals(verification)) {
                    LOGGER.debug("Bravo, l'IA a gagne");
                    partieTermine = true;
                    continue;
                }

                //Affichage de la combinaison secrete de l'IA

                if (modeDev == 1) {
                    System.out.println("Le nombre choisi par l'IA est : " + Arrays.toString(nombreChoisiParIA));
                }

                int tabtentative[] = Fonction.recupererPropositionJoueur(longueurC, input);

                nbrCorrect = 0;

                //appel de la fonction verifierPropositionJoueur
                //afin d'afficher la proposition de l'IA avec en parametre nombreChoisiParIA, tabtentative, longueurC

                String resultat = Fonction.verifierPropositionJoueur(nombreChoisiParIA, tabtentative, longueurC);
                LOGGER.info("Proposition : " + Arrays.toString(tabtentative) + " -> Réponse : " + resultat);

                //condition d'arret du jeu avec affichage
                if ("====".equals(resultat)){
                    LOGGER.info("Bravo, le joueur a gagne");
                    nbrCorrect = 4;
                    partieTermine = true;
                    continue;
                }
                /* Affichage des exceptions */

            } catch(NumberFormatException e){
                LOGGER.error(" Vous ne pouvez pas saisir de lettre ");
            } catch (StringIndexOutOfBoundsException e){
                LOGGER.error(" Respecter le nombre de chiffres ");
            } catch (IllegalArgumentException e){
                LOGGER.error("La valeur liée doit être positive");
            }
        } while (!partieTermine);

        //Condition pour l'arret du jeu avec affichage
        if (nbrCorrect != longueurC){
            LOGGER.info("Le joueur a perdu !");
        } else {
            LOGGER.info("L'IA a perdu !");
        }
        this.jeu.menuPrincipal();
    }
}

