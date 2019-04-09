public class Presentation {
    /**
     * affiche une présentation du jeu recherche +/-
     */
    public static void presenter(){
        System.out.print("\033[30m");
        System.out.println("");
        System.out.println("");
        System.out.println("             Bienvenur dans le jeu <<< Recherche +/- >>>              ");
        System.out.println("       Trouver la bonne Combinaison en un nombre maximal d'essai      ");
        System.out.println("");
        System.out.println("      A chaque coup, si vous n'avez pas trouveé la bonne réponse,     ");
        System.out.println("         L'ordinateur vous donne des indices dans un modèle :         ");
        System.out.println("");
        System.out.println("             + veut dire que le chiffre est trop petit                ");
        System.out.println("             - veut dire que le chiffre est trop grand                ");
        System.out.println("                 = veut dire que le chiffre est bon                   ");
        System.out.println("                    C'est à vous !! bonne chance                      ");
        System.out.println("");
        System.out.println("");
    }

}
