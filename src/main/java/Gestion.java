import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestion {

     private Scanner sc = new Scanner(System.in);

    /**
     * affiche une présentation du jeu recherche +/-
     */
    public void presenter(){
        this.couleurPolice(3);
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
        System.out.print("Que souhaites-tu faire (1/2/3) : ");

        do {
            try {
                choix = sc.nextInt();
                saisieOk = (choix >= 1 && choix <= 3);
            } catch (InputMismatchException e) {
                sc.next();
                saisieOk = false;
            }
            if (!saisieOk) {
                this.couleurPolice(4);
                System.out.println("Erreur de saisie");
                this.couleurPolice(3);
                System.out.print("Que souhaites-tu faire (1/2/3) : ");
            }
        }while (!saisieOk);

        this.decor("double",true,true);

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
            try {
                choix = sc.nextInt();
                saisieOk = (choix >= 1 && choix <= 3);
            } catch (InputMismatchException e) {
                sc.next();
                saisieOk = false;
            }
            if (!saisieOk) {
                this.couleurPolice(4);
                System.out.println("Erreur de saisie");
                this.couleurPolice(3);
                System.out.print("Que souhaites-tu faire (1/2/3) : ");
            }
        }while (!saisieOk);

        switch (choix){
            case 1:
                this.decor("double",true,true);
                break;
            case 2:
                modeDeJeu = 0;
                this.decor("double",true,true);
                break;
            case 3:
                modeDeJeu = 4;
                break;
        }
        return modeDeJeu;
    }

    /**
     * Change la couleur de la police de la console en fonction du cas
     * - bleu : joueur humain joue
     * - jaune : IA joue
     * - blanc : menu
     * @param cas cas sités au-dessus
     */
    public void couleurPolice (int cas){
        switch (cas){
            case 1:
                System.out.print("\033[34m");   //bleu
                break;
            case 2:
                System.out.print("\033[33m");   //jaune
                break;
            case 3:
                System.out.print("\033[30m");   //blanc
                break;
            case 4:
                System.out.print("\033[31m");   //rouge
        }
    }

    /**
     * dispose des éléments de décor au cours du déroulement du jeu
     * Purement esthétique !!!
     * @param cas simple ou double ligne
     * @param retourAvant sauter une ligne avant
     * @param retourApres sauter une ligne après
     */
    public void decor (String cas, boolean retourAvant, boolean retourApres) {
        switch (cas) {
            case "double":
                if (retourAvant)
                    System.out.println("\n");
                System.out.println("====================================");
                if (retourApres)
                    System.out.println("\n");
                break;
            case "simple":
                if (retourAvant)
                    System.out.println("\n");
                System.out.println("------------------------------------");
                if (retourApres)
                    System.out.println("\n");
                break;
        }
    }

    /**
     * Message de fin
     */
    public void auRevoir(){
        System.out.println("\nMerci et à bientôt");
    }
}
