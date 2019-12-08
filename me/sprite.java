import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class sprite
{
	protected int mX, mY, cX, cY, width, height;
    protected Image image;
    protected boolean visible;
    protected boolean carrying;
    protected boolean moveable;
    protected ArrayList<projectile> bullets;
    protected boolean free;
    protected boolean remain;
    
    public sprite(int x, int y)
    {
        mX = x;
        mY = y;
        cX = 0;
        cY = 0;
        visible = true;
        carrying = false;
        moveable = true;
        free = false;
        remain = false;
        bullets = new ArrayList<projectile>();
    }
    
    public Image loadImage(String url)
    {
        URL imageURL = this.getClass().getResource(url);
        ImageIcon ii = new ImageIcon(imageURL);
        image = ii.getImage();
        getImageDim();
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
    
    public void move()
    {
    	if(moveable)
    	{
    		if(!free)
    		{
		    	if (mX+cX < 1 || mX+cX > 500)
		    		cX = 0;
		    	if(mY+cY < 20 || mY+cY > 400)
		    		cY = 0;
    		}
	    	mX += cX;
	    	mY += cY;
    	}
    }
    
    public void setChangeX(int x)
    {
    	cX = x;
    }
    
    public void setChangeY(int y)
    {
    	cY = y;
    }
    
    public ArrayList<projectile> getBullets()
    {
    	return bullets;
    }
    
    public void setX(int x)
    {
    	mX = x;
    }
    
    public void setY(int y)
    {
    	mY = y;
    }
    
    public boolean isCarrying()
    {
    	return carrying;
    }
    
    public void setCarry(boolean car)
    {
    	carrying = car;
    }
    
    public void setMoveable(boolean move)
    {
    	moveable = move;
    }
    
    public void setFree(boolean f)
    {
    	free =f ;
    }
    
    public void follow(sprite p)
    {
    	this.mX = p.getX()+p.getWidth()/2+p.getWidth()/4;
    	this.mY = p.getY()+p.getHeight()/2;
    }
    
    public boolean getRemain()
    {
    	return remain;
    }
    
    public void setRemain(boolean r)
    {
    	remain = r;
    }
}
