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

        logger.debug("la map doit contenir " +nombreDeCase+ " couple(s) clé/valeur");
        Map<Integer,String> historique = new HashMap<>(nombreDeCase);
        for (int i=0; i<nombreDeCase; i++)
            historique.put(i,"");
            logger.debug("état de la map : " +historique);

        logger.trace("fin initialistion map historique");
        logger.debug("map renvoyée : " +historique);
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
            logger.debug("état de la map avant ajout : " + historique);
            historique.put(cle, ajout);
            logger.debug("état de la map aprés ajout : " + historique);
        }else {
            logger.debug("état de la map avant ajout : " + historique);
            ajout += historique.get(cle);
            historique.replace(cle, ajout);
            logger.debug("état de la map aprés ajout : " + historique);
        }

        logger.trace("fin d'ajout dans la map");
        logger.debug("état de la map : " +historique);
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

        logger.trace("fin de lecture de la map");
        logger.debug("valeur " +valeur+ " associée à la clé " +cle+ " est nouvelle  : " +nouveau);
        return nouveau;
    }

    /**
     * Lis l'historique et extrait la dernière réponse générée :
     * - 1er caractére des valeurs trouvés à la clé i
     * - 1er caractère des valeurs trouvés à toutes les clés
     * @return réponse
     */
    public String lireReponse (Map<Integer,String> memoire, int cle){
        logger.trace("début de lecture dans  la map");

        String reponse ="";
        int i;
        if (cle == memoire.size()) {
            logger.debug(memoire.size()+ " premiers chiffres de chaque clé à lire");
            logger.debug("état de la map : " +memoire);
            for (i = 0; i < cle; i++) {
                reponse += memoire.get(i).substring(0,1);
                logger.debug("chiffre " +(i+1)+ " : " +reponse);
            }
            logger.debug("réponse renvoyée : " +reponse);
        }else{
            logger.debug("état de la map à la clé " +cle+ " : " +memoire.get(cle));
            reponse = memoire.get(cle).substring(0,1);
            logger.debug("chiffre lu : " +reponse);
        }

        logger.trace("fin de lecture dans  la map");
        return reponse;
    }
}
