package com.nanoo.p3.joueur;

import com.nanoo.p3.gestion.Gestion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JoueurHumain extends Joueur {

    private static final Logger logger = LogManager.getLogger(JoueurHumain.class);

    private Scanner sc = new Scanner(System.in);
    private Gestion gestion = new Gestion();

    /**
     * Demande à l'attaquant humain de saisir une combinaison que le défenseur devra trouver
     * @param coupMax nombre de coup(s) max pour trouver la bonne réponse
     * @param nombreChiffre nombre de chiffre(s) qui conposent la combinaison
     * @return la combinaison saisie
     */
    @Override
    public String genererCombinaison(int coupMax, int nombreChiffre) {
        logger.trace("début de demande à l'utilisateur de donner une combinaison");

        boolean saisieOk;
        String combinaison ="";

        System.out.println("Choisi une Combinaison de " +nombreChiffre+ " chiffre(s) que je devrai trouver en moins de " +coupMax+ " coup(s).");
        do {
            try {
                combinaison = sc.nextLine();
                logger.debug("Saisie utilisateur : " +combinaison);
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
                System.out.println("Choisi une Combinaison de " +nombreChiffre+ " chiffre(s) que je devrai trouver en moins de " +coupMax+ " coup(s).");
            }
        // On demande une combinaison tant qu'une réponse valide n'est pas saisie
        }while (!saisieOk);
        System.out.print("\n");

        logger.trace("fin de demande à l'utilisateur de donner une combinaison");
        return combinaison;
    }

    /**
     * Annonce au défenseur combien il lui reste de coup(s) et lui demande de saisir une réponse
     * @param coupRestant nombre de coup(s) restant
     * @param coupMax nombre de coup(s) alloués au début du jeu
     * @param nombreChiffre nombre de chiffre qui composent la Combinaison
     * @return la Combinaison saisie
     * */
    public String genererReponse(int coupRestant, int coupMax, int nombreChiffre) {
        logger.trace("début de demande à l'utilisateur de donner une réponse");
        boolean saisieOk;
        String reponse ="";

        // Premier essai
        if (coupRestant == coupMax) {
            System.out.println("Tu dois trouver ma Combinaison de " +nombreChiffre+ " chiffre(s) en moins de " + coupMax + " coup(s)");
        // Dernier essai
        } else if (coupRestant == 1){
            System.out.println("Dernier coup !!! Saisi une Combinaison de " + nombreChiffre + " chiffre(s) :");
        // Les autres essaies
        } else {
            System.out.println("Il te reste " + coupRestant + " coup(s)");
            System.out.println("Saisi une Combinaison de " + nombreChiffre + " chiffre(s) :");
        }

        do {
            try {
                reponse = sc.nextLine();
                logger.debug("Saisie utilisateur : " +reponse);
                saisieOk = (reponse.length() == nombreChiffre);
            } catch (InputMismatchException e) {
                sc.next();
                logger.error("Erreur de saisie !!");
                saisieOk = false;
            }
            if (!saisieOk) {
                gestion.couleurPolice(4);
                System.out.println("Erreur de saisie!");
                gestion.couleurPolice(1);
                System.out.println("Saisi une combinaison de " + nombreChiffre + " chiffre(s) :");
            }
        }while (!saisieOk);

        logger.trace("fin de demande à l'utilisateur de donner une réponse");
        return reponse;
    }

}
