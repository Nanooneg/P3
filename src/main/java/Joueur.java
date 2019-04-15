import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Joueur {

    static final Logger logger = LogManager.getLogger(Joueur.class);

    public abstract String genererCombinaison(int coupMax, int nombreChiffre);

    /**
     * affiche la Combinaison masquée
     * XXXX au départ pui +=+- en fonction des réponses du joueur défenseur
     * @param modele modéle masqué à afficher
     * @param coupRestant nombre de coup(s) restant
     * @param coupMax nombre de coup(s) alloué(s) au début du jeu
     * @param level nombre de case de la Combinaison
     * @param developpeur mode développeur on/off
     * @param combinaison Combinaison à trouver
     */
    public void afficherModele(String modele, int coupRestant, int coupMax, int level, boolean developpeur, String combinaison) {
        logger.trace("affichage du modèle");

        if (coupRestant == coupMax) {
            String modelDeBase = "";
            int i;
            for (i = 0; i < level; i++){
                modelDeBase += "X";
            }
            if (developpeur)
                System.out.println(combinaison+ " <== SOLUTION - Mode developpeur : ON");
            System.out.println(modelDeBase);
            logger.debug("modèle affiché : " +modelDeBase);
        }else {
            if (developpeur)
                System.out.println(combinaison+ " <== SOLUTION - Mode developpeur : ON");
            System.out.println(modele);
            logger.debug("modèle affiché : " +modele);
        }

        logger.trace("fin d'affichage du modèle");
    }
}
