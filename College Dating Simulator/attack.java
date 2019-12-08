

public class attack {
	
	private String myName;
	private int myDmg, myPP;
	
	public attack(String name, int dmg)
	{
		myName = name;
		myDmg = dmg;
		myPP = 5;
	}

	public String getName()
	{
		return myName;
	}
	
	public int getDmg()
	{
		return myDmg;
	}
	
	public int getPP()
	{
		return myPP;
	}
	
	public int attackEnemy(enemy e)
	{
		myPP--;
		
		if(myName.equals("Milk Tea & Boba"))
		{
			if (e.getName().equals("ABG Girl") || e.getName().equals("Professor Narges Norouzi"))
			{
				e.takeDmg(myDmg*2);
				return 1;
			}
			else if (e.getName().equals("12A Student"))
			{
				e.takeDmg(myDmg/2);
				return -1;
			}
		}
		else if (myName.contains("Extra"))
		{
			if (e.getName().equals("12A Student"))
			{
				e.takeDmg(myDmg*2);
				return 1;
			}
			else if (e.getName().equals("Football Player"))
			{
				e.takeDmg(myDmg/2);
				return -1;
			}
		}
		else if (myName.equals("Gatorade"))
		{
			if (e.getName().equals("Football Player"))
			{
				e.takeDmg(myDmg*2);
				return 1;
			}
			else if (e.getName().equals("Weeaboo Guy"))
			{
				e.takeDmg(myDmg/2);
				return -1;
			}
		}
		else if (myName.contains("Japanese"))
		{
			if (e.getName().equals("Weeaboo Guy"))
			{
				e.takeDmg(myDmg*2);
				return 1;
			}
			else if (e.getName().equals("ABG Girl"))
			{
				e.takeDmg(myDmg/2);
				return -1;
			}
		}
		
		e.takeDmg(myDmg);
		return 0;
	}
}
