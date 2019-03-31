import java.awt.Image;
import javax.swing.ImageIcon;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;
    public String label;

    //All pieces
    public Queen queen;
    public Bishop bishop;
    public King king;


    public Sprite()
    {

    }

    public Sprite(int x, int y)
    {
        this.x = x;
        this.y = y;
        visible = true;
    }

    public Sprite(int x, int y, Image i, String st) {

        this.x = x;
        this.y = y;
        visible = true;
        this.image = i;
        this.label = st;
    }

    public Sprite(Queen queen)
    {
        this.queen = queen;
        this.x = queen.x;
        this.y = queen.y;
        visible = queen.visible;
        this.image = queen.image;
        this.label = queen.label;
        this.width = queen.image.getWidth(null);
        this.height = queen.image.getHeight(null);
    }


    protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}

