import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class JoueurIA extends Joueur {

    static final Logger logger = LogManager.getLogger(JoueurIA.class);

    Historique historique = new Historique();

    /**
     * Crée une Combinaison aléatoire
     * @param nombreChiffre nombre de case que comporte la Combinaison à générer
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
            logger.debug(memoire.size()+ " valeur(s) à ajouter à la mémoire :");
            for (i=0; i<memoire.size(); i++) {
                logger.debug("ajouts à la mémoire -> clé : " +i+ " valeur : 5");
                historique.ajouter(memoire,i,"5");
            }
            System.out.println("J'ai " +coupMax+ " coup(s) pour trouver : ");
        } else {
            logger.debug(memoire.size()+ " valeur(s) à ajouter à la mémoire :");
            for (i=0; i<memoire.size(); i++){
                switch (caractereModele[i]){
                    case '+' :
                        //récupère le dernier chiffre généré dans la réponse précédante
                        min = Integer.valueOf(historique.lireReponse(memoire,i));
                        max = 9;
                        do {
                            temp = String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
                        }while (!historique.parcourir(memoire,i,temp));

                        logger.debug("ajouts à la mémoire -> clé : " +i+ " valeur : " +temp);
                        historique.ajouter(memoire,i,temp);
                        break;
                    case '-' :
                        min = 0;
                        max = Integer.valueOf(historique.lireReponse(memoire,i));
                        do {
                            temp = String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
                        }while (!historique.parcourir(memoire,i,temp));

                        logger.debug("ajouts à la mémoire -> clé : " +i+ " valeur : " +temp);
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
