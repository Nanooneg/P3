package com.nanoo.p3.joueur;

import com.nanoo.p3.jeu.Historique;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class JoueurIA extends Joueur {

    private static final Logger logger = LogManager.getLogger(JoueurIA.class);

    private Historique historique = new Historique();

    /**
     * Crée une Combinaison aléatoire
     * @param coupMax nombre de coup(s) max pour trouver la bonne réponse
     * @param nombreChiffre nombre de chiffres qui composent la combinaison
     * @return la Combinaison générée
     */
    @Override
    public  String genererCombinaison (int coupMax, int nombreChiffre) {
        logger.trace("début de demande à l'IA de générer une combinaison");

        int[] combinaisonRandom = new int[nombreChiffre];
        int min = 0, max = 9;
        int i, taille;
        String combinaison = "";

        // choix aléatoire de chiffres pour chaques cases de la Combinaison à définir et ajout à la chaine de caractére à renvoyer
        for (i = 0, taille = nombreChiffre; i<taille; i++) {
            combinaisonRandom[i] = min + (int) (Math.random() * ((max - min) + 1));
            combinaison += String.valueOf(combinaisonRandom[i]);
            logger.debug("chiffre " +(i+1)+ " généré : " +combinaisonRandom[i]);
        }

        logger.trace("fin de demande à l'IA de générer une combinaison");
        logger.debug("combinaison renvoyée : " +combinaison);
        return combinaison;
    }

    /**
     * Génére une réponse en fonction de la réponse précédante et du modéle ("+-+=")
     * @param coupRestant nombre de coup(s) restant(s)
     * @param coupMax nombre de coup(s) alloué(s) au début du jeu
     * @param modele modèle sous la forme "+-+=" retourné aprés la comparaison de la réponse précédante
     * @param memoire historique des réponses précédantes
     * @return l'historique mis à jour
     */
    public Map<Integer,String> genererReponse (int coupRestant, int coupMax, String modele, Map<Integer,String> memoire) {
        logger.trace("début de demande à l'IA de générer une réponse");

        String temp;
        char[] caractereModele = modele.toCharArray();
        int i, taille, min, max;

        // Si l'historique est vide
        if (memoire.get(0).isEmpty()){
            for (i = 0, taille = memoire.size(); i<taille; i++) {
                // Première réponse : x fois "5" , le plus logique pour réduire les possibilités
                historique.ajouter(memoire,i,"5");
            }
            System.out.println("J'ai " +coupMax+ " coup(s) pour trouver : ");
        // Sinon en fonction du signe du modèle
        } else {
            for (i = 0, taille = memoire.size(); i<taille; i++){
                switch (caractereModele[i]){
                    case '+' :
                        // Ajout du signe du modèle à l'historique pour permettre de définir pas la suite...
                        //... les minimums et maximums les plus pertinents
                        historique.ajouter(memoire,i,"+");
                        min = historique.lireMinimum(memoire.get(i));
                        max = historique.lireMaximum(memoire.get(i));
                        do {
                            temp = String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
                        // Génère un nombre aléatoire différent tant qu'il n'est pas une nouvelle réponse
                        }while (!historique.parcourir(memoire,i,temp));
                        historique.ajouter(memoire,i,temp);
                        break;
                    case '-' :
                        historique.ajouter(memoire,i,"-");
                        min = historique.lireMinimum(memoire.get(i));
                        max = historique.lireMaximum(memoire.get(i));
                        do {
                            temp = String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
                        }while (!historique.parcourir(memoire,i,temp));
                        historique.ajouter(memoire,i,temp);
                        break;
                }
            }
            if (coupRestant == 1)
                System.out.println("Dernier coup !!");
            else
                System.out.println("Il me reste " +coupRestant+ " coup(s) : ");
        }

        logger.trace("fin de demande à l'IA de générer une réponse");
        logger.debug("map renvoyée : " +memoire);
        return memoire;
    }


}