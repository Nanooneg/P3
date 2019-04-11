import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Mode mode = new Mode();
        Gestion gestion = new Gestion();

        //récupération des paramétres chargés
        Chargement chargement = new Chargement();
        int caseCombinaison = chargement.getCaseCombinaison();
        int coupMax = chargement.getCoupMax();
        boolean developpeur = chargement.isDeveloppeur();

        //mode de jeu
        int modeDeJeu = 0; // 1 - Challenger   2 - Défenseur   3 - Duel  (4 - sortie du programme, seulement proposé au moment de rejouer)
        Scanner sc =new Scanner(System.in);

        //présentation du jeu
        gestion.presenter();

        //déclaration variables
        int coupRestant = coupMax + 1;
        int essai = 0;

        do {
            if (modeDeJeu==0)
                modeDeJeu = gestion.choixMode();
            switch (modeDeJeu) {
                //***CHALLENGER***
                case 1:
                    mode.challenger(coupRestant,coupMax,essai,caseCombinaison,developpeur);
                    break;
                //***DEFENSEUR***
                case 2:
                    mode.defenseur(coupRestant,coupMax,essai,caseCombinaison,developpeur);
                    break;
                //***DUEL***
                case 3:
                    mode.duel(coupRestant,coupMax,essai,caseCombinaison,developpeur);
                    break;
            }
            //proposition de rejouer
            modeDeJeu = gestion.choixRejouer(modeDeJeu);
        }while (modeDeJeu != 4);

        System.out.println("\nMerci et à bientôt");
    }
}