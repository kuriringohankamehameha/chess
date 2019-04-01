import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
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
    public boolean setChoiceVisible = false;

    //List
    public ArrayList<Sprite> list;
    public ArrayList<Integer> moveSetQueenx;
    public ArrayList<Integer> moveSetQueeny;


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

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Sprite)) return false;
        Sprite o = (Sprite) obj;
        return o.visible == this.visible;
    }

    public void resolveConflicts(int xc, int yc)
    {
        int i=0;
        for(Sprite sprite:this.list)
        {
            if(this!=sprite && abs(this.list.get(i).x - xc)==0 && abs(this.list.get(i).y - yc)==0)
            {
                this.list.get(i).visible = false;
                this.list.remove(sprite);
                return;
            }
            i++;
        }

        //this.list.removeIf(obj -> obj.visible == false);
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
        moveHorizontally(from_x,tox);
        moveVertically(from_y,toy);
    }

    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseClicked(MouseEvent e) {
        int xc = e.getX();
        int yc = e.getY();
        int xoffset = -25;
        int yoffset = -55;
        xc+=xoffset;
        yc+=yoffset;
        int move[] = new int[2];

            //Choice is made and you can move
            if((this.y <= yc+22 && this.y >=yc-22 )&& !(this.x <= xc+22 & this.x >= xc-22)) {
                moveHorizontally(this.x, xc);
                return ;
            }

            if((this.x <= xc+22 && this.x >=xc-22 )&& !(this.y <= yc+22 & this.y >= yc-22)) {
                moveVertically(this.y, yc);
                return ;
            }

            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                moveDiagonally(this.x, this.y, xc, yc);
                return ;
            }

            /**
             * TODO: Highlight available squares based on decision value on the board (??)
             */




    }

    public boolean isPiece(int x, int y)
    {
        for(int i=0;i<this.list.size();i++) {
            if ((this.list.get(i).visible==true) && this.list.get(i).x==x && this.list.get(i).y==y)
                return true;
        }

        return false;
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

    public void addtoSet(Sprite sp,int c)
    {
        if(sp.label == "QUEEN") {
            if (c == 0) {
                for (int i = 0; i < 8; i++) {
                    if (!(abs(this.x - 44 * i) <= 5)) {
                        this.moveSetQueenx.add(44 * i);
                        this.moveSetQueeny.add(this.y);

                    }


                }
            } else if (c == 1) {
                for (int i = 0; i < 8; i++) {
                    if (!(abs(this.y - 44 * i) <= 5)) {
                        this.moveSetQueenx.add(this.x);
                        this.moveSetQueeny.add(44 * i);
                    }

                }
            } else if (c == 2) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (this.x != 44 * i && this.y != 44 * j && (abs(abs(this.x - 44 * i) - abs(this.y - 44 * j)) <= 5)) {
                            this.moveSetQueenx.add(44 * i);
                            this.moveSetQueeny.add(44 * j);
                        }
                    }
                }
            }
        }

        else if(sp.label == "BISHOP")
        {
            if (c == 2) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (this.x != 44 * i && this.y != 44 * j && (abs(abs(this.x - 44 * i) - abs(this.y - 44 * j)) <= 5)) {
                            this.moveSetQueenx.add(44 * i);
                            this.moveSetQueeny.add(44 * j);
                        }
                    }
                }
            }
        }

        if(sp.label == "ROOK") {
            if (c == 0) {
                for (int i = 0; i < 8; i++) {
                    if (!(abs(this.x - 44 * i) <= 5)) {
                        this.moveSetQueenx.add(44 * i);
                        this.moveSetQueeny.add(this.y);

                    }


                }
            } else if (c == 1) {
                for (int i = 0; i < 8; i++) {
                    if (!(abs(this.y - 44 * i) <= 5)) {
                        this.moveSetQueenx.add(this.x);
                        this.moveSetQueeny.add(44 * i);
                    }

                }
            }
        }


    }

    public void removeSet(Sprite sp)
    {
        if(sp.label=="QUEEN")
        {
            int number = this.moveSetQueenx.size() - 1; //Careful!
            for(int i=number;i>=0;i--)
            {
                moveSetQueenx.remove(i);
                moveSetQueeny.remove(i);
            }

            return;
        }

        if(sp.label=="BISHOP")
        {
            int number = this.moveSetQueenx.size() - 1; //Careful!
            for(int i=number;i>=0;i--)
            {
                moveSetQueenx.remove(i);
                moveSetQueeny.remove(i);
            }

            return;
        }

        if(sp.label=="ROOK")
        {
            int number = this.moveSetQueenx.size() - 1; //Careful!
            for(int i=number;i>=0;i--)
            {
                moveSetQueenx.remove(i);
                moveSetQueeny.remove(i);
            }

            return;
        }
    }

    public void displayChoices(Sprite sp)
    {
        //Display all available choice squares
        if(sp.label=="QUEEN")
        {
            setChoiceVisible = true;
            return;
        }

        else if(sp.label=="BISHOP")
        {
            setChoiceVisible = true;
            return;
        }

        else if(sp.label=="ROOK")
        {
            setChoiceVisible = true;
            return;
        }

    }

}

