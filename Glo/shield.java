public class shield extends Sprite
{
    boolean use, used;
    public shield(int x, int y)
    {
        super(x, y);
        use = false;
        used = false;
        image = loadImage("images/Shield.png");
        getImageDim();
    }
    
    public void follow(Player p)
    {
        mX = p.getX();
        mY = p.getY();
    }
    
    public void follow(Player2 p)
    {
        mX = p.getX();
        mY = p.getY();
    }
    
    public void remove()
    {
        mX = -1;
        mY = -1;
        image = null;
    }
    
    public boolean getUse()
    {
        return use;
    }
    
    public void setUse(boolean bol)
    {
        use = bol;
    }
    
    public boolean getUsed()
    {
        return used;
    }
    
    public void setUsed(boolean bol)
    {
        used = bol;
    }
}
