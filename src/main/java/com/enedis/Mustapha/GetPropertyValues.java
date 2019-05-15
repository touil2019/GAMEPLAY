package com.enedis.Mustapha;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class GetPropertyValues permet d'appeler les variables declarees dans le fichier config.properties
 *
 * @author MUSTAPHA TOUIL
 * @version
 * @return val
 */

public class GetPropertyValues {


   public int longueurC;
   public int nombreEssai;
   public int modeDev;

    private static final GetPropertyValues val = new GetPropertyValues();

    GetPropertyValues() {

        getPropValues("config.properties");

    }

    public static GetPropertyValues getInstance() {

        return val;
    }



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
