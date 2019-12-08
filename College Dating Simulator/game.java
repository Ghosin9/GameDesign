

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import java.util.Random;

public class game {
	
	/*
	 * things to add:
	 * attack animations(just one frame)
	 */
	
	private JFrame frame;
	private JPanel textPanel, enemyPanel, playerPanel, buttonPanel;
	private JButton a1, a2, a3, a4, next;
	private JLabel enemyLabel, enemyName, playerLabel, playerName;
	private JTextArea text;
	
	private player myPlayer;
	private enemy enemy1;
	private JTextArea enemyHP;
	
	private Random rnd;
	
	int step=0, enemys = 1, effective = 0;
	
	public static void main(String []args)
	{
		game g1 = new game();
		g1.enemy1 = new enemy("ABG Girl", 10);
		g1.createMenu();
		g1.createWindow();
	}
	
	public void createMenu()
	{
		rnd = new Random();
		String name = null;
		while (name == null)
		{
			name = JOptionPane.showInputDialog(null, "Enter your name", "Welcome to College Dating Simulator", JOptionPane.DEFAULT_OPTION);
		}
		myPlayer = new player(name, 10);
	}

	public void createWindow()
	{
		//frame
		frame = new JFrame("College Dating Simulator");
		frame.setSize(800, 800);
		frame.setLayout(new GridLayout(2, 2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//panels
		enemyPanel = new JPanel();
		enemyPanel.setLayout(new BorderLayout());
		textPanel = new JPanel();
		textPanel.setLayout(new GridLayout(2,0));
		playerPanel = new JPanel();
		playerPanel.setLayout(new BorderLayout());
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,2));
		
		//labels
		text = new JTextArea("Press Next");
		text.setSize(325,325);
		text.setLineWrap(true);
		text.setEditable(false);
		enemyName = new JLabel(enemy1.getName() + "     Love Meter: " + enemy1.getHP() + "/10");
		enemyName.setHorizontalAlignment(0);
		enemyLabel = new JLabel(enemy1.getImage());
		playerName = new JLabel(myPlayer.getName());
		playerName.setHorizontalAlignment(0);
		playerLabel = new JLabel(myPlayer.getImage());
		
		//buttons
		a1 = new JButton(myPlayer.getAttacks().get(0).getName() + "\n" + myPlayer.getAttacks().get(0).getPP() + "/5");
		a2 = new JButton(myPlayer.getAttacks().get(1).getName() + "\n" + myPlayer.getAttacks().get(1).getPP() + "/5");
		a3 = new JButton(myPlayer.getAttacks().get(2).getName() + "\n" + myPlayer.getAttacks().get(2).getPP() + "/5");
		a4 = new JButton(myPlayer.getAttacks().get(3).getName() + "\n" + myPlayer.getAttacks().get(3).getPP() + "/5");
		next = new JButton("Next");
		a1.addActionListener(new Listener());
		a2.addActionListener(new Listener());
		a3.addActionListener(new Listener());
		a4.addActionListener(new Listener());
		next.addActionListener(new Listener());
		a1.setEnabled(false);
		a2.setEnabled(false);
		a3.setEnabled(false);
		a4.setEnabled(false);
		
		//hp bars
		enemyHP = new JTextArea();
		enemyHP.setSize(50, 200);
		enemyHP.setEditable(false);
		enemyHP.setLineWrap(true);
		enemyHP.setBackground(Color.RED);
		
		//panel adding
		textPanel.add(text);
		textPanel.add(next);
		enemyPanel.add(enemyName, BorderLayout.PAGE_START);
		enemyPanel.add(enemyLabel, BorderLayout.CENTER);
		playerPanel.add(playerName, BorderLayout.PAGE_START);
		playerPanel.add(playerLabel, BorderLayout.CENTER);
		buttonPanel.add(a1);
		buttonPanel.add(a2);
		buttonPanel.add(a3);
		buttonPanel.add(a4);
		
		//frame adding
		frame.add(textPanel);
		frame.add(enemyPanel);
		frame.add(playerPanel);
		frame.add(buttonPanel);
		
		frame.setVisible(true);
	}
	
