import java.util.Scanner;

public class Combinaison {

    private Scanner sc = new Scanner(System.in);


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
     * Demande a l'attaquant de saisir une Combinaison que le defenseur devra trouver
     * @param nbr nombre de coup(s) pour trouver sa Combinaison
     * @return la Combinaison saisie
     *
     *  - UTILISEE DANS LA PREMIERE VERSION -
     */
    public String demanderAttaquant(int nbr) {
        System.out.println("Veuillez saisir une Combinaison de 4 chiffres que le joueur défenseur devra trouver en moins de " +nbr+ " coups.");
        System.out.println("C'est à vous : ");
        String strA = sc.nextLine();
        return strA;
    }

    /**
     * Demande au defenseur de saisir une Combinaison et lui annonce combien il lui reste de coup(s)
     * @param coupRestant nombre de coup(s) restant
     * @param coupMax nombre de coup(s) alloués au début du jeu
     * @param nombreChiffre nombre de chiffre qui compose la Combinaison
     * @return la Combinaison saisie
     */
    public String demanderDefenseur(int coupRestant, int coupMax, int nombreChiffre) {
        if (coupRestant == coupMax)
            System.out.println("Vous devez trouver la Combinaison du joueur attaquant en moins de " +coupRestant+ " coup(s)");
        else
            System.out.println("Il vous reste " + coupRestant + " coup(s)");

        System.out.println("Veuillez saisir une Combinaison de " +nombreChiffre+ " chiffres :");
        String strD = sc.nextLine();
        return strD;
    }

    /**
     * Comapare la Combinaison entrée par le defenseur avec celle de l'attaquant
     * fait un model de cette Combinaison en remplacant les chiffre :
     * + si le chiffre est plus grand que l'original
     * - si le chiffre est plus petit que l'original
     * = si le chiffre est le bon
     * @param combinaison Combinaison à prendre en modèle
     * @param reponse Combinaison à comparer au modéle
     * @param level nombre de case de la Combinaison
     * @return modéle sous forme de +=+-
     */
    public String comparer (String combinaison, String reponse, int level){

        String modele = "";
        char[] chiffreCombinaison = combinaison.toCharArray();
        char[] chiffreReponse = reponse.toCharArray();

        int i;
        for (i=0; i<level; i++) {
            if (chiffreCombinaison[i] < chiffreReponse[i])
                modele += "-";
            else if (chiffreCombinaison[i] > chiffreReponse[i])
                modele += "+";
            else if (chiffreCombinaison[i] == chiffreReponse[i])
                modele += "=";
        }

        return modele;
    }

    /**
     * Crée une Combinaison aléatoire
     * @param nombreDeCase nombre de case que comporte la Combinaison à générer
     * @return la Combinaison générée
     */
    public String genererCombinaison (int nombreDeCase){

        int combinaisonRandom[]= new int[nombreDeCase];
        int min = 0, max = 9;
        int i;
        // choix aleatoire de chiffres pour chaques cases de la Combinaison à définir
        for (i=0; i<nombreDeCase; i++) {
            combinaisonRandom[i] = min + (int) (Math.random() * ((max - min) + 1));
        }
        // lecture de chaques valeurs de case et ajout à la chaine de caractére à renvoyer
        String nombreAleatoire = "";
        for (i=0; i<nombreDeCase; i++) {
            nombreAleatoire += String.valueOf(combinaisonRandom[i]);
        }
        return nombreAleatoire;
    }

}
