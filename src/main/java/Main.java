import joueur.JoueurHumain;
import joueur.JoueurIA;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //récupération des paramétres chargés
        Chargement chargement = new Chargement();
        int caseCombinaison = chargement.getCaseCombinaison();
        int coupMax = chargement.getCoupMax();
        boolean developpeur = chargement.isDeveloppeur();

        //booleen pour rejouer
        boolean jouer = true;
        int modeDeJeu = 0; // 1 - Challenger   2 - Défenseur   3 - Duel
        Scanner sc =new Scanner(System.in);

        //présentation du jeu
        Presentation presentation = new Presentation();
        presentation.presenter();
        modeDeJeu = presentation.choixMode();

        do {

            //déclaration objets
            Combinaison combinaison = new Combinaison();
            JoueurHumain humain = new JoueurHumain();
            JoueurIA IA = new JoueurIA();

            //déclaration variables
            int coupRestant = coupMax + 1;
            int essai = 0;


            switch (modeDeJeu) {
                //***CHALLENGER***
                case 1:
                    String combinaisonA = IA.genererCombinaison(coupMax, caseCombinaison);   //Combinaison Attaquant
                    String combinaisonD ="";    //Combinaison Défenseur
                    String modele ="";    //Modèle ("+-+=")
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
                    break;
                //***DEFENSEUR***
                case 2:
                    combinaisonA = humain.genererCombinaison(coupMax, caseCombinaison);
                    combinaisonD ="";
                    String combinaisonP ="";  // combinaison précédante
                    modele ="";
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
                    break;
                //***DUEL***
                case 3:
                    String combinaisonAHumain = IA.genererCombinaison(coupMax, caseCombinaison);
                    String combinaisonDHumain ="";
                    String combinaisonAIA = humain.genererCombinaison(coupMax, caseCombinaison);
                    String combinaisonDIA ="";
                    String combinaisonPIA ="";
                    String modeleHumain ="";    //Modèle ("+-+=")
                    String modeleIA ="";
                    //boucle de déroulement du jeu (1 tour humain puis 1 tour IA) : affichage du mogèle -> réponse du defenseur -> comparaison
                    while ((!combinaisonAHumain.equals(combinaisonDHumain) && coupRestant != 0)||!combinaisonAIA.equals(combinaisonDIA) && coupRestant != 0){
                        coupRestant--;
                        //System.out.println("==========");
                        //---Tour humain---
                        System.out.print("\033[34m");   //police en Blanc
                        combinaison.afficherModele(modeleHumain, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonAHumain);
                        combinaisonDHumain = humain.genererReponse(coupRestant, coupMax, caseCombinaison);
                        modeleHumain = combinaison.comparer(combinaisonAHumain, combinaisonDHumain);
                        //System.out.println("----------");
                        //---Tour IA---
                        System.out.print("\033[33m");   //police en
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
            //proposition de rejouer
            System.out.print("\033[30m");   //police en Blanc
            System.out.println("");
            System.out.println("Voulez-vous rejouer? oui/non");
            String demandeRejouer = sc.nextLine();
            if (demandeRejouer.equals("non"))
                jouer = false;
            System.out.println("\n\n");
        }while (jouer);

        System.out.println("Merci et à bientôt");
    }
}