	class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource().equals(next))
			{
				nextButton(step);
				if (step ==8)
					step =5;
				else
					step++;
			}
			else if(e.getSource().equals(a1))
			{
				text.setText("You used " + myPlayer.getAttacks().get(0).getName() + "!");
				playerLabel.setIcon(myPlayer.setAttackImage("images/boba.png"));
				effective = myPlayer.getAttacks().get(0).attackEnemy(enemy1);
				if (effective == 0)
					step++;
				a1.setText(myPlayer.getAttacks().get(0).getName() + "\n" + myPlayer.getAttacks().get(0).getPP() + "/5");
				next.setEnabled(true);
				a1.setEnabled(false);
				a2.setEnabled(false);
				a3.setEnabled(false);
				a4.setEnabled(false);
			}
			else if(e.getSource().equals(a2))
			{
				text.setText("You used " + myPlayer.getAttacks().get(1).getName() + "!");
				playerLabel.setIcon(myPlayer.setAttackImage("images/extra.png"));
				effective = myPlayer.getAttacks().get(1).attackEnemy(enemy1);
				if(effective == 0)
					step++;
				a2.setText(myPlayer.getAttacks().get(1).getName() + "\n" + myPlayer.getAttacks().get(1).getPP() + "/5");
				next.setEnabled(true);
				a1.setEnabled(false);
				a2.setEnabled(false);
				a3.setEnabled(false);
				a4.setEnabled(false);
			}
			else if (e.getSource().equals(a3))
			{
				text.setText("You used " + myPlayer.getAttacks().get(2).getName() + "!");
				playerLabel.setIcon(myPlayer.setAttackImage("images/gatorade.png"));
				effective = myPlayer.getAttacks().get(2).attackEnemy(enemy1);
				if (effective == 0)
					step++;
				a3.setText(myPlayer.getAttacks().get(2).getName() + "\n" + myPlayer.getAttacks().get(2).getPP() + "/5");
				next.setEnabled(true);
				a1.setEnabled(false);
				a2.setEnabled(false);
				a3.setEnabled(false);
				a4.setEnabled(false);
			}
			else if (e.getSource().equals(a4))
			{
				text.setText("You used " + myPlayer.getAttacks().get(3).getName() + "!");
				playerLabel.setIcon(myPlayer.setAttackImage("images/anime.png"));
				effective = myPlayer.getAttacks().get(3).attackEnemy(enemy1);
				if (effective == 0)
					step++;
				a4.setText(myPlayer.getAttacks().get(3).getName() + "\n" + myPlayer.getAttacks().get(3).getPP() + "/5");
				next.setEnabled(true);
				a1.setEnabled(false);
				a2.setEnabled(false);
				a3.setEnabled(false);
				a4.setEnabled(false);
			}
			enemyName.setText(enemy1.getName() + "     Love Meter: " + enemy1.getHP() + "/" + enemy1.getMaxHP());
			
			enemyHP.setSize(50, enemy1.getHP()*20);
		}
		
		private void nextButton(int count)
		{
			if(count ==0)
				text.setText("Welcome to College Dating Simulator! Press the Next Button to move the text along!");
			else if (count ==1)
				text.setText("Your goal is to date everyone! You must use different moves to increase their love meter!");
			else if (count == 2)
				text.setText("Once their love meter passes 10 you win, but if you run out of moves you lose!");
			else if (count == 3)
			{
				text.setText("Some moves are more effective than others... \nBut Beware fellow dater \nIf you die in the game, then you die in real life");
				new sound();
			}
			else if (count == 4)
				text.setText("You are approached by " + enemy1.getName());
			else if (count ==5)
			{
				text.setText("It's your turn");
				enemyLabel.setIcon(enemy1.getImage());
				next.setEnabled(false);
				if (myPlayer.getAttacks().get(0).getPP() > 0)
					a1.setEnabled(true);
				if (myPlayer.getAttacks().get(1).getPP() > 0)
					a2.setEnabled(true);
				if (myPlayer.getAttacks().get(2).getPP() > 0)
					a3.setEnabled(true);
				if (myPlayer.getAttacks().get(3).getPP() > 0)
					a4.setEnabled(true);
			}
			else if (count ==6)
			{
				if (effective == 1)
					text.setText("It was super effective, dealing double damage!");
				else if (effective == -1)
					text.setText("It's not very effective, dealing half damage!");
			}
			else if (count ==7)
			{
				playerLabel.setIcon(myPlayer.getImage());
				text.setText("Its the enemy's turn");
			}
			else if (count ==8)
			{
				int r = rnd.nextInt(enemy1.getAttacks().size());
				text.setText(enemy1.getName() + " used " + enemy1.getAttacks().get(r).getName() + "!\nThis reduced their love meter by " + enemy1.getAttacks().get(r).getDmg() + "!");
				enemyLabel.setIcon(enemy1.setAttackImage(r));
				enemy1.takeDmg(-enemy1.getAttacks().get(r).getDmg());
			}
			
			if (count <10)
				checkWin(enemys);
			
			if (count == 10)
			{
				text.setText("You have defeated " + enemy1.getName() + "!");
				playerLabel.setIcon(myPlayer.getImage());
				enemyLabel.setIcon(enemy1.getImage());
			}
			else if (count ==11)
			{
				step =3;
				if (enemys == 2)
				{
					enemy1 = new enemy("Weeaboo Guy", 10);
					
				}
				else if (enemys == 3)
				{
					enemy1 = new enemy("12A Student", 10);
				}
				else if (enemys == 4)
				{
					enemy1 = new enemy("Football Player", 10);
				}
				else if (enemys == 5)
				{
					enemy1 = new enemy("Professor Narges Norouzi", 20);
				}
				enemyName.setText(enemy1.getName() + "     Love Meter: " + enemy1.getHP() + "/10");
				enemyLabel.setIcon(enemy1.getImage());
			}
			
			if (count == 15)
			{
				text.setText("please give ly phung an A");
				next.setEnabled(false);
			}
			
			if (count == 20)
			{
				text.setText("You have ran out of moves!");
			}
			else if (count == 21)
			{
				text.setText("You lose!");
				step= 14;
			}
		}
		
		private void checkWin(int numEnemys)
		{
			int sum = 0;
			for(attack a:myPlayer.getAttacks())
			{
				sum += a.getPP();
			}
			if (sum ==0)
			{
				step = 19;
			}
			
			if (enemy1.getHP() >= enemy1.getMaxHP())
			{
				step = 9;
				enemys++;
			}
			
			if (numEnemys >= 6)
			{
				step = 14;
				enemyLabel.setIcon(null);
				enemyName.setText("");
				text.setText("You have made everyone fall in love with you! Congratulations!!!!");
			}
		}
	}
	
	class sound
	{
		sound() 
		{
			AudioInputStream music;
			try 
			{
				URL url = this.getClass().getResource("images/music.wav");
				music = AudioSystem.getAudioInputStream(url);
				Clip clip = AudioSystem.getClip();
				clip.open(music);
				clip.start();
			}
			catch (Exception ex)
			{
				JOptionPane.showMessageDialog(null, "ERROR");
			}
		}
	}
}
