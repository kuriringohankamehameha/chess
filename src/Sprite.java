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


    public void moveHorizontally(int from, int to)
    {
        //assert to!=from;
        int distance = to - from;
        //this.x+= distance;
        //We need distance to be a multiple of 44
        if(distance > 0) {
            if (distance % 44 < 12) {
                distance-=(distance%44);
            } else if (distance % 44 > 27) {
                distance += (44 - distance % 44);
            }
            else
                return;
        }
        else
        {
            int pd = -(distance);
            if(pd % 44 < 12)
            {
                pd-=(pd)%44;
            }
            else if(pd%44 > 27)
            {
                pd+=(44 - pd%44);
            }
            else
                return;
            distance = -(pd);

        }


        assert distance%44 == 0;

        this.x+= distance;
    }

    public void moveVertically(int from, int to)
    {
        int distance = to - from;
        //We need distance to be a multiple of 44

        /*
        if(distance%44 < 12)
        {
            distance-= distance%44;
        }
        else if(distance%44 > 32)
        {
            distance+= (44 - distance%44);
        }
        else
            return;

        assert distance%44 == 0;
        this.y+= distance;
        */

        //this.x+= distance;
        //We need distance to be a multiple of 44
        //NEED TO REFACTOR CODE
        if(distance > 0) {
            if (distance % 44 < 12) {
                distance-=(distance%44);
            } else if (distance % 44 > 27) {
                distance += (44 - distance % 44);
            }
            else
                return;
        }
        else
        {
            int pd = -(distance);
            if(pd % 44 < 12)
            {
                pd-=(pd)%44;
            }
            else if(pd%44 > 27)
            {
                pd+=(44 - pd%44);
            }
            else
                return;
            distance = -(pd);

        }
        this.y+= distance;

    }

    public void moveDiagonally(int from_x, int from_y, int tox, int toy)
    {
        //As of now, Going to 1st, 2nd quadrants work

    /*
        int xd= (tox - from_x);
        int yd= (toy - from_y);

        if(xd%44 < 9) //CHANGE THIS
        {
            xd-= xd%44;
        }
        else if(xd%44 > 32)
        {
            xd+= (44 - xd%44);
        }
        else
            return;

        if(yd%44 < 12)
        {
            yd-= yd%44;
        }
        else if(yd%44 > 32)
        {
            yd+= (44 - yd%44);
        }
        else
            return;

        this.x+= xd;
        this.y+= yd;
        */

        moveHorizontally(from_x,tox);
        moveVertically(from_y,toy);
    }

}

