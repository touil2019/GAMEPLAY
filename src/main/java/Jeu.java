import java.awt.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Jeu {

    private static final Logger LOGGER = LogManager.getLogger(Jeu.class.getName());

    public void lancerLeJeu(int operation) {
        switch (operation) {
            case 1:
                LOGGER.debug("Vous avez choisi le mode 1: challenger");

                Challenger modeChallenger = new Challenger();
                modeChallenger.jouer();
                break;

            case 2:
                LOGGER.debug("Vous avez choisi le mode 2: defenseur");

                Defenseur modeDefenseur = new Defenseur();
                modeDefenseur.jouer();
                break;
            case 3:
                LOGGER.debug("Vous avez choisi le mode 3: duel");

                Duel modeDuel = new Duel();
                modeDuel.jouer();
                break;
        }
    }
}
