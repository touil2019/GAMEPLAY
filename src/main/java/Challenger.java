import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class Challenger {

    /* Ajout d'une methode jouer()
     Elle contient la logique du mode challenger
     Appel de deux méthodes player et random dans la méthode jouer ()
    */

    private static final Logger LOGGER = LogManager.getLogger(Challenger.class.getName());

    public void jouer() {

        int longueurC = 3;
        int nombreEssai = 5;
        int nbrCorrect = 0;

    int tabPc[] = Fonction.random(longueurC);

        do{
            //Declaration de la saisie avec appel de la fonction player

            LOGGER.debug("Saisir un nombre");
            Scanner input = new Scanner(System.in);
            String saisieJoueur;
            int tabPlayer[];
            try{
                saisieJoueur = input.nextLine();
                tabPlayer = Fonction.player(longueurC, saisieJoueur);

            //Boucle permettant de restituer la position correct d'un nombre saisi par le joueur

                LOGGER.debug("Proposition : " + saisieJoueur + " -> Réponse : ");
                nbrCorrect = 0;
                for (int index = 0; index < longueurC; index++) {
                    if(tabPc[index] < tabPlayer[index]){
                        LOGGER.debug("-");
                    } else if(tabPc[index] > tabPlayer[index]){
                        LOGGER.debug("+");
                    } else {
                        LOGGER.debug("=");
                        nbrCorrect ++;
                    }
                }

                /*Condition d'arret et decrementation du nombre d'essai en comparaison de la longueur du tableau
                et nbrCorrect
                */
                nombreEssai --;
                LOGGER.debug(" ");
                if (nbrCorrect == longueurC){
                    LOGGER.debug("Bravo, tu as gagne");
                }


            if (nbrCorrect != longueurC){
                LOGGER.debug("VOUS AVEZ PERDU !");
            }
            //Affichage des exceptions

            } catch(NumberFormatException e){
                LOGGER.error("Vous ne pouvez pas saisir de lettre");
                break;
            } catch (StringIndexOutOfBoundsException e){
                LOGGER.error("Respecter le nombre de chiffres");
            }

        //  condition d'arrêt jusqu'a epuisement du nombre d'essai et l'inegalite entre nbrCorrect et longueur C

        } while(nombreEssai > 0 && nbrCorrect != longueurC);



    }
}
