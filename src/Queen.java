import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.lang.Math.abs;


public class Queen extends Sprite implements MouseListener {

    public int offset;
    public int[][] moveset;
    public int choice = 0;

    public Queen(int x,int y)
    {
        super(x,y);
        super.label = "QUEEN";
        super.visible = true;

        initQueen();
    }

    public void initQueen()
    {
        loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wqueen.png");
        this.image=this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        getImageDimensions();
    }

    public void move(Square from, Square to)
    {
        int row = from.row_index - to.row_index;
        y+=row;

        int col = from.col_index - to.col_index;
        x+=col;

        if(x > 8 ||y > 8) {
            visible = false;
        }

    }

    public void move(int from, int to)
    {
        y+=0;

        x+=to-from;

        if(x > 8 ||y > 8) {
            visible = false;
        }

        this.offset = (to-from)/2;

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int xc = e.getX();
        int yc = e.getY();

        int xoffset = -25;
        int yoffset = -55;

        xc+=xoffset;
        yc+=yoffset;

        if(choice == 0)
        {
            if(abs(this.x - xc)<=11 && abs(this.y - yc)<=11) {
                moveDiagonally(this.x, this.y, xc, yc);
                choice = 1;
            }
            return;
        }

        if(choice==1)
        {
            super.mouseClicked(e);
            choice=0;
            return;
        }

    }

}
