package joueur;

import java.util.HashMap;
import java.util.Map;

public class Historique {
    /**
     * Créer et initialise une collection
     * @return collection vide
     */
    public Map<Integer,String> initialiser(int nombreDeCase){
        Map<Integer,String> historique = new HashMap<>(nombreDeCase);
        for (int i=0; i<nombreDeCase; i++)
            historique.put(i,"");
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
        if (historique.get(cle).isEmpty())
            historique.put(cle,ajout);
        else
            ajout += historique.get(cle);
            historique.replace(cle,ajout);
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
        boolean nouveau = true;
        if (historique.get(cle).contains(valeur))
            nouveau = false;
        return nouveau;
    }
}
