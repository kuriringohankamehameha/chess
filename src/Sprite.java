import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

import static java.lang.Math.abs;

public class Sprite implements MouseListener {

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

    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseClicked(MouseEvent e) {
        //System.out.println("Mouse clicked\n");
        int xc = e.getX();
        int yc = e.getY();
        int xoffset = -25;
        int yoffset = -55;
        xc+=xoffset;
        yc+=yoffset;
        int move[] = new int[2];

        int decision = 0;


            //Choice is made and you can move
            if((this.y <= yc+22 && this.y >=yc-22 )&& !(this.x <= xc+22 & this.x >= xc-22)) {
                moveHorizontally(this.x, xc);
                decision = 0;
                return ;
            }

            if((this.x <= xc+22 && this.x >=xc-22 )&& !(this.y <= yc+22 & this.y >= yc-22)) {
                moveVertically(this.y, yc);
                decision = 1;
                return ;
            }

            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                moveDiagonally(this.x, this.y, xc, yc);
                decision = 2;
                return ;
            }

            /**
             * TODO: Highlight available squares based on decision value on the board (??)
             */




    }

    public int[] makeChoice(int x, int y)
    {
        //After this, you can only click on the highlighted squares, or click the piece itself to revert back
        //Setting the from and to coordinates for movement, if possible
        int m[] = new int[2];
        m[0] = x;
        m[1] = y;

        return m;

    }
    /*
    public int[][] getAvailableMoves()
    {

    }
    */

    public void recentre(int x, int y)
    {
        //Recentres the piece to the center of the nearest square
        if(x%44!=0 && x%44>22)
        {
            this.x=x+(x%44-22);
        }
        else if(x%44!=0 && x%44<22)
        {
            this.x=x-(22-x%44);
        }

        if(y%44!=0 && y%44>22)
        {
            this.y=y+(y%44-22);
        }
        else if(y%44!=0 && y%44<22)
        {
            this.y=y-(22-y%44);
        }


    }

    public void mouseReleased(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mouseEntered(MouseEvent e)
    {

    }


}

