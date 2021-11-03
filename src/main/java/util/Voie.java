package util;

import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe Voie pour gerer les voies de circulations.
 */
public class Voie implements Voiture, Camion{
    private final String name;
    private final double vitesse;
    private final int type;

    public boolean voiture1 = true; //Booléen qui indique si la voiture1 empêche la création de la voiture2
    public boolean voiture2 = false;

    public boolean is_timeline=false; //booléen indiquant si une timeline est définie sur cette voie
    public boolean is_timeline2 = false; //booléen indiquant si une 2eme timeline est définie sur cette voie

    public Timeline timeline; //timeline de la voie
    public Timeline timeline2;

    public ArrayList<DoubleProperty[]> position = new ArrayList<>(); //liste qui contient les positions des voitures sur cette voie
    public ArrayList<DoubleProperty[]> position2 = new ArrayList<>();

    public int sens; //sens de la voie (1 : de la gauche vers la droite et -1: droite vers la gauche)

    /**
     * Constructeur de la classe Voie
     * @param name Nom de la voie
     * @param level Niveau de la parie à laquelle est associee la voie
     * @param taille Largeur de la voie
     * @param sens Sens de la voie (1 de gauche à droite, -1 sinon)
     */
    public Voie(String name, int level, int taille, int sens)
    {
        this.name = name;
        this.vitesse = (1.1 + 0.05 * (Math.random() - 0.5)) - 1 / Math.pow(level, 0.1);
        int rd = new Random().nextInt(2);
        this.type = rd * tailleVoiture + (1 - rd) * tailleCamion;
        this.sens = sens;
    }

    /**
     * getName indique l'identifiant de la voie.
     * @return
     * Le nom de la voie.
     */
    public String getName()
    {
        return this.name;
    }


    /**
     * getVitesse indique la vitesse des vehicule sur la voie.
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
    public Boolean passage(int level)
    {
        double d = Math.random();

        if(level > 100)
        {
            return d < 0.01;
        }
        else
        {
            return d < 0.008 + 0.002 * ((float)level / 100);
        }
    }
}
