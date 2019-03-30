import javax.swing.*;
import java.awt.*;

/**
 * King is an instance of a piece that can move only one step in every direction (up, down, left, right and diagonal)
 * TODO:
 * Place the king image on the board and make it move
 * */

public class King extends Piece{

    private Image image;
    private int w;
    private int h;

    private int x = 40;
    private int y = 60;

    public King()
    {
        /**
         * Default constructor for King
         */
        loadImage();

    }

    public void loadImage() {

        ImageIcon ii = new ImageIcon("src/images/wking.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public Image getImage() {

        return image;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }


}
