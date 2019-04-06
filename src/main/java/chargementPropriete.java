import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class chargementPropriete {
    /**
     * récupere les propriétés dans un fichier .properties
     * @param filename fichier contenant les propriétés
     * @throws IOException
     * @throws FileNotFoundException
     * @return un objet contenant les propriétés du fichier
     */
    public static Properties chargement (String filename) throws IOException, FileNotFoundException {

        Properties properties = new Properties();

        FileInputStream input = new FileInputStream("C:\\Users\\arnau\\Documents\\Doc Arnaud\\OpenClassrooms\\A rendre\\P3\\src\\properties\\config.properties");
        try{
            properties.load(input);
            return properties;
        }
        finally {
            input.close();
        }
    }
}
