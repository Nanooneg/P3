class Combinaison {

    /**
     * affiche la Combinaison masquée
     * XXXX au départ pui +=+- en fonction des réponses du joueur défenseur
     * @param modele modéle masqué à afficher
     * @param coupRestant nombre de coup(s) restant
     * @param coupMax nombre de coup(s) alloué(s) au début du jeu
     * @param level nombre de case de la Combinaison
     * @param developpeur mode développeur on/off
     * @param combinaison Combinaison à trouver
     */
    public void afficherModele(String modele, int coupRestant, int coupMax, int level, boolean developpeur, String combinaison) {
        if (coupRestant == coupMax) {
            String modelDeBase = "";
            int i;
            for (i = 0; i < level; i++){
                modelDeBase += "X";
            }
            if (developpeur)
                System.out.println(combinaison+ " <== SOLUTION - Mode developpeur : ON");
            System.out.println(modelDeBase);
        }else {
            if (developpeur)
                System.out.println(combinaison+ " <== SOLUTION - Mode developpeur : ON");
            System.out.println(modele);
        }
    }

    /**
     * affiche la solution
     * @param combinaison Combinaison à trouver
     * @param coupRestant nombre de coup(s) restant
     * @param essai nombre d'essai du defenseur
     */
    public void afficherResultat(String combinaison, int coupRestant, int essai) {
        if (coupRestant != 0)
            System.out.println("Bravo la bonne réponse est bien " +combinaison+ " et vous l'avez trouver en " +essai+ " coup(s) !!");
        else
            System.out.println("Dommage, vous n'avez pas trouvé ! la solution était : " +combinaison+ "...");
    }

    /**
     * Comapare la Combinaison entrée par le defenseur avec celle de l'attaquant
     * fait un model de cette Combinaison en remplacant les chiffre :
     * + si le chiffre est plus grand que l'original
     * - si le chiffre est plus petit que l'original
     * = si le chiffre est le bon
     * @param combinaison Combinaison à prendre en modèle
     * @param reponse Combinaison à comparer au modéle
     * @return modéle sous forme de +=+-
     */
    public String comparer (String combinaison, String reponse){

        int nombreChiffre = reponse.length();
        String modele = "";
        char[] chiffreCombinaison = combinaison.toCharArray();
        char[] chiffreReponse = reponse.toCharArray();

        int i;
        for (i=0; i<nombreChiffre; i++) {
            if (chiffreCombinaison[i] < chiffreReponse[i])
                modele += "-";
            else if (chiffreCombinaison[i] > chiffreReponse[i])
                modele += "+";
            else if (chiffreCombinaison[i] == chiffreReponse[i])
                modele += "=";
        }

        return modele;
    }



}
