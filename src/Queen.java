import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Queen extends Sprite implements MouseListener {

    public Queen(int x,int y)
    {
        super(x,y);

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

    }

    public void mousePressed(MouseEvent e)
    {
        //move(0,5);
    }

    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Mouse clicked\n");
        move(0,5);
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
