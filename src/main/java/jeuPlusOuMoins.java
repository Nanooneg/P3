import java.io.IOException;
import java.util.Properties;

public class jeuPlusOuMoins {

    public static void main(String[] args) {

        int level = 0;
        int R = 0;

        try {
            //chargement des propriétés
            Properties coupMax = chargementPropriete.chargement("C:\\Users\\arnau\\Documents\\Doc Arnaud\\OpenClassrooms\\A rendre\\P3\\src\\properties\\config.properties");
            R = Integer.parseInt(coupMax.getProperty("coupMax", "vide"));
            level = Integer.parseInt(coupMax.getProperty("caseCombinaison", "vide"));
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

        while ( !cbnA.equals(cbnD) && R != 0) {
            cbn.afficherModele(cbnM,R,level);
            cbnD = cbn.demanderDefenseur(R);
            i++;
            cbnM = cbn.comparer(cbnA,cbnD,level);
            R--;
        }

        cbn.afficherResultat(cbnA, R, i);

    }
}