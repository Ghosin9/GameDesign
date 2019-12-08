public class projectile extends sprite
{
	public projectile(int x, int y, boolean isPlayer)
	{
		super(x,y);
		if(isPlayer)
			cX = 1;
		else
			cX = -1;
	}
	
	public void move()
    {
    	if (mX+cX < 1 || mX+cX > 700)
    		visible = false;
    	if(mY+cY < 20 || mY+cY > 500)
    		visible = false;
    		
    	mX += cX;
    	mY += cY;
    }
}