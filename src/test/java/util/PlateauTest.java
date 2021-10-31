package util;

import frog.Frog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PlateauTest {

    @Test
    @DisplayName("Test de enregistrerPartie")
    void enregistrerPartie() {
        int[] tabScore1 = {1, 1, 2, 3, 5, 6, 7, 8, 9, 10};
        int[] tabScore2 = {3, 3, 3, 4, 5, 6, 7, 8, 9, 10};

        int score1 = 4;
        int score2 = 1;

        if(score1 > tabScore1[0])
        {
            tabScore1[0] = score1;
            Arrays.sort(tabScore1);
        }

        if(score2 > tabScore2[0])
        {
            tabScore2[0] = score2;
            Arrays.sort(tabScore2);
        }

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, tabScore1);
        assertArrayEquals(new int[]{3, 3, 3, 4, 5, 6, 7, 8, 9, 10}, tabScore2);

    }

    @Test
    @DisplayName("Test de enregistrerScore")
    void enregistrerScore() {
        var f1 = new Plateau(10, 10, 1);

        int[] tabScore = new int[3];

        f1.enregistrerPartie("testPlateau.txt", 1);
        f1.enregistrerPartie("testPlateau.txt", 1);
        f1.enregistrerPartie("testPlateau.txt", 2);
        f1.enregistrerPartie("testPlateau.txt", 4);
        f1.enregistrerPartie("testPlateau.txt", 5);
        f1.enregistrerPartie("testPlateau.txt", 6);
        f1.enregistrerPartie("testPlateau.txt", 7);
        f1.enregistrerPartie("testPlateau.txt", 8);
        f1.enregistrerPartie("testPlateau.txt", 9);
        f1.enregistrerPartie("testPlateau.txt", 10);


        try {
            File fr = new File("testPlateau.txt");

            BufferedReader frIn = new BufferedReader(new FileReader(fr));

            String readLine = "";

            for(int i = 0; i < 3; ++i) {
                readLine = frIn.readLine();
                String[] readLineTab = readLine.split(",", 2);
                int readLineScore = Integer.parseInt(readLineTab[1]);

                tabScore[i] = readLineScore;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        assertArrayEquals(new int[]{10, 9, 8}, tabScore);
    }

    @Test
    @DisplayName("Test de recupererPartie")
    void recupererPartie() {
        var f1 = new Plateau(10, 10, 1);
        int[] tabScore = f1.recupererPartie("testPlateau.txt");

        assertEquals(10, tabScore.length);
        assertEquals(10, tabScore[tabScore.length - 1]);
    }

    @Test
    @DisplayName("Test du getter hauteur")
    void getHauteur() {
        var f1 = new Plateau(10, 10, 1);
        var f2 = new Plateau(15, 15 - 5, 2);
        assertEquals(10, f1.getHauteur());
        assertEquals(10, f2.getHauteur());
    }

    @Test
    @DisplayName("Test du getter largeur")
    void getLargeur() {
        var f1 = new Plateau(10, 10, 1);
        var f2 = new Plateau(15, 15 - 5, 2);
        assertEquals(10, f1.getLargeur());
        assertEquals(15, f2.getLargeur());
    }

    @Test
    @DisplayName("Test du getter level")
    void getLevel() {
        var f1 = new Plateau(10, 10, 1);
        var f2 = new Plateau(15, 15 - 5, 2);
        assertEquals(1, f1.getLevel());
        assertEquals(2, f2.getLevel());
    }
}