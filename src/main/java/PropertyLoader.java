import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

public class PropertyLoader {

    public int longueurC;
    public int nombreEssai;

    private static final PropertyLoader pro = new PropertyLoader();

    private PropertyLoader(){
        load("config.properties");
    }

   public static PropertyLoader getInstance(){
        return pro;
   }


    /**
     * Charge la liste des propriétés contenu dans le fichier spécifié
     *
     * @param filename le fichier contenant les propriétés
     * @return un objet Properties contenant les propriétés du fichier
     */


    void load(String filename) {
        Properties properties = new Properties();

        InputStream input = null;

        try {
            input = getClass().getClassLoader().getResourceAsStream(filename);

            properties.load(input);
            longueurC = Integer.parseInt(properties.getProperty("recherche_longueurC"));
            nombreEssai = Integer.parseInt(properties.getProperty("recherche_nombreEssai"));


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
