package graphicalElements;

import javafx.scene.image.Image;

/**
 * Interface de gestion de la représentation graphique de la grenouille
 */
public interface IFroggerGraphics {
    Image imageFrog(int dx, int dy) throws Exception;
    Image imageBackground(int dx, int dy) throws Exception;
}
