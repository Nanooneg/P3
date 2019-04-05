import java.util.Scanner;

public class combinaison {

    private Scanner sc = new Scanner(System.in);


    /**
     * affiche la combinaison masquée
     * XXXX au départ pui +=+- en fonction des réponses du joueur défenseur
     * @param modele modéle masqué à afficher
     * @param nbr nombre de coup(s) restant
     */
    public void afficherModele(String modele, int nbr) {
        if (nbr == 20)
            System.out.println("XXXX");
        else
            System.out.println(modele);
    }

    /**
     * affiche la solution
     * @param comb combinaison de l'attaquant / solution
     * @param nbr nombre de coup(s) restant
     */
    public void afficherResultat(String comb, int nbr, int essai) {
        if (nbr != 0)
            System.out.println("Bravo la bonne réponse est bien " +comb+ " et vous l'avez trouver en " +essai+ " coup(s) !!");
        else
            System.out.println("Dommage, vous n'avez pas trouvé ! la solution était : " +comb+ "...");
    }

    /**
     * Demande a l'attaquant de saisir une combinaison que le defenseur devra trouver
     * @param nbr nombre de coup(s) pour trouver sa combinaison
     * @return la combinaison saisie
     */
    public String demanderAttaquant(int nbr) {
        System.out.println("Veuillez saisir une combinaison de 4 chiffres que le joueur défenseur devra trouver en moins de " +nbr+ " coups.");
        System.out.println("C'est à vous : ");
        String strA = sc.nextLine();
        return strA;
    }

    /**
     * Demande au defenseur de saisir une combinaison et lui annonce combien il lui reste de coup(s)
     * @param nbr nombre de coup(s) restant
     * @return la combinaison saisie
     */
    public String demanderDefenseur(int nbr) {
        if (nbr == 20)
            System.out.println("Vous devez trouver la combinaison du joueur attaquant en moins de " +nbr+ " coup(s)");
        else
            System.out.println("Il vous reste " + nbr + " coup(s)");

        System.out.println("Veuillez saisir une combinaison de 4 chiffres :");
        String strD = sc.nextLine();
        return strD;
    }

    /**
     * Comapare la combinaison entrée par le defenseur avec celle de l'attaquant
     * fait un model de cette combinaison en remplacant les chiffre :
     * + si le chiffre est plus grand que l'original
     * - si le chiffre est plus petit que l'original
     * = si le chiffre est le bon
     * @param strA combinaison à prendre en modèle
     * @param strD combinaison à comparer au modéle
     * @return modéle sous forme de +=+-
     */
    public String comparer (String strA, String strD){

        String modele = "";
        char[] chiffreModele = strA.toCharArray();
        char[] chiffreAcomparer = strD.toCharArray();

        int i;
        for (i=0; i<4; i++) {
            if (chiffreModele[i] < chiffreAcomparer[i])
                modele += "-";
            else if (chiffreModele[i] > chiffreAcomparer[i])
                modele += "+";
            else if (chiffreModele[i] == chiffreAcomparer[i])
                modele += "=";
        }

        return modele;
    }

    /**
     * Crée une combinaison aléatoire
     * @param nombreDeCase nombre de case que comporte la combinaison à générer
     * @return la combinaison aléatoire
     */
    public String randomModele (int nombreDeCase){

        int combinaisonRandom[]= new int[nombreDeCase];
        int min = 0, max = 9;
        int i;
        // choix aleatoire de chiffres pour chaques cases de la combinaison à définir
        for (i=0; i<nombreDeCase; i++) {
            combinaisonRandom[i] = min + (int) (Math.random() * ((max - min) + 1));
        }
        // lecture de chaques valeurs de case et ajout à ma chaine de caractére à renvoyer
        int j;
        String nombreAleatoire = "";
        for (j=0; j<nombreDeCase; j++) {
            nombreAleatoire += String.valueOf(combinaisonRandom[j]);
        }
        return nombreAleatoire;
    }

}
