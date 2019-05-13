package com.enedis.Mustapha;

import java.util.Arrays;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

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

    /**
     *      *
     *
     */


    public void jouer() {

        GetPropertyValues conf = new GetPropertyValues();

        int longueurC = conf.longueurC;
        int modeDev = conf.modeDev;
        int nbrCorrect = 0;
        boolean partieTermine = false;
        int nombreChoisiParIA[] = Fonction.random(longueurC);


        Scanner input = new Scanner(System.in);
        int saisieJoueur[];


        saisieJoueur = Fonction.recupererPropositionJoueur(longueurC, input);

        int[] propositionIA = new int[longueurC];

                char[] tableauDeVerification = new char[longueurC];
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

                //Affichage de la proposition de l'IA

                LOGGER.info("Proposition : " + Arrays.toString(propositionIA) + " -> Réponse : ");
                String verification = input.nextLine();
                tableauDeVerification = verification.toCharArray();

                //Condition d'arrêt avec affichage
                if ("====".equals(verification)) {
                    LOGGER.debug("Bravo, l'IA a gagne");
                    partieTermine = true;
                    continue;
                }
                if (modeDev == 1) {
                    System.out.println("Le nombre choisi par l'IA est : " + Arrays.toString(nombreChoisiParIA));
                }

                int tabtentative[] = Fonction.recupererPropositionJoueur(longueurC, input);

                //Boucle avec condition afin de comparer le nombreChoisiParIA et le tableau du Joueur

                nbrCorrect = 0;

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

