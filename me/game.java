import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class game extends JFrame
{
	private DrawPanel dp;
	
	private JTextArea textArea;
	private Font textFont = new Font("Comic Sans MS", Font.BOLD, 24);
	
	private JButton next;
	private Font nextFont = new Font("Comic Sans MS", Font.BOLD, 50);
	
	private String text;
	private int x, stage;
	
	private sprite player;
	private ArrayList<sprite> sprites;
	private sprite star;
	private ArrayList<sprite> holdables;
	private int counting;
	private ArrayList<sprite> walls;
	private Random ran;
	
	private boolean drawRect;

	public static void main(String[] args)
	{
		game g = new game();
	}
	
	public game()
	{
		ran = new Random();
		stage = 0;
		drawRect= true;
		counting = 0;
		setSize(700, 700);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.white);
		
		text = "Use the Arrow keys to move and the mouse to click on buttons. The Star is your friend.";
		textArea = new JTextArea();
		textArea.setBackground(Color.white);
		textArea.setForeground(Color.black);
		textArea.setFont(textFont);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		add(textArea, BorderLayout.PAGE_START);
		textArea.addKeyListener(new keyboard());
		
		player = new sprite(150, 25);
		player.loadImage("images/player.png");
		star = new sprite(500, 25);
		star.loadImage("images/star.png");
		star.setVisible(false);
		dp = new DrawPanel();
		add(dp, BorderLayout.CENTER);
		
		next = new JButton("Next " + counting + "/100");
		next.setFont(nextFont);
		next.addActionListener(new listener());
		add(next, BorderLayout.PAGE_END);
		
		sprites = new ArrayList<sprite>();
		holdables = new ArrayList<sprite>();
		walls = new ArrayList<sprite>();
		
		level.start();
		textDelay.start();
		setVisible(true);
	}
	
	public void stage1()
	{
		text = "This is a story about me and the people like me.";
		
		textDelay.start();
	}
	
	public void stage2()
	{
		text = "When I was a boy, we couldn’t play electronic games. We would throw rocks for fun.";
		
		sprite s1 = new sprite(600, 50);
		s1.loadImage("images/stick.png");
		sprite s2 = new sprite(600, 200);
		s2.loadImage("images/stick.png");
		sprite s3 = new sprite(600, 350);
		s3.loadImage("images/stick.png");
		
		sprites.add(s1);
		sprites.add(s2);
		sprites.add(s3);
		bulletDelay.start();
		textDelay.start();
	}
	
	public void stage3()
	{
		text = "When I was a boy, we couldn’t play arcade games. Instead, I would fetch water near the river for my family.";
		
		sprite bucket = new sprite(25, 425);
		bucket.loadImage("images/fullbucket.png");
		holdables.add(bucket);
		sprite river = new sprite(0, 500);
		river.loadImage("images/river.png");
		sprites.add(river);
		
		textDelay.start();
	}
	
	public void stage4()
	{
		text = "When I was a boy, we couldn’t play cooking games with fancy ingredients. Rice was the only thing I ate.";
		
		sprite rice1 = new sprite(ran.nextInt(400) +25, ran.nextInt(400) +25);
		rice1.loadImage("images/rice.png");
		sprite rice2 = new sprite(ran.nextInt(400) +25, ran.nextInt(400) +25);
		rice2.loadImage("images/rice.png");
		sprite rice3 = new sprite(ran.nextInt(400) +25, ran.nextInt(400) +25);
		rice3.loadImage("images/rice.png");
		sprite rice4 = new sprite(ran.nextInt(400) +25, ran.nextInt(400) +25);
		rice4.loadImage("images/rice.png");
		sprite rice5 = new sprite(ran.nextInt(400) +25, ran.nextInt(400) +25);
		rice5.loadImage("images/rice.png");
		
		holdables.add(rice1);
		holdables.add(rice2);
		holdables.add(rice3);
		holdables.add(rice4);
		holdables.add(rice5);
		
		textDelay.start();
	}
	
	public void stage5()
	{
		text = "When I was a boy, we couldn’t play shooting games. Guns were always around me, and I didn’t want them to stay.";
		
		sprite shooter1 = new sprite(650, 300);
		shooter1.loadImage("images/soldier.png");
		sprite shooter2 = new sprite(650, 400);
		shooter2.loadImage("images/soldier.png");
		
		sprite wall1 = new sprite(400, 325);
		wall1.loadImage("images/wall.png");
		sprite wall2 = new sprite(400, 425);
		wall2.loadImage("images/wall.png");
		
		sprites.add(shooter1);
		sprites.add(shooter2);
		walls.add(wall1);
		walls.add(wall2);
		
		bulletDelay.start();
		textDelay.start();
	}
	
	public void stage6()
	{
		text = "When I was a boy, we couldn’t play racing games with expensive race cars. I raced away on tiny boats, leaving my family behind.";
		
		sprite boat = new sprite(50, 400);
		boat.loadImage("images/boat.png");
		walls.add(boat);
		
		textDelay.start();
	}
	
	public void stage7()
	{
		text = "When I was a boy, we couldn’t play adventure games. I had to discover on my own, how to live in a strange place.";
		
		sprite road = new sprite(0, 250);
		road.loadImage("images/road.png");
		sprite sign = new sprite(50, 100);
		sign.loadImage("images/sign.png");
		
		sprites.add(road);
		walls.add(sign);
		
		textDelay.start();
	}
	
	public void stage8()
	{
		text = "When I was a boy, we couldn’t play strategy games. Building a life plan from nothing was hard enough. ";
		
		sprite college = new sprite(150, 150);
		college.loadImage("images/college.png");
		sprite book1 = new sprite(ran.nextInt(400) +25, ran.nextInt(400) +25);
		book1.loadImage("images/book.png");
		sprite book2 = new sprite(ran.nextInt(400) +25, ran.nextInt(400) +25);
		book2.loadImage("images/book.png");
		sprite book3 = new sprite(ran.nextInt(400) +25, ran.nextInt(400) +25);
		book3.loadImage("images/book.png");
		
		sprites.add(college);
		holdables.add(book1);
		holdables.add(book2);
		holdables.add(book3);
		
		textDelay.start();
	}
	
	public void stage9()
	{
		text = "When I was a boy, we couldn’t play games about life. I was too busy living it.";
		
		sprite girl = new sprite(400, 400);
		girl.loadImage("images/girl.png");
		
		holdables.add(girl);
		
		textDelay.start();
	}
	
	public void stage10()
	{
		text = "When I was a boy, we couldn’t play games. We weren’t blessed with that opportunity.";
		
		sprite game1 = new sprite(650, 50);
		game1.loadImage("images/game.png");
		sprite game2 = new sprite(650, 150);
		game2.loadImage("images/game.png");
		sprite game3 = new sprite(650, 250);
		game3.loadImage("images/game.png");
		sprite game4 = new sprite(650, 350);
		game4.loadImage("images/game.png");
		sprite baby = new sprite(50, 50);
		baby.loadImage("images/baby.png");
		
		sprites.add(game1);
		sprites.add(game2);
		sprites.add(game3);
		sprites.add(game4);
		
		textDelay.start();
	}
	
	public void stage11()
	{
		text = "But when he was a boy, he could played as many games as he could.";
		
		sprite game1 = new sprite(650, 50);
		game1.loadImage("images/game.png");
		sprite game2 = new sprite(650, 150);
		game2.loadImage("images/game.png");
		sprite game3 = new sprite(650, 250);
		game3.loadImage("images/game.png");
		sprite game4 = new sprite(650, 350);
		game4.loadImage("images/game.png");
		sprite baby = new sprite(100, 100);
		baby.loadImage("images/baby.png");
		sprite girl = new sprite(50, 50);
		girl.loadImage("images/girl.png");
		
		holdables.add(game1);
		holdables.add(game2);
		holdables.add(game3);
		holdables.add(game4);
		
		sprites.add(baby);
		
		walls.add(girl);
		
		textDelay.start();
	}
	
	public void stage12()
	{
		text = "Games are a form of expression, that display freedom, creativity and love for one another. Thanks for playing my game.";
		
		sprite baby = new sprite(100, 100);
		baby.loadImage("images/baby.png");
		sprite girl = new sprite(50, 50);
		girl.loadImage("images/girl.png");
		
		sprites.add(baby);
		
		walls.add(girl);
		
		textDelay.start();
	}
	
	Timer textDelay = new Timer(40, new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			next.setEnabled(false);
			char[] character = text.toCharArray();
			String adding = "";
			
			adding += character[x];
			textArea.append(adding);
			x++;
			
			if(x == character.length)
			{
				x = 0;
				textDelay.stop();
				if(stage == 0 || stage == 1 || stage == 5 || stage == 10 || stage == 12)
				{
					star.setVisible(true);
				}
				if(stage == 11)
				{
					player.setFree(true);
					drawRect = false;
				}
			}
		}
	});
	
	Timer bulletDelay = new Timer(2000, new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if (stage == 2)
			{
				projectile j = new projectile(player.getX()+ player.getWidth()/2, player.getY()+player.getHeight()/2, true);
				j.loadImage("images/bullet.png");
				player.getBullets().add(j);
			}
			else if (stage == 5)
			{
				for(sprite s: sprites)
				{
					projectile j = new projectile(s.getX()-s.getWidth()/2, s.getY()+s.getHeight()/2, false);
					j.loadImage("images/bullet.png");
					player.getBullets().add(j);
				}
			}
		}
	});
	
	public void checkCol()
	{
		Rectangle p = player.getBounds();
		Rectangle st = star.getBounds();
		
		//player and star col
		if (counting < 100)
		{
			if(p.intersects(st) && star.isVisible())
				counting++;
		}
		
		//sprite and bullet col
		for(int x = 0; x < player.getBullets().size(); x++)
		{
			Rectangle b = player.getBullets().get(x).getBounds();
			//sprites
			for(int y = 0; y < sprites.size(); y++)
			{
				if(b.intersects(sprites.get(y).getBounds()))
				{
					player.getBullets().remove(x);
					sprites.remove(y);
				}
			}
			
			//walls
			for(int z = 0; z < walls.size(); z++)
			{
				if(b.intersects(walls.get(z).getBounds()))
				{
					player.getBullets().remove(x);
				}
			}
		}
		
		//boat shit
		if(stage == 6)
		{
			for(sprite boat: walls)
			{
				if(p.intersects(boat.getBounds()))
				{
					player.setX(boat.getX()+boat.getWidth()/4);
					player.setY(boat.getY()-player.getHeight()/2);
					boat.setX(boat.getX()+1);
					player.setMoveable(false);
				}
			}
		}
		
		//holdables
		for(int x = 0; x < holdables.size(); x++)
		{
			Rectangle h = holdables.get(x).getBounds();
			if(h.intersects(p))
			{
				if(stage == 9)
				{
					holdables.get(x).setX(player.getX()+player.getWidth()/2);
					holdables.get(x).setY(player.getY());
				}
				else
				{
					if (!holdables.get(x).getRemain())
						holdables.get(x).follow(player);
				}
				if(stage!=11)
					holdables.get(x).setCarry(true);
			}
		}
		
		//next button
		next.setText("Next " + counting + "/100");
		if(counting == 100)
		{
			next.setForeground(Color.green);
			next.setEnabled(true);
		}
	}
	
	public void checkStage()
	{
		if(stage == 2)
		{
			if (sprites.size() ==0)
			{
				star.setVisible(true);
			}
		}
		else if (stage ==3)
		{
			boolean setStar = true;
			for(sprite h: holdables)
			{
				if(!h.isCarrying())
					setStar = false;
			}
			star.setVisible(setStar);
		}
		else if (stage == 4)
		{
			boolean setStar = true;
			for(sprite h: holdables)
			{
				if(!h.isCarrying())
					setStar = false;
			}
			star.setVisible(setStar);
		}
		else if (stage == 6)
		{
			for(int x = 0; x < walls.size(); x++)
			{
				if(walls.get(x).getX() >= 1000)
				{
					walls.remove(x);
					star.setVisible(true);
					player.setX(50);
					player.setY(25);
					player.setMoveable(true);
				}
			}
		}
		else if (stage == 7)
		{
			Rectangle p = player.getBounds();
			for(sprite sign: walls)
			{
				if(p.intersects(sign.getBounds()))
				{
					star.setVisible(true);
				}
			}
		}
		else if (stage == 8)
		{
			boolean setCollege = true;
			for(sprite h: holdables)
			{
				if(!h.isCarrying())
					setCollege = false;
			}
			for(sprite s: sprites)
			{
				s.setVisible(setCollege);
				if(setCollege)
				{
					Rectangle p = player.getBounds();
					if(p.intersects(s.getBounds()))
					{
						star.setX(250);
						star.setY(200);
						star.setVisible(true);
					}
				}
			}
		}
		else if (stage == 9)
		{
			boolean setStar = true;
			for(sprite h: holdables)
			{
				if(!h.isCarrying())
					setStar = false;
			}
			star.setVisible(setStar);
		}
		else if (stage == 11)
		{
			for(sprite s: sprites)
			{
				Rectangle s1 = s.getBounds();
				for(sprite h: holdables)
				{
					boolean setStar = true;
					if(!h.isCarrying())
						setStar = false;
					if(s1.intersects(h.getBounds()))
					{
						h.setX(s.getX()+s.getWidth()/2);
						h.setY(s.getY()+s.getHeight()/2);
						h.setCarry(true);
						h.setRemain(true);
					}
					star.setVisible(setStar);
				}
			}
		}
	}
	
	class DrawPanel extends JPanel
	{
		public DrawPanel()
		{
			addKeyListener(new keyboard());
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			//draw boundary
			if(drawRect)
			{
				Graphics2D g2d = (Graphics2D) g;
				g2d.setStroke(new BasicStroke(3));
				g2d.drawRect(1, 20, 575, 475);
			}
			
			//star
			if(star.isVisible())
				g.drawImage(star.getImage(), star.getX(), star.getY(), this);
			
			//holdables
			for(sprite h: holdables)
			{
				if(h.isVisible())
					g.drawImage(h.getImage(), h.getX(), h.getY(), this);
			}
			
			//player
			if(player.isVisible())
				g.drawImage(player.getImage(), player.getX(), player.getY(), this);
			
			//sprites
			for(sprite s: sprites)
			{
				if(s.isVisible())
					g.drawImage(s.getImage(), s.getX(), s.getY(), this);
			}
			
			//projectiles
			for(projectile p: player.getBullets())
			{
				if(p.isVisible())
					g.drawImage(p.getImage(), p.getX(), p.getY(), this);
			}
			
			//walls
			for(sprite w: walls)
			{
				if(w.isVisible())
					g.drawImage(w.getImage(), w.getX(), w.getY(), this);
			}
		}
	}
	
	Timer level = new Timer(3, new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
			checkCol();
			checkStage();
			player.move();
			
			//boat movement
			if(stage == 6)
			{
				for(sprite w: walls)
				{
					w.move();
				}
			}
			
			//projectile movement
			for(projectile p: player.getBullets())
			{
				p.move();
			}
			dp.repaint();
		}
	});
	
	class keyboard implements KeyListener
	{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if (key == KeyEvent.VK_UP)
			{
				player.setChangeY(-1);
			}
			
			if (key == KeyEvent.VK_DOWN)
			{
				player.setChangeY(1);
			}
			
			if (key == KeyEvent.VK_LEFT)
			{
				player.setChangeX(-1);
			}
			
			if (key == KeyEvent.VK_RIGHT)
			{
				player.setChangeX(1);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_UP)
			{
				player.setChangeY(0);
			}
			
			if (key == KeyEvent.VK_DOWN)
			{
				player.setChangeY(0);
			}
			
			if (key == KeyEvent.VK_LEFT)
			{
				player.setChangeX(0);
			}
			
			if (key == KeyEvent.VK_RIGHT)
			{
				player.setChangeX(0);
			}
		}
	}
	
	class listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			sprites.removeAll(sprites);
			holdables.removeAll(holdables);
			walls.removeAll(walls);
			
			textArea.setText("");
			next.setEnabled(false);
			star.setVisible(false);
			stage++;
			bulletDelay.stop();
			star.setX(ran.nextInt(475) +25);
			star.setY(ran.nextInt(400) +25);
			
			counting = 0;
			next.setText("Next " + counting + "/100");
			next.setForeground(Color.black);
			
			if(stage == 1)
				stage1();
			else if(stage == 2)
				stage2();
			else if(stage == 3)
				stage3();
			else if(stage == 4)
				stage4();
			else if(stage == 5)
				stage5();
			else if(stage == 6)
				stage6();
			else if(stage == 7)
				stage7();
			else if(stage == 8)
				stage8();
			else if(stage == 9)
				stage9();
			else if(stage == 10)
				stage10();
			else if(stage == 11)
				stage11();
			else if(stage == 12)
				stage12();
			
			if(stage == 13)
			{
				System.exit(1);
			}
		}
	}
}
