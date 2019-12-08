

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class sprite {
	
	protected String myName;
	protected ImageIcon image, attack;
	protected int myHP, maxHP;
	protected ArrayList<attack> attacks;
	
	public sprite(String name, int hp, int max)
	{
		myName = name;
		myHP = hp;
		maxHP = max;
	}

	public String getName()
	{
		return myName;
	}
	
	public ImageIcon getImage()
	{
		return image;
	}
	
	public ImageIcon setAttackImage(String imageURL)
	{
		attack = new ImageIcon(this.getClass().getResource(imageURL));
		return attack;
	}
	
	public int getHP()
	{
		return myHP;
	}
	
	public int getMaxHP()
	{
		return maxHP;
	}
	
	public ArrayList<attack> getAttacks()
	{
		return attacks;
	}
}
