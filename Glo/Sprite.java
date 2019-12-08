import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.URL;


public class Sprite
{
    protected int mX, mY, width, height;
    protected Image image;
    private boolean visible, collide;
    protected wall mWall;
    
    public Sprite(int x, int y)
    {
        mX = x;
        mY = y;
        visible = true;
        collide = false;
    }
    
    public Image loadImage(String url)
    {
        URL imageURL = this.getClass().getResource(url);
        ImageIcon ii = new ImageIcon(imageURL);
        return ii.getImage();
    }
    
    public Image getImage()
    {
        return image;
    }
    
    public int getX()
    {
        return mX;
    }
    
    public int getY()
    {
        return mY;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public wall getWall()
    {
        return mWall;
    }
    
    public boolean getCollide()
    {
        return collide;
    }
    
    public void setCollide(boolean bol, wall w)
    {
        mWall = w;
        collide = bol;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(mX, mY, width, height);
    }
    
    public void getImageDim()
    {
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    public boolean isVisible()
    {
        return visible;
    }
    
    public void setVisible(boolean value)
    {
        visible = value;
    }
}
