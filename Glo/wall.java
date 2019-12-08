
/**
 * Write a description of class wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class wall extends Sprite
{
    public wall(int x, int y)
    {
        super(x,y);
        //100 x 10, gaps are 90-100
        image = loadImage("images/wall.png");
        getImageDim();
    }
    
    public wall(int x, int y, int w)
    {
        super(x,y);
        image = loadImage("images/wallUp.png");
        getImageDim();
    }
    
    public wall(int x, int y, String s)
    {
        super(x,y);
        //50 x 10, gaps are 50-60
        image = loadImage("images/smallwall.png");
        getImageDim();
    }
    
    public wall(int x, int y, boolean bol)
    {
        super(x,y);
        //50 x 10, gaps are 50-60
        image = loadImage("images/smallwallUp.png");
        getImageDim();
    }
}
