public class jeuPlusOuMoins {

    public static void main(String[] args) {

        int R = 20, i = 0;
        combinaison cbn = new combinaison();
        String cbnA;
        String cbnD = "";
        String cbnM = "XXXX";
        presentationDuJeu.presenter();
        cbnA = cbn.demanderAttaquant(R);

        while (cbnA != cbnD && R != 0) {
            cbn.afficherModele(cbnM,R);
            cbnD = cbn.demanderDefenseur(R);
            i++;
            cbnM = cbn.comparer(cbnA,cbnD);
            R--;
        }

        cbn.afficherResultat(cbnA, R, i);
    }

}