public class Main {

    public static void main(String[] args) {

        //récupération des paramétres chargés
        Chargement chargement = new Chargement();
        int caseCombinaison = chargement.getCaseCombinaison();
        int coupMax = chargement.getCoupMax();
        boolean developpeur = chargement.isDeveloppeur();

        //déclaration variables et objets
        Combinaison cbn = new Combinaison();
        int coupRestant = chargement.getCoupMax();
        int essai = 0;
        String combinaisonA = cbn.genererCombinaison(caseCombinaison);
        String combinaisonD = "";
        String modele = "";

        //présentation du jeu
        Presentation.presenter();

        //boucle de déroulement du jeu : affichage du mogèle -> réponse du defenseur -> comparaison
        while ( !combinaisonA.equals(combinaisonD) && coupRestant != 0) {
            cbn.afficherModele(modele,coupRestant,coupMax,caseCombinaison,developpeur,combinaisonA);
            combinaisonD = cbn.demanderDefenseur(coupRestant,coupMax,caseCombinaison);
            modele = cbn.comparer(combinaisonA,combinaisonD,caseCombinaison);
            coupRestant--;
            essai++;
        }
        // fin du jeu affichage de la solution
        cbn.afficherResultat(combinaisonA,coupRestant,essai);

    }
}