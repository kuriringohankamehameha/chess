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
    public String color;
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
                if(!allyCheck(xc,yc)&& horizontalClear(x,y,xc,yc))
                moveHorizontally(this.x, xc);
                return ;
            }

            if((this.x <= xc+22 && this.x >=xc-22 )&& !(this.y <= yc+22 & this.y >= yc-22)) {
                if(!allyCheck(xc,yc)&& verticalClear(x,y,xc,yc))
                moveVertically(this.y, yc);
                return ;
            }

            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                if(!allyCheck(xc,yc)&& diagonalClear(x,y,xc,yc))
                moveDiagonally(this.x, this.y, xc, yc);
                return ;
            }
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

        else if(sp.label == "ROOK") {
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

        if(sp.label=="ROOK" || sp.label=="KNIGHT" || sp.label=="PAWN")
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

        else if(sp.label == "KING")
        {
            setChoiceVisible=true;
            return;
        }

        else if(sp.label == "PAWN")
        {
            setChoiceVisible=true;
            return;
        }

        else if(sp.label == "KNIGHT")
        {
            setChoiceVisible=true;
            return;
        }


    }

    public boolean allyCheck(int x, int y) {
        //Checks if piece at (x,y) is an ally
        for (int i = 0; i < this.list.size(); i++) {
            if ((this.list.get(i).visible == true) && abs(this.list.get(i).x - x)<=10 && abs(this.list.get(i).y - y)<=10) {
                if (this.list.get(i).color == this.color) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean enemyCheck(int x, int y) {
        //Checks if piece at (x,y) is an ally
        for (int i = 0; i < this.list.size(); i++) {
            if ((this.list.get(i).visible == true) && abs(this.list.get(i).x - x)<=10 && abs(this.list.get(i).y - y)<=10) {
                if (this.list.get(i).color != this.color) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean horizontalClear(int xfrom, int yfrom, int xto, int yto) {
        //Checks if piece at (x,y) is an ally
        int inc=44;
        if(xto<xfrom) {
            inc = -44;
            for (int i = xfrom+inc; i >= xto; i += inc) {
                if (allyCheck(i, yfrom))
                    return false;
            }
        }
        else {
            for (int i = xfrom+inc; i <= xto; i += inc) {
                if (allyCheck(i, yfrom))
                    return false;
            }
        }
        return true;
    }

    public boolean verticalClear(int xfrom, int yfrom, int xto, int yto) {
        //Checks if piece at (x,y) is an ally
        int inc=44;
        if(yto<yfrom) {
            inc = -44;
            for (int i = yfrom+inc; i >= yto; i += inc) {
                if (allyCheck(xfrom, i))
                    return false;
            }
        }
        else {
            for (int i = yfrom+inc; i <= yto; i += inc) {
                if (allyCheck(xfrom, i))
                    return false;
            }
        }
        return true;
    }

    public boolean diagonalClear(int xfrom, int yfrom, int xto, int yto) {

        int xinc = 44;
        int yinc = 44;
        if(yto<yfrom)
            yinc = -44;
        if(xto<xfrom)
            xinc = -44;

        int j=yfrom+yinc;
        if(xinc == yinc && xinc == 44) {
            for (int i = xfrom + xinc; i <= xto && j <= yto; i += xinc) {
                if (allyCheck(i, j))
                    return false;
                j += yinc;
            }
        }

        else if(xinc==44 && yinc ==-44)
        {
            for (int i = xfrom + xinc; i <= xto && j >= yto; i += xinc) {
                if (allyCheck(i, j))
                    return false;
                j += yinc;
            }
        }

        else if(xinc==-44 && yinc==-44) {
            for (int i = xfrom + xinc; i >= xto && j >= yto; i += xinc) {
                if (allyCheck(i, j))
                    return false;
                j += yinc;
            }
        }

        else if(xinc==-44 && yinc==44) {
            for (int i = xfrom + xinc; i >= xto && j <= yto; i += xinc) {
                if (allyCheck(i, j))
                    return false;
                j += yinc;
            }
        }

        return true;
    }

    public boolean enemydiagonalClear(int xfrom, int yfrom, int xto, int yto) {

        int xinc = 44;
        int yinc = 44;
        if(yto<yfrom)
            yinc = -44;
        if(xto<xfrom)
            xinc = -44;

        int j=yfrom+yinc;
        if(xinc == yinc && xinc == 44) {
            for (int i = xfrom + xinc; i <= xto && j <= yto; i += xinc) {
                if (enemyCheck(i, j))
                    return false;
                j += yinc;
            }
        }

        else if(xinc==44 && yinc ==-44)
        {
            for (int i = xfrom + xinc; i <= xto && j >= yto; i += xinc) {
                if (enemyCheck(i, j))
                    return false;
                j += yinc;
            }
        }

        else if(xinc==-44 && yinc==-44) {
            for (int i = xfrom + xinc; i >= xto && j >= yto; i += xinc) {
                if (enemyCheck(i, j))
                    return false;
                j += yinc;
            }
        }

        else if(xinc==-44 && yinc==44) {
            for (int i = xfrom + xinc; i >= xto && j <= yto; i += xinc) {
                if (enemyCheck(i, j))
                    return false;
                j += yinc;
            }
        }

        return true;
    }

    public int enemydiagonalCount(int xfrom, int yfrom, int xto, int yto) {

        int xinc = 44;
        int yinc = 44;
        if(yto<yfrom)
            yinc = -44;
        if(xto<xfrom)
            xinc = -44;

        int tc = 0;

        int j=yfrom+yinc;
        if(xinc == yinc && xinc == 44) {
            for (int i = xfrom + xinc; i <= xto && j <= yto; i += xinc) {
                if (enemyCheck(i, j))
                {
                    tc++;
                }
                j += yinc;
            }
        }

        else if(xinc==44 && yinc ==-44)
        {
            for (int i = xfrom + xinc; i <= xto && j >= yto; i += xinc) {
                if (enemyCheck(i, j))
                    tc++;
                j += yinc;
            }
        }

        else if(xinc==-44 && yinc==-44) {
            for (int i = xfrom + xinc; i >= xto && j >= yto; i += xinc) {
                if (enemyCheck(i, j))
                tc++;
                j += yinc;
            }
        }

        else if(xinc==-44 && yinc==44) {
            for (int i = xfrom + xinc; i >= xto && j <= yto; i += xinc) {
                if (allyCheck(i, j))
                    tc++;
                j += yinc;
            }
        }

        return tc;
    }

    public ArrayList<Integer> enemydiagonalCoordinates(int xfrom, int yfrom, int xto, int yto) {

        int xinc = 44;
        int yinc = 44;
        if(yto<yfrom)
            yinc = -44;
        if(xto<xfrom)
            xinc = -44;

        int tc=0;
        ArrayList<Integer> list = new ArrayList<>(2);

        int j=yfrom+yinc;
        if(xinc == yinc && xinc == 44) {
            for (int i = xfrom + xinc; i <= xto && j <= yto; i += xinc) {
                if (enemyCheck(i, j))
                {
                    if(tc!=0)
                        break;
                    tc++;
                    list.add(i);
                    list.add(j);
                }
                j += yinc;
            }
        }

        else if(xinc==44 && yinc ==-44)
        {
            for (int i = xfrom + xinc; i <= xto && j >= yto; i += xinc) {
                if (enemyCheck(i, j)) {
                    if(tc!=0)
                        break;
                    tc++;
                    list.add(i);
                    list.add(j);
                }
                j += yinc;
            }
        }

        else if(xinc==-44 && yinc==-44) {
            for (int i = xfrom + xinc; i >= xto && j >= yto; i += xinc) {
                if (enemyCheck(i, j)) {
                    if(tc!=0)
                        break;
                    tc++;
                    list.add(i);
                    list.add(j);
                }
                j += yinc;
            }
        }

        else if(xinc==-44 && yinc==44) {
            for (int i = xfrom + xinc; i >= xto && j <= yto; i += xinc) {
                if (enemyCheck(i, j)) {
                    if(tc!=0)
                        break;
                    tc++;
                    list.add(i);
                    list.add(j);
                }
                j += yinc;
            }
        }

        return list;
    }


}

