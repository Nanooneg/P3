package com.nanoo.p3.joueur;

import com.nanoo.p3.jeu.Historique;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class JoueurIA extends Joueur {

    static final Logger logger = LogManager.getLogger(JoueurIA.class);

    Historique historique = new Historique();

    /**
     * Crée une Combinaison aléatoire
     * @param coupMax nombre de coup(s) max pour trouver la bonne réponse
     * @param nombreChiffre nombre de chiffres que doit comporter la combinaison
     * @return la Combinaison générée
     */
    @Override
    public  String genererCombinaison(int coupMax, int nombreChiffre) {
        logger.trace("début de demande à l'IA de générer une combinaison");

        int combinaisonRandom[]= new int[nombreChiffre];
        int min = 0, max = 9;
        int i;
        logger.debug(nombreChiffre+ " chiffre à générer aléatoirement :");
        // choix aleatoire de chiffres pour chaques cases de la Combinaison à définir
        for (i=0; i<nombreChiffre; i++) {
            combinaisonRandom[i] = min + (int) (Math.random() * ((max - min) + 1));
            logger.debug("chiffre " +(i+1)+ " généré : " +combinaisonRandom[i]);
        }
        // lecture de chaques valeurs de case et ajout à la chaine de caractére à renvoyer
        String combinaison = "";
        for (i=0; i<nombreChiffre; i++) {
            combinaison += String.valueOf(combinaisonRandom[i]);
        }

        logger.trace("fin de demande à l'IA de générer une combinaison");
        logger.debug("combinaison renvoyée : " +combinaison);
        return combinaison;
    }

    /**
     * Génére une réponse en fonction de la réponse précédante et du modéle ("+-+=")
     * @param coupRestant nombre de coup(s) restant
     * @param coupMax nombre de coup(s) alloués au début du jeu
     * @param modele modèle sous la forme "+-+=" retourné aprés la comparaison de la réponse précédante
     * @param memoire historique des reponses précédantes
     * @return l'historique mis à jour
     */
    public Map<Integer,String> genererReponse(int coupRestant, int coupMax, String modele, Map<Integer,String> memoire) {
        logger.trace("début de demande à l'IA de générer une réponse");

        String temp;
        char[] caractereModele = modele.toCharArray();

        int i, min, max;
        if (memoire.get(0).isEmpty()){
            for (i=0; i<memoire.size(); i++) {
                historique.ajouter(memoire,i,"5");
            }
            System.out.println("J'ai " +coupMax+ " coup(s) pour trouver : ");
        } else {
            logger.debug(memoire.size()+ " valeur(s) à ajouter à la mémoire :");
            for (i=0; i<memoire.size(); i++){
                switch (caractereModele[i]){
                    case '+' :
                        historique.ajouter(memoire,i,"+");
                        min = historique.lireMinimum(memoire.get(i));
                        max = historique.lireMaximum(memoire.get(i));
                        do {
                            temp = String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
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
