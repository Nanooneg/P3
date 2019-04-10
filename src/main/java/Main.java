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
                case 1:
                    String combinaisonA = IA.genererCombinaison(coupMax, caseCombinaison);
                    String combinaisonD ="";
                    String modele ="";
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


                case 2:
                    combinaisonA = humain.genererCombinaison(coupMax, caseCombinaison);
                    combinaisonD ="";
                    String combinaisonP ="";  // combinaison précédante
                    modele ="";
                    //boucle de déroulement du jeu : affichage du mogèle -> réponse du defenseur -> comparaison
                    while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
                        combinaison.afficherModele(modele, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonA);
                        combinaisonD = IA.genererReponse(coupRestant, coupMax, modele, combinaisonP, caseCombinaison);
                        System.out.println(combinaisonD);
                        combinaisonP = combinaisonD;
                        modele = combinaison.comparer(combinaisonA, combinaisonD);
                        coupRestant--;
                        essai++;
                    }
                    // fin du jeu affichage de la solution
                    combinaison.afficherResultat(combinaisonA, coupRestant, essai);
                    break;
            }
            //proposition de rejouer
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