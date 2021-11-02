package graphicalElements;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    static  int value;
    @Test
    @DisplayName("Test de la méthode start")
    void start() {

    }

    @Test
    void imageFrog() throws Exception {
        App app = new App();
        Image im = app.imageFrog(500/6, 500/10);
        //assertNotNull(im);
        
    }

    @Test
    void imageBackground() {
    }

    @Test
    void rotate() {
    }

    @Test
    void main() throws Exception {
        App app = new App();
        //value = app.main();
    }
}