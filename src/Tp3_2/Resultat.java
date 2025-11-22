package Tp3_2;

import java.io.Serializable;

public class Resultat implements Serializable {
    private double valeur;

    public Resultat(double valeur) {
        this.valeur = valeur;
    }

    public double getValeur() {
        return valeur;
    }

    @Override
    public String toString() {
        return "Résultat = " + valeur;
    }
}

