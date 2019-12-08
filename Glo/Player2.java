import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class Player2 extends Sprite
{
    private final int BOARD_DIM = 700;
    private ArrayList<Bullet> bullets;
    private int cX, cY, dir;
    private boolean shield;
    private Image image_up, image_down, image_right, image_left;

    public Player2(int x, int y)
    {
        super(x, y);
        dir = 1;
        //52 x 30
        image_up = loadImage("images/player2_1.png");
        image_right = loadImage("images/player2_2.png");
        image_down = loadImage("images/player2_3.png");
        image_left = loadImage("images/player2_4.png");
        image = image_up;

        bullets = new ArrayList<Bullet>();
        shield = false;
        getImageDim();
    }

    public void move()
    {
        if (getWall() == null)
        {
            mX += cX;
            mY += cY;
        }
        else
        {
            Rectangle r = new Rectangle (mX + cX, mY + cY, width, height);
            Rectangle w = getWall().getBounds();
            if (r.intersects(w))
            {
                return;
            }
            else
            {
                mX += cX;
                mY += cY;
            }
        }
    }

    public void fire()
    {
        if (bullets.size() < 1)
        {
            bullets.add(new Bullet(mX+10, mY, dir));
            SoundEffect pew = SoundEffect.SHOOT;
            pew.play(false);
        }
    }

    public int getDir()
    {
        return dir;
    }

    public boolean isShield()
    {
        return shield;
    }

    public void setShield(Boolean bol)
    {
        shield = bol;
    }

    public ArrayList<Bullet> getBullets()
    {
        return bullets;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ENTER)
        {
            fire();
        }

        if (key == KeyEvent.VK_LEFT)
        {
            cX = -2;
            dir = 4;
            image = image_left;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            cX = 2;
            dir = 2;
            image = image_right;
        }

        if (key == KeyEvent.VK_UP)
        {
            cY = -2;
            dir = 1;
            image = image_up;
        }

        if (key == KeyEvent.VK_DOWN)
        {
            cY = 2;
            dir = 3;
            image = image_down;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            cX = 0;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            cX = 0;
        }

        if (key == KeyEvent.VK_UP)
        {
            cY = 0;
        }

        if (key == KeyEvent.VK_DOWN)
        {
            cY = 0;
        }
    }
}
