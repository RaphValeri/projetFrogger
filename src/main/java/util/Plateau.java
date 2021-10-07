package util;

import java.awt.*;
import java.util.Arrays;


public class Plateau {
    private final Case[][] plateau;     //Plateau du jeu, etat des cases.
    private Voie[] voie;      //Voies du jeu.
    final int hauteur = 12; //dimension du plateau.
    final int largeur;
    int largeurMin = 10;
    int largeurMax = 30;

    //private Jeu;

    public Plateau()
    {
        this.largeur = (int) (Math.random() * (this.largeurMax - this.largeurMin) + this.largeurMin);        //largeur du plateau aléatoire entre 10 et 30, loi uniforme.

        System.out.println(largeur);

        this.plateau = new Case[this.hauteur][largeur];     //Initialisation plateau de Case.
        this.voie = new Voie[this.hauteur - 2];     //Initialisation des voies.

        for(int i = 0; i < this.hauteur; i++)       //Cretion des cases du plateau avec leur etat.
        {
            for(int j = 0; j < largeur; j++)
            {
                if(i == 0 || i == hauteur - 1)
                    this.plateau[i][j] = new Case(1);
                else
                    this.plateau[i][j] = new Case(0);
            }

            if(i > 0 && i < this.hauteur - 1)
            {
                this.voie[i - 1] = new Voie("Voie " + i, 1, largeur);
            }
        }


    }

    public void AfficherPlateau()
    {
        /*
        * AfficherPlateau affiche les etats des cases du plateau.
        * Return (void).*/
        for (Case[] tab: this.plateau) {
            for (Case s: tab) {
                System.out.print(s.getState() + "\t");
            }
            System.out.println("\n");
        }
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public Voie[] getVoie() {
        return voie;
    }

    public void AfficherVoie()
    {
        /*
         * AfficherVoie affiche le nom de la voie et les positions des vehicules.
         * Return (void).*/
        for(int i = 0; i < this.voie.length; i++)
        {
            System.out.println(this.voie[i].getName() + " :" + Arrays.toString(this.voie[i].getVehicule()));
        }
    }

    public void unTour()
    {
        /*
         * unTour fait avancer la parie d'un coup d'horloge en appellant les voies une a une.
         * Return (void).*/
        for (Voie value : this.voie) value.unTourVoie();
    }

    public static void main(String[] args) {
        Plateau p1 = new Plateau();

        p1.AfficherPlateau();

        p1.AfficherVoie();

        for(int i = 0; i < 10; i++)
        {
            p1.unTour();
            System.out.println("");
            System.out.println("Tour " + i);
            p1.AfficherVoie();
        }


    }

}
