import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    //log class Main
    static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        //début du jeu
        logger.info("début du jeu");

        Mode mode = new Mode();
        Gestion gestion = new Gestion();

        //récupération des paramétres chargés
        Chargement chargement = new Chargement();
        int CASE_COMBINAISON = chargement.getCaseCombinaison();
        int COUP_MAX = chargement.getCoupMax();
        boolean DEVELOPPEUR = chargement.isDeveloppeur();
        logger.trace("Chargement des paramètres");
        logger.debug("caseCombinaison = " +CASE_COMBINAISON);
        logger.debug("coupMax = " +COUP_MAX);
        logger.debug("modeDeveloppeur = " +DEVELOPPEUR);

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

        //fin du jeu
        gestion.auRevoir();
        logger.info("fin du jeu");

    }
}