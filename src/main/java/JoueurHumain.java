import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JoueurHumain extends Joueur {

    static final Logger logger = LogManager.getLogger(JoueurHumain.class);

    Scanner sc = new Scanner(System.in);
    Gestion gestion = new Gestion();

    /**
     * Demande à l'attaquant humain de saisir un combinaison que le défenseur IA devra trouver en X coup(s)
     * @param coupMax nombre de coup(s) max pour trouver la bonne réponse
     * @return la combinaison à trouver
     */
    @Override
    public String genererCombinaison(int coupMax, int nombreChiffre) {
        logger.trace("début de demande à l'utilisateur de donner une combinaison");

        boolean saisieOk = true;
        int temp;
        String combinaison ="";
        System.out.println("Choisi une Combinaison de " +nombreChiffre+" chiffres que je devrai trouver en moins de " +coupMax+ " coups.");
        do {
            try {
                temp = sc.nextInt();
                logger.debug("Saisie utilisateur : " +temp);
                combinaison = String.valueOf(temp);
                saisieOk = (combinaison.length() == nombreChiffre);
            } catch (InputMismatchException e) {
                sc.next();
                logger.error("Erreur de saisie !!");
                saisieOk = false;
            }
            if (!saisieOk) {
                gestion.couleurPolice(4);
                System.out.println("Erreur de saisie!");
                gestion.couleurPolice(3);
                System.out.println("Choisi une Combinaison de " +nombreChiffre+" chiffres que je devrai trouver en moins de " +coupMax+ " coups.");
            }
        }while (!saisieOk);
        System.out.println("");

        logger.trace("fin de demande à l'utilisateur de donner une combinaison");
        logger.debug("réponse renvoyé : " +combinaison);
        return combinaison;
    }

    /**
     * Annonce au défenseur combien il lui reste de coup(s) et lui demande de saisir une réponse
     * @param coupRestant nombre de coup(s) restant
     * @param coupMax nombre de coup(s) alloués au début du jeu
     * @param nombreChiffre nombre de chiffre qui compose la Combinaison
     * @return la Combinaison saisie
     * */
    public String genererReponse(int coupRestant, int coupMax, int nombreChiffre) {
        logger.trace("début de demande à l'utilisateur de donner une réponse");
        boolean saisieOk = true;
        int temp = 0;
        String reponse ="";
        if (coupRestant == coupMax) {
            System.out.println("Tu dois trouver ma Combinaison de " +nombreChiffre+ " chiffre(s) en moins de " + coupMax + " coup(s)");
        } else if (coupRestant == 1){
            System.out.println("Dernier coup !!! Saisi une Combinaison de " + nombreChiffre + " chiffres :");
        } else {
            System.out.println("Il te reste " + coupRestant + " coup(s)");
            System.out.println("Saisi une Combinaison de " + nombreChiffre + " chiffres :");
        }

        do {
            if (!saisieOk)
                System.out.println("Erreur de saisie!");
            try {
                temp = sc.nextInt();
                logger.debug("Saisie utilisateur : " +temp);
                reponse = String.valueOf(temp);
                saisieOk = (reponse.length() == nombreChiffre);
            } catch (InputMismatchException e) {
                sc.next();
                logger.error("Erreur de saisie !!");
                saisieOk = false;
            }
        }while (!saisieOk);
        logger.trace("fin de demande à l'utilisateur de donner une réponse");
        logger.debug("réponse renvoyé : " +reponse);
        return reponse;
    }

}
