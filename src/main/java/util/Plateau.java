package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.*;
import java.nio.file.*;
import java.util.Arrays;


public class Plateau {
    private final util.Case[][] plateau;     //Plateau du jeu, etat des cases.
    public util.Voie[] voie;      //Voies du jeu.
    int hauteur ;       //dimension du plateau.
    int largeur ;
    int level;
    private final int nScore = 10;

    //private Jeu;

    public Plateau(int largeur, int hauteur, int level)
    {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.level = level;
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
                //Création des voies en utilisant une loi d'équiprobabilité sur le sens de chaque voie
                double d = Math.random();
                int sens;
                if(d<=0.5) sens = 1;
                else sens = -1;
                this.voie[i - 1] = new util.Voie("Voie " + i, level, largeur, sens);
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

    public void enregistrerPartie(String file, int score)
    {
        /*
         * enregistrerPartie enregistre un nouveau score parmi les meilleurs scores precedement obtenus.
         * Parametre:
         * file: chemin du fichier.
         * score: score obtenu.
        * Return (void).*/

        int[] tabScore = recupererPartie(file);

        if(score > tabScore[0])
        {
            tabScore[0] = score;
            Arrays.sort(tabScore);
        }

        int j = enregistrerScore(file, tabScore);
    }

    public int enregistrerScore(String file, int[] tab) {
        /*
         * AfficherVoie enregistre un tableau des scores.
         * Parametre:
         * file: chemin du fichier.
         * tab: tableau des scores.
         * Return 1 si la transcription n'est pas complete, 0 sinon.*/

        try {
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter fwOut = new BufferedWriter(fw);

            for (int k : tab) {
                try {
                    fwOut.write(String.valueOf(k));
                    fwOut.write(",");
                    fwOut.write("25/10");
                    fwOut.newLine();
                }
                catch (Exception e1)
                {
                    System.out.println(e1);
                    System.out.println("Les resultats n'ont pas pu tous etre affiche.");
                }
            }

            fwOut.close();



        }
        catch (IOException e) {
            System.out.println(e);
            return 1;
        }

        return 0;
    }

    public int[] recupererPartie(String file)
    {
        /*
         * recupererPartie recupere dans un fichier le tableau des scores.
         * Parametre:
         * file: chemin du fichier.
         * Return (void).*/
        int [] tabScore = new int[nScore];
        int i = 0;

        try {
            File fr = new File(file);

            BufferedReader frIn = new BufferedReader(new FileReader(fr));

            String readLine = "";

            while ((readLine = frIn.readLine()) != null) {
                String[] readLineTab = readLine.split(",", 2);
                int readLineScore = Integer.parseInt(readLineTab[0]);
                //System.out.println(readLineScore);
                tabScore[i] = readLineScore;
                ++i;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tabScore;
    }

    public void unTour()
    {
        /*
         * unTour fait avancer la parie d'un coup d'horloge en appellant les voies une a une.
         * Return (void).*/
        for (util.Voie value : this.voie) value.unTourVoie();
    }

    public static void main(String[] args) {
        Plateau p1 = new Plateau(10, 12, 1);
        int tab1[] = {1, 3, 2, 7, 9};


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

        p1.enregistrerPartie(file, 14);

        p1.recupererPartie(file);

    }





    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getLevel() {
        return level;
    }

}
