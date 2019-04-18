package com.nanoo.p3.gestion;

import java.util.ResourceBundle;

public class Chargement {

    private ResourceBundle chargement = ResourceBundle.getBundle("config.config");

    private int caseCombinaison = Integer.parseInt(chargement.getString("caseCombinaison"));
    private int coupMax = Integer.parseInt(chargement.getString("coupMax"));
    private boolean developpeur = Boolean.parseBoolean(chargement.getString("developpeur"));

    public int getCaseCombinaison() {
        return caseCombinaison;
    }

    public int getCoupMax() {
        return coupMax;
    }

    public boolean isDeveloppeur() {
        return developpeur;
    }

    public void setDeveloppeur(boolean developpeur) {
        this.developpeur = developpeur;
    }
}
