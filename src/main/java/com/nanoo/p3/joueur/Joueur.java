package com.nanoo.p3.joueur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Joueur {

    private static final Logger logger = LogManager.getLogger(Joueur.class);

    public abstract String genererCombinaison(int coupMax, int nombreChiffre);

    /**
     * affiche un modèle de la combinaison à trouver
     * XXXX au départ pui +=+- en fonction des réponses du défenseur
     * @param modele modéle à afficher
     * @param level nombre de case de la Combinaison
     * @param developpeur mode développeur on/off
     * @param combinaison Combinaison à trouver
     */
    public void afficherModele(String modele, int level, boolean developpeur, String combinaison) {
        logger.trace("affichage du modèle");

        // Pas de modèle, donc premier coup : on affiche des "X" pour représenter le nombre de chiffres
        if (modele.equals("")) {
            int i, taille;

            for (i = 0, taille = level; i<taille; i++){
                modele += "X";
            }
            if (developpeur) // Mode développeur : on affiche la solution en plus du modèle
                System.out.println(modele+ " => " +combinaison+ " <= SOLUTION - mode développeur : ON");
            else {
                System.out.println(modele);
                logger.debug("modèle affiché : " + modele);
            }
        // Sinon le modèle reçu en paramètre est affiché
        }else {
            if (developpeur) // Mode développeur : on affiche la solution en pluis du modèle
                System.out.println(modele+ " => " +combinaison+ " <= SOLUTION - mode développeur : ON");
            else {
                System.out.println(modele);
                logger.debug("modèle affiché : " + modele);
            }
        }

        logger.trace("fin d'affichage du modèle");
    }
}
