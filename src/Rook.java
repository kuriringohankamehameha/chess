import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static java.lang.Math.abs;

public class Rook extends Sprite implements MouseListener {

    public int choice=0;

    public Rook(int x, int y)
    {
        super(x,y);
        super.label = "ROOK";
        super.visible = true;

        initRook();

    }
    public Rook(int x, int y, ArrayList<Sprite> al)
    {
        super(x,y);
        super.label = "ROOK";
        super.visible = true;
        super.list=al;

        initRook();

    }


    public void initRook() {

        loadImage("/Users/ramachandran/IdeaProjects/chess+networking/src/images/wrook.png");
        this.image = this.image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        getImageDimensions();

    }

    public void mouseClicked(MouseEvent e)
    {
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
            if((this.y <= yc+22 && this.y >=yc-22 )&& !(this.x <= xc+22 & this.x >= xc-22)) {
                moveHorizontally(this.x, xc);
                resolveConflicts(this.x,this.y);
                System.out.println("RESOLVING CONFLICT HORIZ\n");

                choice = 0;
                return ;
            }

            if((this.x <= xc+22 && this.x >=xc-22 )&& !(this.y <= yc+22 & this.y >= yc-22)) {
                moveVertically(this.y, yc);
                resolveConflicts(this.x,this.y);
                System.out.println("RESOLVING CONFLICT VERRT\n");
                choice = 0;
                return ;
            }

            choice = 0;
            return;

        }
    }
}
