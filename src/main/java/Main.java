import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        //declaration des paramètres
        int level = 0;
        int coupMax = 0;
        boolean developpeur = false;

        try {
            //chargement des paramètres
            Properties properties = chargementPropriete.chargement("C:\\Users\\arnau\\Documents\\Doc Arnaud\\OpenClassrooms\\A rendre\\P3\\src\\properties\\config.properties");
            coupMax = Integer.parseInt(properties.getProperty("coupMax", "vide"));
            level = Integer.parseInt(properties.getProperty("caseCombinaison", "vide"));
            developpeur = Boolean.parseBoolean(properties.getProperty("developpeur", "vide"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //déclaration variables et objet combinaison
        combinaison cbn = new combinaison();
        int coupRestant = coupMax;
        int essai = 0;
        String combinaisonA;
        String combinaisonD = "";
        String modele = "";

        //présentation du jeu et récupération d'une combinaison générée aléatoirement
        parametreDuJeu.presenter();
        combinaisonA = cbn.genererModele(level);

        //boucle de déroulement du jeu : affichage du mogèle -> réponse du defenseur -> comparaison
        while ( !combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            cbn.afficherModele(modele,coupRestant,coupMax,level,developpeur,combinaisonA);
            combinaisonD = cbn.demanderDefenseur(coupRestant,coupMax,level);
            modele = cbn.comparer(combinaisonA,combinaisonD,level);
            coupRestant--;
            essai++;
        }
        // fin du jeu affichage de la solution
        cbn.afficherResultat(combinaisonA,coupRestant,essai);

    }
}