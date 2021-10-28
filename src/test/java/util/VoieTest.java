package util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Test du getter Vehicule")
    void getVehicule() {
        Voie v1 = new Voie("Voie 1", 1, 1, 1);
        Voie v2 = new Voie("Voie 2", 4, 2, 1);
        //TO DO
    }

    @Test
    void getVitesse() {
    }

    @Test
    void getType() {
    }

    @Test
    void setVehicleCoef() {
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
        assertFalse(c_true / c_false > 0.015);
        assertFalse(c_true / c_false < 0.005);

    }

    @Test
    void avancerVelo() {
    }

    @Test
    void avancerVoiture() {
    }

    @Test
    void unTourVoie() {
    }
}