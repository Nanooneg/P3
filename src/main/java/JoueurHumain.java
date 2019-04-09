import java.util.Scanner;

public class JoueurHumain extends Joueur {

    Scanner sc = new Scanner(System.in);

    /**
     * Demande à l'attaquant humain de saisir un combinaison que le défenseur IA devra trouver en X coup(s)
     * @param coupMax nombre de coup(s) max pour trouver la bonne réponse
     * @return la combinaison à trouver
     */
    @Override
    public String genererCombinaison(int coupMax, int nombreChiffre) {
        System.out.println("Veuillez saisir une Combinaison de " +nombreChiffre+" chiffres que le joueur défenseur devra trouver en moins de " +coupMax+ " coups.");
        System.out.println("");
        String combinaison = sc.nextLine();
        return combinaison;
    }

    /**
     * Annonce au défenseur combien il lui reste de coup(s) et lui demande de saisir une réponse
     * @param coupRestant nombre de coup(s) restant
     * @param coupMax nombre de coup(s) alloués au début du jeu
     * @param nombreChiffre nombre de chiffre qui compose la Combinaison
     * @return la Combinaison saisie
     * */
       public String genererReponse(int coupRestant, int coupMax, int nombreChiffre) {
        if (coupRestant == coupMax)
            System.out.println("Vous devez trouver la Combinaison du joueur attaquant en moins de " +coupMax+ " coup(s)");
        else
            System.out.println("Il vous reste " + coupRestant + " coup(s)");

        System.out.println("Veuillez saisir une Combinaison de " +nombreChiffre+ " chiffres :");
        String reponse = sc.nextLine();
        return reponse;
    }

}
