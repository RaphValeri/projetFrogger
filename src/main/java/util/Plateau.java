package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.*;
import java.nio.file.*;


public class Plateau {
    private final util.Case[][] plateau;     //Plateau du jeu, etat des cases.
    public util.Voie[] voie;      //Voies du jeu.
    int hauteur = 12;       //dimension du plateau.
    int largeurMin = 10;
    int largeurMax = 30;
    int largeur;

    //private Jeu;

    public Plateau()
    {
        this.largeur = (int) (Math.random() * (this.largeurMax - this.largeurMin) + this.largeurMin);        //largeur du plateau aléatoire entre 10 et 30, loi uniforme.

        System.out.println(largeur);

        this.plateau = new util.Case[this.hauteur][largeur];     //Initialisation plateau de Case.
        this.voie = new util.Voie[this.hauteur - 2];     //Initialisation des voies.

        for(int i = 0; i < this.hauteur; i++)       //Cretion des cases du plateau avec leur etat.
        {
            for(int j = 0; j < largeur; j++)
            {
                if(i == 0 || i == hauteur - 1)
                    this.plateau[i][j] = new util.Case(1);
                else
                    this.plateau[i][j] = new util.Case(0);
            }

            if(i > 0 && i < this.hauteur - 1)
            {
                this.voie[i - 1] = new util.Voie("Voie " + i, 1, largeur);
            }
        }


    }

    public void AfficherPlateau()
    {
        /*
         * AfficherPlateau affiche les etats des cases du plateau.
         * Return (void).*/
        for (util.Case[] tab: this.plateau) {
            for (util.Case s: tab) {
                System.out.print(s.getState() + "\t");
            }
            System.out.println("\n");
        }
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

    public int enregistrerPartie(String file) {
        /*
         * AfficherVoie affiche le nom de la voie et les positions des vehicules.
         * Return (void).*/

        try {
            FileWriter fw = new FileWriter(file, false);

            for (util.Voie value : this.voie) {
                for(int i = 0; i < largeur; ++i)
                {

                    fw.write(Integer.toString(value.getVehicule()[i]));
                }


            }

            fw.close();

        } catch (IOException e) {
            System.out.println("e.toString()");
            return 1;
        }

        return 0;
    }

    public void recupererPartie(String file)
    {
        Path fr = Paths.get(file);
        InputStream input = null;
        try {
            input = Files.newInputStream(fr);

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            int coefVehicule;

            for (Voie value : voie) {
                for (int j = 0; j < this.largeur; j++) {
                    coefVehicule = reader.read();
                    value.setVehicleCoef(coefVehicule - 48, j);
                }
            }

            input.close();



        } catch (IOException e) {
            System.out.println("Message " + e);
        }
    }

    public void unTour()
    {
        /*
         * unTour fait avancer la parie d'un coup d'horloge en appellant les voies une a une.
         * Return (void).*/
        for (util.Voie value : this.voie) value.unTourVoie();
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

        String file = "test.txt";

        int j = p1.enregistrerPartie(file);

        System.out.println(j);

        for(int i = 10; i < 15; i++)
        {
            p1.unTour();
            System.out.println("");
            System.out.println("Tour " + i);
            p1.AfficherVoie();
        }

        p1.recupererPartie(file);
        p1.AfficherVoie();

        for(int i = 15; i < 20; i++)
        {
            p1.unTour();
            System.out.println("");
            System.out.println("Tour " + i);
            p1.AfficherVoie();
        }
    }





    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }
}
