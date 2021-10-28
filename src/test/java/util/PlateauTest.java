package util;

import frog.Frog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class PlateauTest {

    @Test
    @DisplayName("Test de enregistrerPartie")
    void enregistrerPartie() {
        var f1 = new Plateau(10, 10, 1);

        int[] tabScore;

        f1.enregistrerPartie("testPlateau.txt", 1);
        f1.enregistrerPartie("testPlateau.txt", 1);
        f1.enregistrerPartie("testPlateau.txt", 2);
        f1.enregistrerPartie("testPlateau.txt", 3);
        f1.enregistrerPartie("testPlateau.txt", 5);
        f1.enregistrerPartie("testPlateau.txt", 6);
        f1.enregistrerPartie("testPlateau.txt", 7);
        f1.enregistrerPartie("testPlateau.txt", 8);
        f1.enregistrerPartie("testPlateau.txt", 9);
        f1.enregistrerPartie("testPlateau.txt", 10);


        tabScore = f1.recupererPartie("testPlateau.txt");


        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, tabScore);

    }

    @Test
    @DisplayName("Test de enregistrerScore")
    void enregistrerScore() {
    }

    @Test
    @DisplayName("Test de recupererPartie")
    void recupererPartie() {
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