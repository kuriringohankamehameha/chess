import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Knight extends Sprite implements MouseListener {
    public int choice=0;

    public Knight(int x, int y)
    {
        super(x,y);
        super.label = "KNIGHT";
        super.visible = true;

        initKnight();

    }

    public Knight(int x, int y, ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "KNIGHT";
        super.visible = true;
        super.list=al;

        initKnight();

    }

    public void initKnight() {

        loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wknight.png");
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
                return;
            }
        }

        if(choice == 1)
        {
            System.out.println("X, Y diff are  ("+ abs(this.x - xc) + " , " + abs(this.y - yc) + " )\n");
            //2 steps in horiz and one vertical

            //if((abs(this.y + 44 - yc)<= 22) && !(this.x <= xc + 22 && this.x >= xc -22))
            if(abs(this.x - xc)<=50 && abs(this.y - yc)<=110 && abs(this.x - xc)>=22 && abs(this.y - yc)>=22)
            {

                //if(abs(this.x - xc)<=50+44) {
                //if(abs(this.x - xc)<=44+44+22) {
                    moveHorizontally(this.x, xc);
                    moveVertically(this.y, yc);
                    resolveConflicts(this.x, this.y);
                //}
                choice = 0;
                return ;
            }

            //if((abs(this.x + 44- xc)<=22)&& !(this.y <= yc+22 & this.y >= yc-22)) {
            if(abs(this.x - xc)<=110 && abs(this.y - yc)<=65 && abs(this.x - xc)>=22 && abs(this.y - yc)>=22)
            {
                //if(abs(this.y - yc)<=44+44+22) {
                    moveHorizontally(this.x,xc);
                    moveVertically(this.y, yc);
                    resolveConflicts(this.x, this.y);

                //}
                choice = 0;
                return ;
            }

            choice = 0;
            return;

        }
    }

}
