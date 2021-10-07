package TEST;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;


public class graphics2D extends JFrame {

    public graphics2D() {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("FROGGER");
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        Dimension D = getContentPane().getSize();
        int width = D.width;
        int height = D.height;
        int borderHeight = getHeight() - height;

        for (int i = borderHeight; i < height; i += height/10) {
            g2d.draw(new Line2D.Double(0, i, width, i));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new graphics2D().setVisible(true);
            }
        });
    }
}