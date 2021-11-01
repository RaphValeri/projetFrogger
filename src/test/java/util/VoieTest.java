package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import util.Voiture;
import util.Camion;

class VoieTest {

    @Test
    @DisplayName("Test du getter Name")
    void getName() {
        Voie v1 = new Voie("Voie 1", 1, 1, 1);
        Voie v2 = new Voie("Voie 2", 1, 2, 1);
        assertEquals("Voie 1", v1.getName());
        assertEquals("Voie 2", v2.getName());
    }


    @Test
    @DisplayName("Test du getter vitesse")
    void getVitesse() {
        Voie v = new Voie("Voie 1", 2, 1, 1);
        int level =2;
        float vitesse_moyenne = 0;
        for(int i=0; i<1000; i++){
            double vitesse = (1.1 + 0.05 * (Math.random() - 0.5)) - 1 / Math.pow(level, 0.1);
            vitesse_moyenne += vitesse;
        }
        vitesse_moyenne /=1000;
        assertFalse(vitesse_moyenne>0.17);
        assertFalse(vitesse_moyenne<0.16);
    }

    @Test
    @DisplayName("Test du getter Type")
    void getType() {
        float c_voiture = 0;
        float c_camion = 0;
        Voie v = new Voie("Voie 1", 1, 10, 1);
        for(int i=0; i<1000; i++){
            int rd = new Random().nextInt(2);
            int type = rd * Voiture.tailleVoiture + (1 - rd) * Camion.tailleCamion;
            if(type == Voiture.tailleVoiture) c_voiture+=1;
            else c_camion+=1;
        }
        assertFalse(c_voiture/c_camion > 1.2);
        assertFalse(c_voiture/c_camion < 0.8);

    }



    @Test
    @DisplayName("Test de la méthode passage")
    void passage() {
        float c_true = 0;
        float c_false = 0;
        Voie v = new Voie("Voie 1", 1, 1, 1);
        for(int i=0; i<1000; i++){
            if(v.passage()) c_true += 1;
            else c_false +=1;
        }
        assertFalse(c_true / c_false > 0.018);
        assertFalse(c_true / c_false < 0.002);

    }


}