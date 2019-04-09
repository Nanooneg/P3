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
        Scanner sc =new Scanner(System.in);

        //présentation du jeu
        Presentation.presenter();

        do {

            //déclaration variables et objets
            Combinaison cbn = new Combinaison();
            int coupRestant = chargement.getCoupMax();
            int essai = 0;
            String combinaisonA = cbn.genererCombinaison(caseCombinaison);
            String combinaisonD = "";
            String modele = "";

            //boucle de déroulement du jeu : affichage du mogèle -> réponse du defenseur -> comparaison
            while (!combinaisonA.equals(combinaisonD) && coupRestant != 0) {
                cbn.afficherModele(modele, coupRestant, coupMax, caseCombinaison, developpeur, combinaisonA);
                combinaisonD = cbn.demanderDefenseur(coupRestant, coupMax, caseCombinaison);
                modele = cbn.comparer(combinaisonA, combinaisonD, caseCombinaison);
                coupRestant--;
                essai++;
            }
            // fin du jeu affichage de la solution
            cbn.afficherResultat(combinaisonA, coupRestant, essai);

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