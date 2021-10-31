package graphicalElements;

import javafx.scene.image.Image;

public interface IFroggerGraphics {
    Image imageFrog(int dx, int dy) throws Exception;
    Image imageBackground(int dx, int dy) throws Exception;
}
