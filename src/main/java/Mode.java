import joueur.JoueurHumain;
import joueur.JoueurIA;

public class Mode {

    //déclaration objets
    private Combinaison combinaison = new Combinaison();
    private JoueurHumain humain = new JoueurHumain();
    private JoueurIA IA = new JoueurIA();

    public void challenger(int coupRestant, int coupMax, int essai, int caseCombinaison, boolean developpeur){
        String combinaisonA = IA.genererCombinaison(coupMax, caseCombinaison);   //Combinaison Attaquant
        String combinaisonD ="";    //Combinaison Défenseur
        String modele ="";    //Modèle ("+-+=")
        System.out.print("\033[34m");   //police en Bleu
        //boucle de déroulement du jeu : affichage du mogèle -> réponse du defenseur -> comparaison
        while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            coupRestant--;
            combinaison.afficherModele(modele, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonA);
            combinaisonD = humain.genererReponse(coupRestant, coupMax, caseCombinaison);
            modele = combinaison.comparer(combinaisonA, combinaisonD);
            essai++;
        }
        // fin du jeu affichage de la solution
        combinaison.afficherResultat(combinaisonA, coupRestant, essai);
    }

    public void defenseur(int coupRestant, int coupMax, int essai, int caseCombinaison, boolean developpeur){
        String combinaisonA = humain.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonD ="";
        String combinaisonP ="";  // combinaison précédante
        String modele ="";
        System.out.print("\033[33m");   //police en Jaune
        while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            coupRestant--;
            combinaison.afficherModele(modele, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonA);
            combinaisonD = IA.genererReponse(coupRestant, coupMax, modele, combinaisonP, caseCombinaison);
            System.out.println(combinaisonD);
            combinaisonP = combinaisonD;
            modele = combinaison.comparer(combinaisonA, combinaisonD);
            essai++;
        }
        combinaison.afficherResultat(combinaisonA, coupRestant, essai);
    }

    public void duel(int coupRestant, int coupMax, int essai, int caseCombinaison, boolean developpeur){
        String combinaisonAHumain = IA.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonDHumain ="";
        String combinaisonAIA = humain.genererCombinaison(coupMax, caseCombinaison);
        String combinaisonDIA ="";
        String combinaisonPIA ="";
        String modeleHumain ="";    //Modèle ("+-+=")
        String modeleIA ="";
        //boucle de déroulement du jeu (1 tour humain puis 1 tour IA) : affichage du mogèle -> réponse du defenseur -> comparaison
        while ((!combinaisonAHumain.equals(combinaisonDHumain) && coupRestant != 0)&&(!combinaisonAIA.equals(combinaisonDIA) && coupRestant != 0)){
            coupRestant--;
            //System.out.println("==========");
            //---Tour humain---
            System.out.print("\033[34m");   //police en Bleu
            combinaison.afficherModele(modeleHumain, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonAHumain);
            combinaisonDHumain = humain.genererReponse(coupRestant, coupMax, caseCombinaison);
            modeleHumain = combinaison.comparer(combinaisonAHumain, combinaisonDHumain);
            //System.out.println("----------");
            //---Tour IA---
            System.out.print("\033[33m");   //police en jaune
            combinaison.afficherModele(modeleIA, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonAIA);
            combinaisonDIA = IA.genererReponse(coupRestant, coupMax, modeleIA, combinaisonPIA, caseCombinaison);
            System.out.println(combinaisonDIA+ "\n");
            combinaisonPIA = combinaisonDIA;
            modeleIA = combinaison.comparer(combinaisonAIA, combinaisonDIA);

            essai++;
        }
        //System.out.println("==========");
        // fin du jeu affichage des solutions
        combinaison.afficherResultat(combinaisonAHumain, coupRestant, essai);
        combinaison.afficherResultat(combinaisonAIA, coupRestant, essai);
    }
}
