package util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.*;

/**
 * Classe Plateau pour les donnees de l'environnement.
 */
public class Plateau {
    public util.Voie[] voie;      //Voies du jeu.
    int hauteur ;       //dimension du plateau.
    int largeur ;
    int level;
    private final int nScore = 10;

    /**
     * Constructeur de la classe Plateau
     * @param largeur
     *               largeur des voies du plateau
     * @param hauteur
     *              Nombres de voies + 2 pour les trottoirs
     * @param level
     *              Niveau de la partie à laquelle le plateau est associée
     */
    public Plateau(int largeur, int hauteur, int level)
    {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.level = level;
        this.voie = new util.Voie[this.hauteur - 2];     //Initialisation des voies.

        for(int i = 0; i < this.hauteur; i++)
        {
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

    /**
     * enregistrerPartie enregistre un nouveau score parmi les meilleurs scores precedement obtenus.
     * @param
     * file (chemin du fichier).
     * score (score de la partie).
     */
    public void enregistrerPartie(String file, int score)
    {
        int[] tabScore = recupererPartie(file);

        if(score > tabScore[0])
        {
            tabScore[0] = score;
            Arrays.sort(tabScore);
        }

        int j = enregistrerScore(file, tabScore);
    }

    /**
     * enregistrerScore enregistre un tableau des scores.
     * @param
     * file (chemin du fichier).
     * tab (tableau des scores).
     * @return
     * 0 si tout s'est passe correctement, 1 si une erreur a ete detecte.
     */
    public int enregistrerScore(String file, int[] tab) {

        try {
            FileWriter fw = new FileWriter(file, false);
            BufferedWriter fwOut = new BufferedWriter(fw);

            for (int i = 0; i < tab.length; ++i) {
                try {
                    fwOut.write(String.valueOf(tab[i]));
                    fwOut.write(",");
                    fwOut.write(Integer.toString(10 - i));
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

    /**
     * recupererPartie recupere dans un fichier le tableau des scores.
     * @param
     * file (chemin du fichier).
     * @return
     * Le tableau des dix meilleurs scores.
     */
    public int[] recupererPartie(String file)
    {
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

    /**
     * getHauteur indique la hauteur de la partie.
     * Le nombre de voie est egale a la hauteur auquelle on soustrait deux.
     * @return
     * La hauteur de la partie.
     */
    public int getHauteur() {
        return hauteur;
    }

    /**
     * getLargeur indique la largeur du plateau.
     * @return
     * La largeur du plateau.
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * getLevel indique le niveau de la partie.
     * @return
     * Le niveau de la partie.
     */
    public int getLevel() {
        return level;
    }

}
