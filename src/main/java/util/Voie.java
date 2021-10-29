package util;

import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;

import java.util.ArrayList;
import java.util.Random;

public class Voie implements Voiture, Camion{
    private final String name;
    private final double vitesse;
    private final int type;

    public boolean voiture1 = true; //Booléen qui indique si la voiture1 empêche la création de la voiture2
    public boolean voiture2 = false;

    public boolean is_timeline=false; //booléen indiquant si une timeline est défini sur cette voie
    public boolean is_timeline2 = false;

    public Timeline timeline; //timeline de la voie
    public Timeline timeline2;

    public ArrayList<DoubleProperty[]> position = new ArrayList<>(); //liste qui contient les positions des voitures sur cette voie
    public ArrayList<DoubleProperty[]> position2 = new ArrayList<>();

    public int sens; //sens de la voie (1 : de la gauche vers la droite et -1: droite vers la gauche)

    public Voie(String name, int level, int taille, int sens)
    {
        this.name = name;
        //this.vitesse = 1.1 - 1 / Math.pow(level, 0.1);
        this.vitesse = 0.2*level + 0.3*Math.random();
        int rd = new Random().nextInt(2);
        this.type = rd * tailleVoiture + (1 - rd) * tailleCamion;
        this.sens = sens;
    }

    /**
     * getType indique l'identifiant de la voie.
     * @return
     * Le nom de la voie.
     */
    public String getName()
    {
        return this.name;
    }


    /**
     * getType indique la vitesse des vehicule sur la voie.
     * @return
     * La vitesse des vehicules sur la voie.
     */
    public double getVitesse()
    {
        /*
        * getVitesse permet d'acceder a la variable vehicule.
        * Renvoie la variable vehicule.*/
        return this.vitesse;
    }

    /**
     * getType indique le type de vehicule sur la voie.
     * @return
     * Le type de vehicule sur la voie.
     */
    public int getType()
    {
        return this.type;
    }


    /**
     * passage indique si un nouveau vehicule apparait dans la voie.
     * @return
     * True si un vehicule apparait, sinon False.
     */
    public Boolean passage()
    {
        double d = Math.random();

        return d < 0.01;
    }
}
