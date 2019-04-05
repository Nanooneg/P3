public class jeuPlusOuMoins {

    public static void main(String[] args) {

        int R = 20;
        int min = 1000;
        int max = 9999;
        combinaison cbn = new combinaison();
        String cbnA;
        String cbnD = "";
        String cbnM = "XXXX";
        parametreDuJeu.presenter();
        cbnA = cbn.randomModele(min, max);
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