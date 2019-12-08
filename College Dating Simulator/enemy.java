

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class enemy extends sprite{

	public enemy(String name, int hp) 
	{
		super(name, 0, 10);
		image = new ImageIcon(this.getClass().getResource("images/stickfigure.png"));
		attacks = new ArrayList<attack>();
		if(myName.equals("ABG Girl"))
		{
			image = new ImageIcon(this.getClass().getResource("images/abggirl.png"));
			attacks.add(new attack("Gossip to the Group Chat", 1));
			attacks.add(new attack("Rave", 2));
			attacks.add(new attack("2 shots of Hennessy", 2));
		}
		else if(myName.equals("12A Student"))
		{
			image = new ImageIcon(this.getClass().getResource("images/12astudent.png"));
			attacks.add(new attack("Forgetting a Semi-colon", 1));
			attacks.add(new attack("Asking for an extension on a 'hard' Zybooks lab", 2));
		}
		else if (myName.equals("Football Player"))
		{
			image = new ImageIcon(this.getClass().getResource("images/footballplayer.png"));
			attacks.add(new attack("Athlete's Foot", 1));
			attacks.add(new attack("Tackle", 2));
		}
		else if (myName.equals("Weeaboo Guy"))
		{
			image = new ImageIcon(this.getClass().getResource("images/weeabookid.png"));
			attacks.add(new attack("Rasengan", 1));
			attacks.add(new attack("Bad Japanese", 2));
		}
		else if (myName.equals("Professor Narges Norouzi"))
		{
			maxHP = 20;
			image = new ImageIcon(this.getClass().getResource("images/narges.png"));
			attacks.add(new attack("Cringy Memes", 1));
			attacks.add(new attack("Weirdly Worded Questions on the Midterm", 2));
			attacks.add(new attack("F on the final", 2));
		}
		attack = image;
	}
	
	public ImageIcon setAttackImage(int num)
	{
		if(myName.equals("ABG Girl"))
		{
			if (num == 0)
				attack = new ImageIcon(this.getClass().getResource("images/gossip.png"));
			if (num == 1)
				attack = new ImageIcon(this.getClass().getResource("images/rave.png"));
			if (num ==2)
				attack = new ImageIcon(this.getClass().getResource("images/henny.png"));
		}
		else if(myName.equals("12A Student"))
		{
			if (num == 0)
				attack = new ImageIcon(this.getClass().getResource("images/semi-colon.png"));
			if (num == 1)
				attack = new ImageIcon(this.getClass().getResource("images/extension.png"));
		}
		else if (myName.equals("Football Player"))
		{
			if (num == 0)
				attack = new ImageIcon(this.getClass().getResource("images/foot.png"));
			if (num == 1)
				attack = new ImageIcon(this.getClass().getResource("images/tackle.png"));
		}
		else if (myName.equals("Weeaboo Guy"))
		{
			if (num == 0)
				attack = new ImageIcon(this.getClass().getResource("images/rasengan.png"));
			if (num ==1)
				attack = new ImageIcon(this.getClass().getResource("images/japanese.png"));
		}
		else if (myName.equals("Professor Narges Norouzi"))
		{
			if (num == 0)
				attack = new ImageIcon(this.getClass().getResource("images/meme.png"));
			if (num == 1)
				attack = new ImageIcon(this.getClass().getResource("images/midterm.png"));
			if (num == 2)
				attack = new ImageIcon(this.getClass().getResource("images/f.png"));
		}
		
		return attack;
	}
	
	public void takeDmg(int dmg)
	{
		myHP += dmg;
	}
}
