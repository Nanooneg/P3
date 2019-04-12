import jdk.internal.util.xml.impl.Input;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestion {

    Scanner sc = new Scanner(System.in);

    /**
     * affiche une présentation du jeu recherche +/-
     */
    public void presenter(){
        System.out.print("\033[30m");  //Police en Blanc
        System.out.println("");
        System.out.println("");
        System.out.println("             Bienvenur dans le jeu <<< Recherche +/- >>>              ");
        System.out.println("       Trouver la bonne Combinaison en un nombre maximal d'essai      ");
        System.out.println("");
        System.out.println("       A chaque coup, si la bonne réponse n'est pas découverte        ");
        System.out.println("                Je donne des indices dans un modèle :                 ");
        System.out.println("");
        System.out.println("             + veut dire que le chiffre est trop petit                ");
        System.out.println("             - veut dire que le chiffre est trop grand                ");
        System.out.println("                 = veut dire que le chiffre est bon                   ");
        System.out.println("");
        System.out.println("");
    }

    /**
     *Propose de choisir le mode de jeu
     * @return le choix du mode de jeu
     */
    public int choixMode(){
        int choix = 0;
        boolean saisieOk = true;

        System.out.println("3 modes de jeu possibles : ");
        System.out.println("1 - Challenger -> Vous essayez de trouver la combinaison de L'IA");
        System.out.println("2 - Défenseur -> L'IA essai de découvrir votre combinaison");
        System.out.println("3 - Duel -> Le mix des deux ! Vous jouez à tour de rôle avec L'IA");
        System.out.println("");
        System.out.print("Que choisissez-vous ? (1/2/3) : ");

        do {
            if (!saisieOk)
                System.out.println("Erreur de saisie");
                System.out.print("Que choisissez-vous ? (1/2/3) : ");
            try {
                choix = sc.nextInt();
                saisieOk = (choix >= 1 && choix <= 3);
            } catch (InputMismatchException e) {
                sc.next();
                saisieOk = false;
            }
        }while (!saisieOk);

        System.out.println("\n====================================\n");

        return choix;
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

    /**
     * Propose à la fin d'une partie de : rejouer, changer de jeu ou sortir
     * @param modeDeJeu mode actuel / partie précédante
     * @return nouveau mode de jeu (4 étant la sortie du programme et 0 un reboot)
     */
    public int choixRejouer(int modeDeJeu){
        System.out.print("\033[30m");   //police en Blanc
        int choix = 0;
        boolean saisieOk = true;
        System.out.println("\nMaintenant tu peux : ");
        System.out.println("1 - Rejouer au même jeu");
        System.out.println("2 - Retourner à l'écran de séléction");
        System.out.println("3 - Nous quitter ... :( ");
        System.out.print("Que souhaites-tu faire (1/2/3) : ");

        do {
            if (!saisieOk)
                System.out.println("Erreur de saisie");
            System.out.print("Que choisissez-vous ? (1/2/3) : ");
            try {
                choix = sc.nextInt();
                saisieOk = (choix >= 1 && choix <= 3);
            } catch (InputMismatchException e) {
                sc.next();
                saisieOk = false;
            }
        }while (!saisieOk);

        switch (choix){
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

    /**
     * Message de fin
     */
    public void auRevoir(){
        System.out.println("\nMerci et à bientôt");
    }
}
