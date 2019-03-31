import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * King is an instance of a piece that can move only one step in every direction (up, down, left, right and diagonal)
 * TODO:
 * Place the king image on the board and make it move
 * */

public class King extends Sprite implements MouseListener{

    public int choice=0;

    public King(int x, int y)
    {
        super(x,y);
        super.label = "KING";
        super.visible = true;

        initKing();

    }

    public King(int x, int y, ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "KING";
        super.visible = true;
        super.list=al;

        initKing();

    }

    public void initKing() {

       loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wking.png");
        this.image = this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        getImageDimensions();

    }

    public void mouseClicked(MouseEvent e)
    {
        if(!this.visible)
            return;
        //Default offsets for the mouse
        int xoffset = -25;
        int yoffset = -55;

        int xc = e.getX();
        int yc = e.getY();

        xc+= xoffset;
        yc+= yoffset;

        if(choice==0)
        {
            if(abs(this.x - xc)<=11 && abs(this.y - yc)<=11) {
                moveDiagonally(this.x, this.y, xc, yc);
                choice = 1;
            }
            return;
        }

        if(choice == 1)
        {
            if((this.y <= yc + 22 && this.y >= yc -22) && !(this.x <= xc + 22 && this.x >= xc -22))
            {
                if(abs(this.x - xc)<=50) {
                    moveHorizontally(this.x, xc);
                    resolveConflicts(this.x, this.y);
                }
                choice = 0;
                return ;
            }

            if((abs(this.x - xc)<=22 && !(abs(this.y - yc)<=22))) {
                if(abs(this.y - yc)<=60) {
                    moveVertically(this.y, yc);
                    resolveConflicts(this.x, this.y);
                }
                choice = 0;
                return ;
            }

            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                if(abs(this.x - xc)<=50 && abs(this.y - yc)<=50) {
                    moveDiagonally(this.x, this.y, xc, yc);
                    resolveConflicts(this.x, this.y);
                }
                    choice = 0;
                return ;
            }

            choice = 0;
            return;

        }
    }

    public void mousePressed(MouseEvent e)
    {

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
