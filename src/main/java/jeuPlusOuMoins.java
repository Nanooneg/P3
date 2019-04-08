import java.io.IOException;
import java.util.Properties;

public class jeuPlusOuMoins {

    public static void main(String[] args) {

        int level = 0;
        int coupMax = 0;
        boolean developpeur = false;

        try {
            //chargement des propriétés
            Properties properties = chargementPropriete.chargement("C:\\Users\\arnau\\Documents\\Doc Arnaud\\OpenClassrooms\\A rendre\\P3\\src\\properties\\config.properties");
            coupMax = Integer.parseInt(properties.getProperty("coupMax", "vide"));
            level = Integer.parseInt(properties.getProperty("caseCombinaison", "vide"));
            developpeur = Boolean.parseBoolean(properties.getProperty("developpeur", "vide"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        combinaison cbn = new combinaison();
        int coupRestant = coupMax;
        int essai = 0;
        String cbnA;
        String cbnD = "";
        String cbnM = "XXXX";
        parametreDuJeu.presenter();
        cbnA = cbn.randomModele(level);

        while ( !cbnA.equals(cbnD) && coupRestant != 0) {
            cbn.afficherModele(cbnM,coupRestant,coupMax,level,developpeur,cbnA);
            cbnD = cbn.demanderDefenseur(coupRestant);
            cbnM = cbn.comparer(cbnA,cbnD,level);
            coupRestant--;
            essai++;
        }

        cbn.afficherResultat(cbnA,coupRestant,essai);

    }
}