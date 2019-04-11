import joueur.JoueurHumain;
import joueur.JoueurIA;

public class Mode {

    //déclaration objets
    private JoueurHumain humain = new JoueurHumain();
    private JoueurIA IA = new JoueurIA();
    private Gestion gestion = new Gestion();

    public void challenger(int modeDeJeu, int coupRestant, int coupMax, int essai, int caseCombinaison, boolean developpeur){
        String combinaisonA = IA.genererCombinaison(coupMax, caseCombinaison);   //Combinaison Attaquant
        String combinaisonD ="";                                                 //Combinaison Défenseur
        String modele ="";                                                       //Modèle ("+-+=")
        System.out.print("\033[34m");    //police en Bleu
        //boucle de déroulement du jeu : affichage du mogèle -> réponse du defenseur -> comparaison
        while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            humain.afficherModele(modele, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonA);
            combinaisonD = humain.genererReponse(coupRestant, coupMax, caseCombinaison);
            modele = gestion.comparer(combinaisonA, combinaisonD);
            //Compteurs
            coupRestant--;
            essai++;
        }
        // fin du jeu affichage de la solution
        if (combinaisonA.equals(combinaisonD))
            System.out.println("Bravo tu as trouvé ma combinaison en " + essai + " coup(s) !!");
        else
            System.out.println("Dommage tu n'as pas trouvé ma combinaison ! C'était : " + combinaisonA + "...");
    }

    public void defenseur(int modeDeJeu, int coupRestant, int coupMax, int essai, int caseCombinaison, boolean developpeur){
        String combinaisonA = humain.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonD ="";
        String combinaisonP ="";                                                 // combinaison précédante
        String modele ="";
        System.out.print("\033[33m");    //police en Jaune
        while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            IA.afficherModele(modele, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonA);
            combinaisonD = IA.genererReponse(coupRestant, coupMax, modele, combinaisonP, caseCombinaison);
            System.out.println(combinaisonD);
            combinaisonP = combinaisonD;
            modele = gestion.comparer(combinaisonA, combinaisonD);
            coupRestant--;
            essai++;
        }
        if (combinaisonA.equals(combinaisonD))
            System.out.println("J'ai trouvé ta combinaison en " +essai+ " coup(s) !!");
        else
            System.out.println("Dommage, je n'ai pas réussi à trouver ta combinaison : " +combinaisonA+ "... ");
    }

    public void duel(int modeDeJeu, int coupRestant, int coupMax, int essai, int caseCombinaison, boolean developpeur) {
        String combinaisonAHumain = IA.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonDHumain = "";
        String combinaisonAIA = humain.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonDIA = "";
        String combinaisonPIA = "";
        String modeleHumain = "";
        String modeleIA = "";
        //boucle de déroulement du jeu (1 tour humain puis 1 tour IA) : affichage du mogèle -> réponse du defenseur -> comparaison
        while ((!combinaisonAHumain.equals(combinaisonDHumain) && coupRestant != 0) && (!combinaisonAIA.equals(combinaisonDIA) && coupRestant != 0)) {
            System.out.print("\033[30m");   //Police en Blanc
            System.out.println("====================================");
            System.out.println("***Tour" +(essai+1)+ "***");
            //---Tour humain---
            System.out.print("\033[34m");   //police en Bleu
            humain.afficherModele(modeleHumain, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonAHumain);
            combinaisonDHumain = humain.genererReponse(coupRestant, coupMax, caseCombinaison);
            modeleHumain = gestion.comparer(combinaisonAHumain, combinaisonDHumain);
            System.out.print("\033[30m");   //Police en Blanc
            System.out.println("------------------------------------");
            //---Tour IA---
            System.out.print("\033[33m");   //police en jaune
            IA.afficherModele(modeleIA, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonAIA);
            combinaisonDIA = IA.genererReponse(coupRestant, coupMax, modeleIA, combinaisonPIA, caseCombinaison);
            System.out.println(combinaisonDIA);
            combinaisonPIA = combinaisonDIA;
            modeleIA = gestion.comparer(combinaisonAIA, combinaisonDIA);
            //compteurs
            coupRestant--;
            essai++;
        }
        System.out.print("\033[30m");   //Police en Blanc
        System.out.println("====================================\n");
        // fin du jeu affichage des solutions
        if (combinaisonAHumain.equals(combinaisonDHumain) && !combinaisonAIA.equals(combinaisonDIA) && coupRestant!=0){
            System.out.println("Bravo! Tu as gagné. Tu as trouvé ma combinaison en " +essai+ " coup(s)");
            System.out.println("et je n'ai pas trouvé la tienne...Ahhh c'était " +combinaisonAIA+ " !");
        }else if (!combinaisonAHumain.equals(combinaisonDHumain) && combinaisonAIA.equals(combinaisonDIA) && coupRestant!=0){
            System.out.println("Tu as perdu! j'ai trouvé ta combinaison en " +essai+ " coup(s)");
            System.out.println("et tu n'as pas trouvé la mienne...C'était : " +combinaisonAHumain+ " !");
        }else if (combinaisonAHumain.equals(combinaisonDHumain) && combinaisonAIA.equals(combinaisonDIA) && coupRestant!=0){
            System.out.println("Ex-aequo !! Nous avons trouvé nos combinaisons respectives en " +essai+ " coup(s)");
        }else {
            System.out.println("Pas de perdant !! et pas de gagnant non-plus...");
            System.out.println("Ma combinaison était : " +combinaisonAHumain+ "!");
            System.out.println("Et la tienne était : " +combinaisonAIA+ " ...");
        }
    }
}
