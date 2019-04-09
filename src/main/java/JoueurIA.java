public class JoueurIA extends Joueur {

    /**
     * Crée une Combinaison aléatoire
     * @param nombreChiffre nombre de case que comporte la Combinaison à générer
     * @return la Combinaison générée
     */
    public String genererCombinaison(int coupMax, int nombreChiffre) {
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

    public String genererReponse(int coupRestant, int coupMax, int nombreChiffre) {
        return null;
    }
}
