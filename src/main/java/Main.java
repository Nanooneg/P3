public class Main {

    public static void main(String[] args) {

        Mode mode = new Mode();
        Gestion gestion = new Gestion();

        //récupération des paramétres chargés
        Chargement chargement = new Chargement();
        int CASE_COMBINAISON = chargement.getCaseCombinaison();
        int COUP_MAX = chargement.getCoupMax();
        boolean DEVELOPPEUR = chargement.isDeveloppeur();

        //mode de jeu
        int modeDeJeu = 0; // 1 - Challenger   2 - Défenseur   3 - Duel  (4 - sortie du programme, seulement proposé au moment de rejouer)

        //présentation du jeu
        gestion.presenter();

        do {
            if (modeDeJeu==0)
                modeDeJeu = gestion.choixMode();
            switch (modeDeJeu) {
                case 1:
                    mode.challenger(COUP_MAX,CASE_COMBINAISON,DEVELOPPEUR);
                    break;
                case 2:
                    mode.defenseur(COUP_MAX,CASE_COMBINAISON,DEVELOPPEUR);
                    break;
                case 3:
                    mode.duel(COUP_MAX,CASE_COMBINAISON,DEVELOPPEUR);
                    break;
            }
            //proposition de rejouer
            modeDeJeu = gestion.choixRejouer(modeDeJeu);
        }while (modeDeJeu != 4);

        gestion.auRevoir();

    }
}