package com.enedis.Mustapha;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Ajout d'une methode jouer() Elle contient la logique du mode com.enedis.Mustapha.Defenseur
 * Appel de deux focntions player et random dans la méthode jouer ()
 * @author MUSTAPHA TOUIL
 * @version
 */

public class Defenseur {

    private static final Logger LOGGER = LogManager.getLogger(Defenseur.class.getName());

    /**
     * Creation d'un constructeur com.enedis.Mustapha.
     *Jeu permettant à la fin de chaque mode le choix du joueur : rejouer, nouveau mode ou quitter
     * @Param Jeu
     */

    private Jeu jeu;

    public Defenseur(Jeu j) {
        this.jeu = j;
    }


    public void jouer() {

        GetPropertyValues conf = new GetPropertyValues();

        int longueurC = conf.longueurC;
        int nombreEssai = conf.nombreEssai;
        int modeDev = conf.modeDev;

        boolean partieTermine = false;
        Borne[] borneDuRandom = Borne.initialiserLesBornes(longueurC);
        char[] tableauDeVerification = new char[longueurC];

        Scanner input = new Scanner(System.in);

        int saisieJoueur[] = Fonction.recupererPropositionJoueur(longueurC, input);
        if (modeDev == 1) {
            System.out.println("Le nombre choisi par le joueur est : " + Arrays.toString(saisieJoueur));
        }
        int[] propositionIA = new int[longueurC];
        String verification;
        do{
            Fonction.random(propositionIA, borneDuRandom, tableauDeVerification);
            verification = Fonction.verifierPropositionIa(propositionIA, longueurC, input);
            tableauDeVerification = verification.toCharArray();

            if ("====".equals(verification)) {
                LOGGER.debug("Bravo, l'IA a gagne");
                partieTermine = true;
                continue;
            }

            nombreEssai --;

        //  condition d'arrêt jusqu'a epuisement du nombre d'essai ou l'inegalite entre nbrCorrect et longueur C
        } while(nombreEssai > 0 && partieTermine == false);
        if (
                partieTermine == false){
            LOGGER.info("L'IA A PERDU !");
        }

        this.jeu.menuPrincipal();
    }

}
