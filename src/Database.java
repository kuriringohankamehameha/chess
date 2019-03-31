import java.util.ArrayList;

import static java.lang.Math.abs;

public class Database {
    //Stores an arraylist of sprites
    public ArrayList<Sprite> dList = new ArrayList<>(16);

    public Database()
    {

    }

    public void addSpritestoDatabase(Sprite sprite)
    {
        if(sprite.label=="KING")
            dList.add(0,sprite);
        if(sprite.label=="QUEEN")
            dList.add(1,sprite);
        if(sprite.label=="BISHOP")
            dList.add(2,sprite);
        if(sprite.label=="ROOK")
            dList.add(3,sprite);
    }

    public void destroyConflicts(int xc, int yc)
    {
        for(int i=0;i<dList.size()-1;i++)
        {

            if(((dList.get(i).x == xc) || (abs(dList.get(i).x - xc))<=11) && ((dList.get(i).y == yc)|| (abs(dList.get(i).y - yc))<=11))
            {
                //Based on what landed first, resolve conflict
                destroySprite(dList.get(i));
            }

        }

    }

    public void destroySprite(Sprite sprite)
    {
        sprite.visible = false;
        return;

    }
}
