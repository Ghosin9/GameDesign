

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class player extends sprite{

	public player(String name, int hp) 
	{
		super(name, hp, 10);
		image = new ImageIcon(this.getClass().getResource("images/stickfigure.png"));
		attack = new ImageIcon(this.getClass().getResource("images/stickfigure.png"));
		attacks = new ArrayList<attack>();
		attacks.add(new attack("Milk Tea & Boba", 3));
		attacks.add(new attack("Extra Credit on the Final", 3));
		attacks.add(new attack("Gatorade", 3));
		attacks.add(new attack("Japanese ~kawaii desu~", 3));
	}
}
