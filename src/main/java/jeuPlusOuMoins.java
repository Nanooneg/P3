import java.io.IOException;
import java.util.Properties;

public class jeuPlusOuMoins {

    public static void main(String[] args) {

        int level = 0;
        int essai = 0;

        try {
            //chargement des propriétés
            Properties properties = chargementPropriete.chargement("C:\\Users\\arnau\\Documents\\Doc Arnaud\\OpenClassrooms\\A rendre\\P3\\src\\properties\\config.properties");
            essai = Integer.parseInt(properties.getProperty("coupMax", "vide"));
            level = Integer.parseInt(properties.getProperty("caseCombinaison", "vide"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        combinaison cbn = new combinaison();
        String cbnA;
        String cbnD = "";
        String cbnM = "XXXX";
        parametreDuJeu.presenter();
        cbnA = cbn.randomModele(level);
        int i = 0;

        while ( !cbnA.equals(cbnD) && essai != 0) {
            cbn.afficherModele(cbnM,essai,level);
            cbnD = cbn.demanderDefenseur(essai);
            i++;
            cbnM = cbn.comparer(cbnA,cbnD,level);
            essai--;
        }

        cbn.afficherResultat(cbnA,essai,i);

    }
}