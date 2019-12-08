import java.awt.Graphics.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Component;
public class Bullet extends Sprite
{
    private final int BOARD_DIM = 1100;
    private final int BUL_SPEED = 7;
    private int cX, cY, bDir, delay;
    
    public Bullet(int x, int y, int dir)
    {
        super(x, y);
        bDir = dir;
        delay = 100;
        if (bDir == 1)
        {
            image = loadImage("images/bullet_up.png");
        } 
        else if (bDir == 2)
        {
            image = loadImage("images/bullet_right.png");
        }
        else if (bDir == 3)
        {
            image = loadImage("images/bullet_down.png");
        }
        else if (bDir == 4)
        {
            image = loadImage("images/bullet_left.png");
        }
        getImageDim();
    }
    
    public void move()
    {
        changeBul();
        mX += cX;
        mY += cY;
        
        if (mX > BOARD_DIM || mY > BOARD_DIM || mX < 10 || mY < 10)
        {
            setVisible(false);
        }
    }
    
    public void changeBul()
    {
        if (bDir == 1)
        {
             cY = -BUL_SPEED;
        }
        
        if (bDir == 2)
        {
            cX = BUL_SPEED;
        }
        
        if (bDir == 3)
        {
            cY = BUL_SPEED;
        }
        
        if (bDir == 4)
        {
            cX = -BUL_SPEED;
        }
    }
    
    public void linger()
    {
    	delay--;
    	if (delay == 0)
    	{
    		setVisible(false);
    	}
    }
}
