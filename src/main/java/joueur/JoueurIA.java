package joueur;

public class JoueurIA extends Joueur {

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
     * @param reponsePrecedante réponse générée au tour précédant
     * @param nombreChiffre nombre de chiffre qui composent la réponse à générer
     * @return réponse générée
     */
    public String genererReponse(int coupRestant, int coupMax, String modele, String reponsePrecedante, int nombreChiffre) {
        String combinaison ="";
        char[] caractereModele = modele.toCharArray();
        int[] chiffreReponseP = new int[reponsePrecedante.length()];
        for (int i=0; i<reponsePrecedante.length(); i++)
        {
            chiffreReponseP[i]=Character.digit(reponsePrecedante.charAt(i),10);
        }

        int i, min, max;

        if (reponsePrecedante.equals(combinaison)){
            for (i=0; i<nombreChiffre; i++)
                combinaison += "5";
            System.out.println("J'ai" +coupMax+ " coup(s) pour trouver : ");
        }else{
            for (i=0; i<nombreChiffre; i++){
                switch (caractereModele[i]){
                    case '+' :
                        min = chiffreReponseP[i];
                        max = 9;
                        combinaison += String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
                        break;
                    case '-' :
                        min = 0;
                        max = chiffreReponseP[i];
                        combinaison += String.valueOf(min + (int) (Math.random() * ((max - min) + 1)));
                        break;
                    case '=' :
                        combinaison += chiffreReponseP[i];
                        break;
                }
            }
            System.out.println("Il me reste " +coupRestant+ " coup(s) : ");

        }

        return combinaison;
    }

}
