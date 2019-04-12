package joueur;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.Map;

public class JoueurIA extends Joueur {

    Historique historique = new Historique();

    /**
     * Crée une Combinaison aléatoire
     * @param nombreChiffre nombre de case que comporte la Combinaison à générer
     * @return la Combinaison générée
     */
    @Override
    public  String genererCombinaison(int coupMax, int nombreChiffre) {
        int combinaisonRandom[]= new int[nombreChiffre];
        int min = 0, max = 9;
        int i;
        // choix aleatoire de chiffres pour chaques cases de la Combinaison à définir
        for (i=0; i<nombreChiffre; i++) {
            combinaisonRandom[i] = min + (int) (Math.random() * ((max - min) + 1));
        }
        // lecture de chaques valeurs de case et ajout à la chaine de caractére à renvoyer
        String combinaison = "";
        for (i=0; i<nombreChiffre; i++) {
            combinaison += String.valueOf(combinaisonRandom[i]);
        }
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
        String temp;
        char[] caractereModele = modele.toCharArray();

        int i, min, max;
        if (memoire.get(0).isEmpty()){
            for (i=0; i<memoire.size(); i++) {
                historique.ajouter(memoire,i,"5");
            }
            System.out.println("J'ai " +coupMax+ " coup(s) pour trouver : ");
        } else {
            for (i=0; i<memoire.size(); i++){
                switch (caractereModele[i]){
                    case '+' :
                        //récupère le dernier chiffre généré dans la réponse précédante
                        min = Integer.valueOf(historique.lireReponse(memoire,i));
                        max = 9;
                        do {
                            temp = String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
                        }while (!historique.parcourir(memoire,i,temp));
                        historique.ajouter(memoire,i,temp);
                        break;
                    case '-' :
                        min = 0;
                        max = Integer.valueOf(historique.lireReponse(memoire,i));
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
        return memoire;
    }


}
