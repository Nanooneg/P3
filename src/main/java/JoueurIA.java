public class JoueurIA extends Joueur {

    /**
     * Crée une Combinaison aléatoire
     * @param nombreChiffre nombre de case que comporte la Combinaison à générer
     * @return la Combinaison générée
     */
    @Override
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

    /**
     * Génére un réponse en fonction de la réponse précédante et du modéle ("+-+=")
     * @param coupRestant nombre de coup(s) restant
     * @param coupMax nombre de coup(s) alloués au début du jeu
     * @param modele modèle sous la forme "+-+=" retourné aprés la comparaison de la réponse précédante
     * @param reponsePrecedante réponse générée au tour précédant
     * @return réponse générée
     */
    public String genererReponse(int coupRestant, int coupMax, String modele, String reponsePrecedante) {
        String combinaison = "";
        char[] caractereModele = modele.toCharArray();
        char[] chiffreReponsePrecedante = reponsePrecedante.toCharArray();
        int nombreChiffre = modele.length();
        int i, min, max;

        if (reponsePrecedante == null){
            for (i=0; i<nombreChiffre; i++)
                combinaison += "5";
            System.out.print("J'ai" +coupMax+ " coup(s) pour trouver :");
        }else{
            for (i=0; i<nombreChiffre; i++){
                switch (caractereModele[i]){
                    case '+':
                        min = chiffreReponsePrecedante[i];
                        max = 9;
                        combinaison += String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
                        break;
                    case '-':
                        min = 0;
                        max = chiffreReponsePrecedante[i];
                        combinaison += String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
                        break;
                    case '=':
                        combinaison += chiffreReponsePrecedante[i];
                        break;
                }
            }
            System.out.print("Il me reste " +coupRestant+ " coup(s) :");
        }

        return combinaison;
    }

}
