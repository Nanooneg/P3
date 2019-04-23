package com.nanoo.p3.jeu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Historique {

    static final Logger logger = LogManager.getLogger(Historique.class);

    /**
     * Créer et initialise une collection
     * @return collection vide
     */
    public Map<Integer,String> initialiser(int nombreDeCase){
        logger.trace("début initialistion map historique");

        Map<Integer,String> historique = new HashMap<>(nombreDeCase);
        for (int i=0; i<nombreDeCase; i++)
            historique.put(i,"");
        logger.debug("état de la map : " +historique);

        logger.trace("fin initialistion map historique");
        return historique;
    }

    /**
     * Ajoute une valeur à une clé dans la collection
     * @param historique historique des réponses déja générées
     * @param cle clé de la map historique
     * @param ajout valeur à ajouter dans la map
     * @return historique mis à jour
     */
    public Map<Integer,String> ajouter(Map<Integer,String> historique,Integer cle, String ajout){
        logger.trace("début d'ajout dans la map");

        if (historique.get(cle).isEmpty()) {
            historique.put(cle, ajout);
        }else {
            ajout += historique.get(cle);
            historique.replace(cle, ajout);
        }

        logger.trace("fin d'ajout dans la map");
        return historique;
    }

    /**
     * Parcours les valeur associés à une clé
     * @param historique historique à parcourir
     * @param cle la clé où récupérer les valeurs
     * @param valeur valeur à chercher
     * @return vrai si la valeur cherchée n'est pas trouvée
     */
    public boolean parcourir (Map<Integer,String> historique, Integer cle, String valeur){
        logger.trace("début de lecture de la map");

        boolean nouveau = true;
        if (historique.get(cle).contains(valeur))
            nouveau = false;

        logger.debug("valeur " +valeur+ " associée à la clé " +cle+ " est nouvelle  : " +nouveau);
        logger.trace("fin de lecture de la map");
        return nouveau;
    }

    /**
     * Lis l'historique et extrait la dernière réponse générée :
     * - 1er caractère des valeurs trouvés à chaque clés
     * @param memoire historique à lire
     * @return réponse
     */
    public String lireReponse (Map<Integer,String> memoire){
        logger.trace("début de lecture dans  la map");

        String reponse ="";
        int i;

        logger.debug("état de la map : " + memoire);
        for (i = 0; i < memoire.size(); i++)
            reponse += memoire.get(i).substring(0, 1);


        logger.debug("réponse renvoyée : " +reponse);
        logger.trace("fin de lecture dans  la map");
        return reponse;
    }

    /**
     * Parcours toutes les valeurs de la chaîne en paramêtre et en déduit le minimum
     * à renvoyer en fonction du signe qui précède le chiffre
     * => minimum = le plus grand entier précédé d'un "+"
     * @param valeurs chaîne à parcourir
     * @return le nouveau minimum
     */
    public int lireMinimum (String valeurs){
        int minimum = 0, i;
        logger.debug("chaîne lue : " + valeurs);

        if (valeurs.equals("")) {
            minimum = 0;
            logger.debug("nouveau Min : " + minimum);
        }else {
            for (i = 0; i < valeurs.length(); i += 2) {
                if ((valeurs.substring(i, i + 1)).equals("+")) {
                    logger.debug(valeurs.substring(i, i + 1));
                    logger.debug(Integer.parseInt(valeurs.substring(i + 1, i + 2)));
                    if (Integer.parseInt(valeurs.substring(i + 1, i + 2)) > minimum) {
                        minimum = Integer.parseInt(valeurs.substring(i + 1, i + 2));
                        logger.debug("nouveau Min : " + minimum);
                    }
                }
            }
        }
        return minimum;
    }

    /**
     * Parcours toutes les valeurs de la chaîne en paramêtre et en déduit le maximum
     * à renvoyer en fonction du signe qui précède le chiffre
     * => maximum = le plus petit entier précédé d'un "-"
     * @param valeurs chaîne à parcourir
     * @return le nouveau maximum
     */
    public int lireMaximum (String valeurs){
        int maximum = 9, i;
        logger.debug("chaîne lue : " + valeurs);

        if (valeurs.equals("")){
            maximum = 9;
            logger.debug("nouveau Max : " + maximum);
        }else {
            for (i = 0; i < valeurs.length(); i += 2) {
                if ((valeurs.substring(i, i + 1)).equals("-")) {
                    logger.debug(valeurs.substring(i, i + 1));
                    logger.debug(Integer.parseInt(valeurs.substring(i + 1, i + 2)));
                    if (Integer.parseInt(valeurs.substring(i + 1, i + 2)) < maximum) {
                        maximum = Integer.parseInt(valeurs.substring(i + 1, i + 2));
                        logger.debug("nouveau Max : " + maximum);
                    }
                }
            }
        }
        return maximum;
    }
}
