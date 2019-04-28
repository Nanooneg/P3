package com.nanoo.p3.gestion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestion {

    private static final Logger logger = LogManager.getLogger(Gestion.class);

    private Scanner sc = new Scanner(System.in);

    /**
     * Affiche une présentation du jeu
     */
    public void presenter(){
        logger.trace("affichage de la présentation du jeu");

        System.out.println();
        System.out.println();
        System.out.println("             Bienvenue dans le jeu <<< Recherche +/- >>>              ");
        System.out.println("       Trouver la bonne Combinaison en un nombre maximal d'essai      ");
        System.out.println();
        System.out.println("       A chaque coup, si la bonne réponse n'est pas découverte        ");
        System.out.println("                Je donne des indices dans un modèle :                 ");
        System.out.println();
        System.out.println("             + veut dire que le chiffre est trop petit                ");
        System.out.println("             - veut dire que le chiffre est trop grand                ");
        System.out.println("                 = veut dire que le chiffre est bon                   ");
        System.out.println();
        System.out.println();

    }

    /**
     * Affiche un menu de selection des modes de jeu et enregistre la saisie du Joueur
     * @return le choix du mode de jeu
     */
    public int choixMode(){
        logger.trace("ouverture menu \"modes\"");
        logger.debug("1=challenger 2=défenseur 3=duel");

        int choix = 0;
        boolean saisieOk;

        System.out.println("3 modes de jeu possibles : ");
        System.out.println("1 - Challenger -> Vous essayez de trouver la combinaison de L'IA");
        System.out.println("2 - Défenseur -> L'IA essaie de découvrir votre combinaison");
        System.out.println("3 - Duel -> Le mix des deux ! Vous jouez à tour de rôle avec L'IA");
        System.out.println();
        System.out.print("Que souhaites-tu faire (1/2/3) : ");

        do {
            try {
                choix = sc.nextInt();
                logger.debug("Saisie utilisateur : " +choix);
                saisieOk = (choix >= 1 && choix <= 3);   // Saisie valide si elle correspond à un choix de menu existant
            } catch (InputMismatchException e) {
                sc.next();
                logger.error("Erreur de saisie !!");
                saisieOk = false;   // Si exception, la saisie n'est pas valide (saisie != d'un chiffre)
            }
            if (!saisieOk) {
                System.out.println("Erreur de saisie");
                System.out.print("Que souhaites-tu faire (1/2/3) : ");
            }
        }while (!saisieOk);   // on continue de demander un choix à l'utilisateur tant que la saisie n'est pas valide

        this.decor("double",true,true);

        logger.debug("choix renvoyé : " +choix);
        logger.trace("fermeture du menu \"modes\"");
        return choix;
    }

    /**
     * Compare la combinaison entrée par le défenseur avec celle de l'attaquant
     * fait un model de cette combinaison en remplacant les chiffres :
     * + si le chiffre est plus grand que l'original
     * - si le chiffre est plus petit que l'original
     * = si le chiffre est le bon
     * @param combinaison combinaison de l'attaquant
     * @param reponse combinaison du défenseur
     * @return modéle sous forme de +=+-
     */
    public String comparer (String combinaison, String reponse){
        logger.trace("début comparaison des combinaisons");

        String modele = "";
        char[] chiffreCombinaison = combinaison.toCharArray();
        char[] chiffreReponse = reponse.toCharArray();

        for (int i = 0, taille = combinaison.length(); i<taille; i++) {
            if (chiffreCombinaison[i] < chiffreReponse[i])
                modele += "-";
            else if (chiffreCombinaison[i] > chiffreReponse[i])
                modele += "+";
            else if (chiffreCombinaison[i] == chiffreReponse[i])
                modele += "=";
        }

        logger.debug("combinaisonA : " +combinaison+ " combinaisonD : " +reponse+ " modèle renvoyé : " +modele);
        logger.trace("fin comparaison des combinaisons");
        return modele;
    }

    /**
     * Affiche un menu à la fin d'une partie qui propose de : rejouer, changer de jeu ou sortir
     * @param modeDeJeu mode actuel
     * @return nouveau mode de jeu (4 étant la sortie du programme et 0 un reboot)
     */
    public int choixRejouer (int modeDeJeu){
        logger.trace("ouverture menu \"rejouer\"");
        logger.debug("1=rejouer 2=retour menu 3=quitter");

        int choix = 0;
        boolean saisieOk;

        System.out.println("\nMaintenant tu peux : ");
        System.out.println("1 - Rejouer au même jeu");
        System.out.println("2 - Retourner à l'écran de séléction");
        System.out.println("3 - Nous quitter ... :( ");
        System.out.print("Que souhaites-tu faire (1/2/3) : ");

        do {
            try {
                choix = sc.nextInt();
                logger.debug("Saisie utilisateur : " +choix);
                saisieOk = (choix >= 1 && choix <= 3);   // Saisie valide si elle correspond à un choix de menu existant
            } catch (InputMismatchException e) {
                sc.next();
                logger.error("Erreur de saisie !!");
                saisieOk = false;   // Si exception, la saisie n'est pas valide (saisie != d'un chiffre)
            }
            if (!saisieOk) {
                System.out.println("Erreur de saisie");
                System.out.print("Que souhaites-tu faire (1/2/3) : ");
            }
        }while (!saisieOk);   // on continue de demander un choix à l'utilisateur tant que la saisie n'est pas valide

        //en finction du choix de l'utilisateur, on change la valeur de mode de jeu (ou non)
        switch (choix){
            case 1:   // même mode de jeu = rejouer
                this.decor("double",true,true);
                break;
            case 2:   // retour au menu
                modeDeJeu = 0;
                this.decor("double",true,true);
                break;
            case 3:   // sortie
                modeDeJeu = 4;
                break;
        }

        logger.trace("fermeture du menu \"rejouer\"");
        return modeDeJeu;
    }

    /**
     * Affiche des éléments de décor
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
        logger.trace("affichage message d'au revoir");

        System.out.println("\nMerci et à bientôt");
    }
}
