package com.nanoo.p3.joueur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Joueur {

    static final Logger logger = LogManager.getLogger(Joueur.class);

    public abstract String genererCombinaison(int coupMax, int nombreChiffre);

    /**
     * affiche la Combinaison masquée
     * XXXX au départ pui +=+- en fonction des réponses du com.nanoo.p3.joueur défenseur
     * @param modele modéle masqué à afficher
     * @param level nombre de case de la Combinaison
     * @param developpeur mode développeur on/off
     * @param combinaison Combinaison à trouver
     */
    public void afficherModele(String modele, int level, boolean developpeur, String combinaison) {
        logger.trace("affichage du modèle");

        if (modele.equals("")) {

            int i;
            for (i = 0; i < level; i++){
                modele += "X";
            }
            if (developpeur)
                System.out.println(modele+ " => " +combinaison+ " <= SOLUTION - mode développeur : ON");
            else {
                System.out.println(modele);
                logger.debug("modèle affiché : " + modele);
            }
        }else {
            if (developpeur)
                System.out.println(modele+ " => " +combinaison+ " <= SOLUTION - mode développeur : ON");
            else {
                System.out.println(modele);
                logger.debug("modèle affiché : " + modele);
            }
        }

        logger.trace("fin d'affichage du modèle");
    }
}
