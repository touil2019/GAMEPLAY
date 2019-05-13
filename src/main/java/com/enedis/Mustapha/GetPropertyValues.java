package com.enedis.Mustapha;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class GetPropertyValues permet d'appeler les variables declarees dans le fichier config.properties
 *
 * @author MUSTAPHA TOUIL
 *
 */

public class GetPropertyValues {


   public int longueurC;
   public int nombreEssai;
   public int modeDev;

    private static final GetPropertyValues val = new GetPropertyValues();

    GetPropertyValues() {

        getPropValues("config.properties");

    }
    /**
     * retourne la valeur affichée dans le fichier de config pour les variables
     * longueurC
     * nombreEssai
     * modeDev
     * @return val
     */

    public static GetPropertyValues getInstance() {

        return val;
    }


    /**
     * conversion de la saisie d'un entier en lettre
     *
     */
    void getPropValues(String fileName) {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = getClass().getClassLoader().getResourceAsStream(fileName);

            prop.load(input);


            longueurC = Integer.parseInt(prop.getProperty("longueurC"));
            nombreEssai = Integer.parseInt(prop.getProperty("nombreEssai"));
            modeDev = Integer.parseInt(prop.getProperty("modeDev"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
