import joueur.Historique;
import joueur.JoueurHumain;
import joueur.JoueurIA;

import java.util.Map;

public class Mode {

    //déclaration objets
    private JoueurHumain humain = new JoueurHumain();
    private JoueurIA IA = new JoueurIA();
    private Gestion gestion = new Gestion();
    private Historique historique = new Historique();

    /**
     * Lance le mode Challenger (le joueur humain doit trouver la combinaison générée par l'IA)
     * @param coupMax maximum de coup(s) alloués pour trouvé la bonne réponse
     * @param caseCombinaison nombre de chiffre qui composent la combinaison à trouver
     * @param developpeur mode développeur activé ou non ( on : affiche la solution à l'écran)
     */
    public void challenger(int coupMax, int caseCombinaison, boolean developpeur){
        String combinaisonA = IA.genererCombinaison(coupMax, caseCombinaison);   //Combinaison Attaquant
        String combinaisonD ="";                                                 //Combinaison Défenseur
        String modele ="";                                                       //Modèle ("+-+=")
        int coupRestant = coupMax;
        gestion.couleurPolice(1);
        //boucle de déroulement du jeu : affichage du mogèle -> réponse du defenseur -> comparaison
        while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            humain.afficherModele(modele, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonA);
            combinaisonD = humain.genererReponse(coupRestant, coupMax, caseCombinaison);
            modele = gestion.comparer(combinaisonA, combinaisonD);
            //Compteurs
            coupRestant--;
        }
        // fin du jeu affichage de la solution
        gestion.couleurPolice(3);
        if (combinaisonA.equals(combinaisonD))
            System.out.println("\nGagné! Tu as trouvé ma combinaison en " +(coupMax-coupRestant)+ " coup(s) !!");
        else
            System.out.println("\nPerdu! Tu n'as pas trouvé ma combinaison ! C'était : " + combinaisonA + "...");
    }

    /**
     * lance le mode Défenseur (L'IA doit trouver la combinaison donnée par le joueur humain)
     * @param coupMax maximum de coup(s) alloués pour trouvé la bonne réponse
     * @param caseCombinaison nombre de chiffre qui composent la combinaison à trouver
     * @param developpeur mode développeur activé ou non ( on : affiche la solution à l'écran)
     */
    public void defenseur(int coupMax, int caseCombinaison, boolean developpeur){
        String combinaisonA = humain.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonD ="";
        String modele ="";
        int coupRestant = coupMax;
        Map<Integer,String> memoire = historique.initialiser(caseCombinaison);
        gestion.couleurPolice(2);
        while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            IA.afficherModele(modele, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonA);
            memoire = IA.genererReponse(coupRestant, coupMax, modele, memoire);
            combinaisonD = historique.lireReponse(memoire,memoire.size());
            System.out.println(combinaisonD);
            modele = gestion.comparer(combinaisonA, combinaisonD);
            coupRestant--;
        }
        gestion.couleurPolice(3);
        if (combinaisonA.equals(combinaisonD))
            System.out.println("\nPerdu! J'ai trouvé ta combinaison en " +(coupMax-coupRestant)+ " coup(s) !!");
        else
            System.out.println("\nGagné! Je n'ai pas réussi à trouver ta combinaison : " +combinaisonA+ "... ");
    }

    /**
     * Lance le mode Duel (Le joueur humain et l'Ia joue à tour de rôle pour
     * trouver la combinaison de l'autre joueur)
     * @param coupMax maximum de coup(s) alloués pour trouvé la bonne réponse
     * @param caseCombinaison nombre de chiffre qui composent la combinaison à trouver
     * @param developpeur mode développeur activé ou non ( on : affiche la solution à l'écran)
     */
    public void duel(int coupMax, int caseCombinaison, boolean developpeur) {
        String combinaisonAHumain = IA.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonDHumain = "";
        String combinaisonAIA = humain.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonDIA = "";
        String modeleHumain = "";
        String modeleIA = "";
        int coupRestant = coupMax;
        Map<Integer,String> memoire = historique.initialiser(caseCombinaison);
        //boucle de déroulement du jeu (1 tour humain puis 1 tour IA)
        while ((!combinaisonAHumain.equals(combinaisonDHumain) && coupRestant != 0) && (!combinaisonAIA.equals(combinaisonDIA) && coupRestant != 0)) {
            gestion.couleurPolice(3);
            gestion.decor("double",false,false);
            System.out.println("***Tour" +((coupMax-coupRestant)+1)+ "***");
            //---Tour humain---
            gestion.couleurPolice(1);
            humain.afficherModele(modeleHumain, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonAHumain);
            combinaisonDHumain = humain.genererReponse(coupRestant, coupMax, caseCombinaison);
            modeleHumain = gestion.comparer(combinaisonAHumain, combinaisonDHumain);
            gestion.couleurPolice(3);
            gestion.decor("simple",false,false);
            //---Tour IA---
            gestion.couleurPolice(2);
            IA.afficherModele(modeleIA, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonAIA);
            memoire = IA.genererReponse(coupRestant, coupMax, modeleIA, memoire);
            combinaisonDIA = historique.lireReponse(memoire,memoire.size());
            System.out.println(combinaisonDIA);
            modeleIA = gestion.comparer(combinaisonAIA, combinaisonDIA);
            //compteurs
            coupRestant--;
        }
        gestion.couleurPolice(3);
        gestion.decor("double",false,true);
        // fin du jeu affichage des solutions
        if (combinaisonAHumain.equals(combinaisonDHumain) && !combinaisonAIA.equals(combinaisonDIA) && coupRestant!=0){
            System.out.println("Gagné! Tu as trouvé ma combinaison en " +(coupMax-coupRestant)+ " coup(s)");
            System.out.println("et je n'ai pas trouvé la tienne...Ahhh c'était " +combinaisonAIA+ " !");
        }else if (!combinaisonAHumain.equals(combinaisonDHumain) && combinaisonAIA.equals(combinaisonDIA) && coupRestant!=0){
            System.out.println("Perdu! J'ai trouvé ta combinaison en " +(coupMax-coupRestant)+ " coup(s)");
            System.out.println("et tu n'as pas trouvé la mienne...C'était : " +combinaisonAHumain+ " !");
        }else if (combinaisonAHumain.equals(combinaisonDHumain) && combinaisonAIA.equals(combinaisonDIA) && coupRestant!=0){
            System.out.println("Ex-aequo !! Nous avons trouvé nos combinaisons respectives en " +(coupMax-coupRestant)+ " coup(s)");
        }else {
            System.out.println("Pas de perdant !! et pas de gagnant non-plus...");
            System.out.println("Ma combinaison était : " +combinaisonAHumain+ "!");
            System.out.println("Et la tienne était : " +combinaisonAIA+ " ...");
        }
    }
}
