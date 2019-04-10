import java.util.Scanner;

public class Presentation {
    /**
     * affiche une présentation du jeu recherche +/-
     */
    public void presenter(){
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

    /**
     *Propose de choisir le mode de jeu
     * @return le choix du mode de jeu
     */
    public int choixMode(){
        int choix = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("3 modes de jeu possibles : ");
        System.out.println("1 - Challenger -> Vous essayez de trouver la combinaison de L'IA");
        System.out.println("2 - Défenseur -> L'IA essai de découvrir votre combinaison");
        System.out.println("3 - Duel -> Le mix des deux ! Vous jouez à tour de rôle avec L'IA");
        System.out.println("");
        System.out.print("Que choisissez-vous ? (1/2/3) : ");
        choix = sc.nextInt();
        System.out.println("\n");

        return choix;
    }

}
