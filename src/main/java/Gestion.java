import java.util.Random;
import java.util.Scanner;

public class Gestion {

    Scanner sc = new Scanner(System.in);

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

    /**
     * Propose à la fin d'une partie de : rejouer, changer de jeu ou sortir
     * @param modeDeJeu mode actuel / partie précédante
     * @return nouveau mode de jeu (4 étant la sortie du programme et 0 un reboot)
     */
    public int choixRejouer(int modeDeJeu){
        System.out.print("\033[30m");   //police en Blanc
        System.out.println("\nVous pouvez : ");
        System.out.println("1 - Rejouer au même jeu");
        System.out.println("2 - Retourner à l'écran de séléction");
        System.out.println("3 - Nous quitter ... :( ");
        System.out.print("Que souhaitez-vous faire (1/2/3) : ");
        int demandeRejouer = sc.nextInt();
        switch (demandeRejouer){
            case 1:
                System.out.println("\n====================================\n");
                break;
            case 2:
                modeDeJeu = 0;
                System.out.println("\n====================================\n");
                break;
            case 3:
                modeDeJeu = 4;
                break;
        }
        return modeDeJeu;
    }
}
