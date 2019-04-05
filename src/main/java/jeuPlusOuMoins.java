public class jeuPlusOuMoins {

    public static void main(String[] args) {

        int R = 20;
        int level = 4;   // difficulté = 4 cases pour la combinaison

        combinaison cbn = new combinaison();
        String cbnA;
        String cbnD = "";
        String cbnM = "XXXX";
        parametreDuJeu.presenter();
        cbnA = cbn.randomModele(level);
        int i = 0;

        while ( !cbnA.equals(cbnD) && R != 0) {
            cbn.afficherModele(cbnM,R);
            cbnD = cbn.demanderDefenseur(R);
            i++;
            cbnM = cbn.comparer(cbnA,cbnD);
            R--;
        }

        cbn.afficherResultat(cbnA, R, i);

    }
}