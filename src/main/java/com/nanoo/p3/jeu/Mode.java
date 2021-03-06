package com.nanoo.p3.jeu;

import com.nanoo.p3.gestion.Gestion;
import com.nanoo.p3.joueur.JoueurHumain;
import com.nanoo.p3.joueur.JoueurIA;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class Mode {

    private static final Logger logger = LogManager.getLogger(Mode.class);

    //déclaration objets
    private JoueurHumain humain = new JoueurHumain();
    private JoueurIA IA = new JoueurIA();
    private Gestion gestion = new Gestion();
    private Historique historique = new Historique();

    /**
     * Lance le mode Challenger (le joueur humain doit trouver la combinaison générée par l'IA)
     * @param coupMax maximum de coup(s) alloué(s) pour trouer la bonne réponse
     * @param caseCombinaison nombre de chiffres qui composent la combinaison à trouver
     * @param developpeur mode développeur activé ou non
     */
    public void challenger(int coupMax, int caseCombinaison, boolean developpeur){
        logger.trace("début mode challenger");

        String combinaisonA = IA.genererCombinaison(coupMax, caseCombinaison);   //Combinaison Attaquant
        String combinaisonD = "";                                                //Combinaison Défenseur
        String modele = "";                                                      //Modèle ("+-+=")
        int coupRestant = coupMax;                                               //même principe pour tous les modes

        //boucle de déroulement du jeu : affichage du mogèle -> réponse du defenseur -> comparaison
        //on en sort quand la combinaison est trouvée ou qu'il ne reste plus d'essaies (même principe pour tous les modes)
        gestion.couleurPolice(1);
        while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            humain.afficherModele(modele, caseCombinaison, developpeur, combinaisonA);
            combinaisonD = humain.genererReponse(coupRestant, coupMax, caseCombinaison);
            modele = gestion.comparer(combinaisonA, combinaisonD);
            coupRestant--;   //Compteur
        }

        // fin du jeu, affichage de la solution (même principe pour tous les modes)
        gestion.couleurPolice(3);
        if (combinaisonA.equals(combinaisonD))
            if (coupMax-coupRestant == 1)
                System.out.println("\nGagné! Tu as trouvé ma combinaison en " +(coupMax-coupRestant)+ " coup !! Trop fort");
            else
                System.out.println("\nGagné! Tu as trouvé ma combinaison en " +(coupMax-coupRestant)+ " coups !!");
        else
            System.out.println("\nPerdu! Tu n'as pas trouvé ma combinaison ! C'était : " + combinaisonA + "...");

        logger.trace("fin mode challenger");
    }

    /**
     * Lance le mode Défenseur (L'IA doit trouver la combinaison donnée par l'attaquant)
     * @param coupMax maximum de coup(s) alloué(s) pour trouver la bonne réponse
     * @param caseCombinaison nombre de chiffres qui composent la combinaison à trouver
     * @param developpeur mode développeur activé ou non
     */
    public void defenseur(int coupMax, int caseCombinaison, boolean developpeur){
        logger.trace("début mode défenseur");

        String combinaisonA = humain.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonD = "";
        String modele = "";
        int coupRestant = coupMax;
        Map<Integer,String> memoire = historique.initialiser(caseCombinaison); //historique des réponses et du modèle associé

        gestion.couleurPolice(2);
        while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            IA.afficherModele(modele, caseCombinaison, developpeur, combinaisonA);
            memoire = IA.genererReponse(coupRestant, coupMax, modele, memoire);
            combinaisonD = historique.lireReponse(memoire);
            System.out.println(combinaisonD);
            modele = gestion.comparer(combinaisonA, combinaisonD);
            coupRestant--;
        }

        gestion.couleurPolice(3);
        if (combinaisonA.equals(combinaisonD))
            if (coupMax-coupRestant == 1)
                System.out.println("\nPerdu! J'ai trouvé ta combinaison en " +(coupMax-coupRestant)+ " coup !! Peux pas faire mieux ");
            else
                System.out.println("\nPerdu! J'ai trouvé ta combinaison en " +(coupMax-coupRestant)+ " coups !!");
        else
            System.out.println("\nGagné! Je n'ai pas réussi à trouver ta combinaison : " +combinaisonA+ "... ");

        logger.trace("fin mode défenseur");
    }

    /**
     * Lance le mode Duel (Le joueur humain et l'Ia jouent à tour de rôle pour
     * trouver leur combinaison respective)
     * @param coupMax maximum de coup(s) alloué(s) pour trouver la bonne réponse
     * @param caseCombinaison nombre de chiffres qui composent la combinaison à trouver
     * @param developpeur mode développeur activé ou non ( on : affiche la solution à l'écran)
     */
    public void duel(int coupMax, int caseCombinaison, boolean developpeur) {
        logger.trace("début mode duel");

        String combinaisonAHumain = IA.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonDHumain = "";
        String combinaisonAIA = humain.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonDIA = "";
        String modeleHumain = "";
        String modeleIA = "";
        int coupRestant = coupMax;
        Map<Integer,String> memoire = historique.initialiser(caseCombinaison);

        //boucle de déroulement du jeu spécifique au mode : 1 tour humain puis 1 tour IA
        while ((!combinaisonAHumain.equals(combinaisonDHumain) && coupRestant != 0) && (!combinaisonAIA.equals(combinaisonDIA) && coupRestant != 0)) {
            gestion.couleurPolice(3);
            gestion.decor("double",false,false);
            System.out.println("***Tour" +((coupMax-coupRestant)+1)+ "***");

            //---Tour humain---
            gestion.couleurPolice(1);
            humain.afficherModele(modeleHumain, caseCombinaison, developpeur, combinaisonAHumain);
            combinaisonDHumain = humain.genererReponse(coupRestant, coupMax, caseCombinaison);
            modeleHumain = gestion.comparer(combinaisonAHumain, combinaisonDHumain);
            gestion.couleurPolice(3);
            gestion.decor("simple",false,false);

            //---Tour IA---
            gestion.couleurPolice(2);
            IA.afficherModele(modeleIA, caseCombinaison, developpeur, combinaisonAIA);
            memoire = IA.genererReponse(coupRestant, coupMax, modeleIA, memoire);
            combinaisonDIA = historique.lireReponse(memoire);
            System.out.println(combinaisonDIA);
            modeleIA = gestion.comparer(combinaisonAIA, combinaisonDIA);

            //compteur
            coupRestant--;
        }

        // fin du jeu affichage des solutions
        gestion.couleurPolice(3);
        gestion.decor("double",false,true);
        if (combinaisonAHumain.equals(combinaisonDHumain) && !combinaisonAIA.equals(combinaisonDIA) && coupRestant!=0){
            if (coupMax-coupRestant == 1)
                System.out.println("Gagné! Tu as trouvé ma combinaison en " +(coupMax-coupRestant)+ " coup");
            else
                System.out.println("Gagné! Tu as trouvé ma combinaison en " +(coupMax-coupRestant)+ " coups");
            System.out.println("et je n'ai pas trouvé la tienne...Ahhh c'était " +combinaisonAIA+ " !");
        }else if (!combinaisonAHumain.equals(combinaisonDHumain) && combinaisonAIA.equals(combinaisonDIA) && coupRestant!=0){
            if (coupMax-coupRestant == 1)
                System.out.println("Perdu! J'ai trouvé ta combinaison en " +(coupMax-coupRestant)+ " coup");
            else
                System.out.println("Perdu! J'ai trouvé ta combinaison en " +(coupMax-coupRestant)+ " coups");
            System.out.println("et tu n'as pas trouvé la mienne...C'était : " +combinaisonAHumain+ " !");
        }else if (combinaisonAHumain.equals(combinaisonDHumain) && combinaisonAIA.equals(combinaisonDIA) && coupRestant!=0){
            if (coupMax-coupRestant == 1)
                System.out.println("Ex-aequo !! Nous avons trouvé nos combinaisons respectives en " +(coupMax-coupRestant)+ " coup");
            else
                System.out.println("Ex-aequo !! Nous avons trouvé nos combinaisons respectives en " +(coupMax-coupRestant)+ " coups");
        }else {
            System.out.println("Pas de perdant !! et pas de gagnant non-plus...");
            System.out.println("Ma combinaison était : " +combinaisonAHumain+ "!");
            System.out.println("Et la tienne était : " +combinaisonAIA+ " ...");
        }

        logger.trace("fin mode duel");
    }
}
