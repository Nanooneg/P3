package joueur;

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
        System.out.println("Choisi une Combinaison de " +nombreChiffre+" chiffres que je devrai trouver en moins de " +coupMax+ " coups.");
        String combinaison = sc.nextLine();
        System.out.println("");
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
            System.out.println("Tu dois trouver ma Combinaison en moins de " +coupMax+ " coup(s)");
        else
            System.out.println("Il te reste " + coupRestant + " coup(s)");

        System.out.println("Saisi une Combinaison de " +nombreChiffre+ " chiffres :");
        String reponse = sc.nextLine();
        System.out.println("");
        return reponse;
    }

}
