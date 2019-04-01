import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Pawn extends Sprite implements MouseListener {
    public int choice=0;

    public Pawn(int x, int y)
    {
        super(x,y);
        super.label = "PAWN";
        super.visible = true;

        initPawn();

    }

    public Pawn(int x, int y, ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "PAWN";
        super.visible = true;
        super.list=al;

        initPawn();

    }

    public void initPawn() {

        loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wpawn.png");
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
            if((this.x <= xc+22 && this.x >=xc-22 )&& !(this.y <= yc+22 & this.y >= yc-22)) {
                if((this.y - yc)>=50) {
                    moveVertically(this.y, yc);
                }
                choice = 0;
                return ;
            }

            if((abs(this.x - xc)<=abs(this.y-yc)+11 && abs(this.x - xc)>=abs(this.y - yc)-11) || (abs(this.y - yc)<=abs(this.x-xc)+11 && abs(this.y - yc)>=abs(this.x - xc)-11))
            {
                Boolean a = isPiece(this.x+44,this.y-44);
                Boolean b = isPiece(this.x-44,this.y-44);
                if((this.y>yc)&&(a || b)){
                   if(a && this.x+40<=xc && this.x+52>=xc) {
                       moveDiagonally(this.x, this.y, xc, yc);
                       resolveConflicts(this.x, this.y);
                       choice = 0;
                       return;
                   }

                    if(b && xc+40<=this.x && xc+52>=this.x) {
                        moveDiagonally(this.x, this.y, xc, yc);
                        resolveConflicts(this.x, this.y);
                        choice = 0;
                        return;
                    }

                }
                choice = 0;
                return ;
            }

            choice = 0;
            return;

        }
    }
}
