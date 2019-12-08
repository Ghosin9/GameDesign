import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.*;
import java.net.URL;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Area;

public class Board extends JPanel implements ActionListener
{
    private Player player1;
    private Player2 player2;
    private Timer timer;
    //array lists for players, bullets, background and the walls
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> bullets2;
    private ArrayList<wall> walls;
    //lingering effect
    private ArrayList<Sprite> linger;
    private Image background, scoreboard, victory, Redx, p1wins, p2wins, win1, win2, win3;
    //shield
    private shield shield;
    //light aoe
    private float alpha = 1f;
    private final int LIGHT = 150;
    //delay for timer
    private final int DELAY = 10;
    //for ending animation
    private int endX, endY;
    //ingame + flash
    private boolean ingame, flasher;
    private JFrame frame;

    public Board(int x, JFrame myframe)
    {
        //load background
        frame = myframe;
        URL imageURL = this.getClass().getResource("images/floor.png");
        ImageIcon ii = new ImageIcon(imageURL);
        background = ii.getImage();

        imageURL = this.getClass().getResource("images/X.png");
        ii = new ImageIcon(imageURL);
        Redx = ii.getImage();

        imageURL = this.getClass().getResource("images/scoreboard.png");
        ii = new ImageIcon(imageURL);
        scoreboard = ii.getImage();

        imageURL = this.getClass().getResource("images/0.png");
        ii = new ImageIcon(imageURL);
        p1wins = ii.getImage();
        p2wins = p1wins;

        imageURL = this.getClass().getResource("images/1.png");
        ii = new ImageIcon(imageURL);
        win1 = ii.getImage();

        imageURL = this.getClass().getResource("images/2.png");
        ii = new ImageIcon(imageURL);
        win2 = ii.getImage();

        imageURL = this.getClass().getResource("images/3.png");
        ii = new ImageIcon(imageURL);
        win3 = ii.getImage();

        endX = 0;
        endY = 0;
        addKeyListener(new KeyHandle());

        //initialize map
        initMap(x);
        flasher = true;
        timer = new Timer(DELAY, this);
        timer.start();
        ingame = true;

        linger = new ArrayList<Sprite>();

        SoundEffect background = SoundEffect.UNDERTALE;
        background.play(true);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;

        final Rectangle back = new Rectangle(0, 0, getWidth(), getHeight());
        Area a1 = new Area(back);

        //background
        g.drawImage(background, 0, 0, this);

        //spawn walls
        for(wall w: walls)
        {
            g.drawImage(w.getImage(), w.getX(), w.getY(), this);
        }

        //spawn players
        if (player1.isVisible())
        {
            g.drawImage(player1.getImage(), player1.getX(), player1.getY(), this);
        }

        if (player2.isVisible())
        {
            g.drawImage(player2.getImage(), player2.getX(), player2.getY(), this);
        }

        //spawn shield
        if (shield.isVisible())
        {
            g.drawImage(shield.getImage(), shield.getX(), shield.getY(), this);
        }

        //lingering effect
        //for(Sprite s: linger)
        //{
        //Ellipse2D e1 = new Ellipse2D.Double(s.getX()-LIGHT/2, s.getY()-LIGHT/2, LIGHT, LIGHT);
        //Area a2 = new Area(e1);
        //a1.subtract(a2);
        //}

        g.setColor(new Color(0,0,0,255));
        //bullets
        bullets = player1.getBullets();
        for (Bullet b: bullets)
        {
            g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            if (!flasher)
            {
                Ellipse2D e1 = new Ellipse2D.Double(b.getX()-LIGHT/2, b.getY()-LIGHT/2, LIGHT, LIGHT);
                g2D.setPaint(new Color(0, 0, 0, 0));
                g2D.fillOval(b.getX()-LIGHT/2, b.getY()-LIGHT/2, LIGHT, LIGHT);
                Area a2 = new Area(e1);
                a1.subtract(a2);
                g2D.setColor(new Color(0,0,0,255));
            }
        }

        bullets2 = player2.getBullets();
        for (Bullet b: bullets2)
        {
            g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            if (!flasher)
            {
                Ellipse2D e1 = new Ellipse2D.Double(b.getX()-LIGHT/2, b.getY()-LIGHT/2, LIGHT, LIGHT);
                g2D.setPaint(new Color(0, 0, 0, 0));
                g2D.fillOval(b.getX()-LIGHT/2, b.getY()-LIGHT/2, LIGHT, LIGHT);
                Area a2 = new Area(e1);
                a1.subtract(a2);
                g2D.setColor(new Color(0,0,0,255));
            }
        }

        if (flasher)
        {
            flash(g2D);
        }
        else
        {
            if (ingame)
            {
                if (player1.getBullets().size() == 0 && player2.getBullets().size() == 0)
                {
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                g2D.fill(a1);
            }
        }

        if (!ingame)
        {
            alpha += 0.005f;
            if (alpha >= 1)
            {
                alpha = 1;
                timer.stop();
                reset();
            }
            else
            {
                if (main.p1 >= 3 || main.p2 >= 3)
                {
                    alpha -= 0.003f;
                    g.drawImage(Redx, endX, endY, null);
                    g.drawImage(victory, 100, 100, null);
                    if (alpha >= 0.9f)
                    {
                        timer.stop();
                        backToMenu();
                    }
                }
                else
                {
                    g.drawImage(Redx, endX, endY, null);
                    Paint paint = new RadialGradientPaint(endX, endY, 1500, new float[] {0, alpha}, 
                            new Color[] {new Color(0, 0, 0, 0), new Color(0, 0, 0, 255)});
                    g2D.setPaint(paint);
                    g2D.fillRect(0, 0, getWidth(), getHeight());
                    g.drawImage(scoreboard, 30, 770, null);
                    g.drawImage(p1wins, 305, 800, null);
                    g.drawImage(p2wins, 525, 800, null);
                }
            }
        }
    }

    public void flash(Graphics2D g2d)
    {
        alpha += -0.005f;
        if (alpha <= 0)
        {
            alpha = 0;
            flasher = false;
        }
        else
        {
            Paint paint = new RadialGradientPaint(400, 400, 1500, new float[] {0, alpha}, 
                    new Color[] {new Color(0, 0, 0, 0), new Color(0, 0, 0, 255)});
            g2d.setPaint(paint);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    public void initMap(int ran)
    {
        walls = new ArrayList<wall>();
        //outside of map
        for(int r = 0; r < 800; r+= 100)
        {
            wall w1 = new wall(r + 10, 0);
            walls.add(w1);
        }

        for(int r = 0; r < 800; r+= 100)
        {
            wall w1 = new wall(r + 10, 810);
            walls.add(w1);
        }

        for (int c = 0; c < 800; c += 100)
        {
            wall w1 = new wall(810, c + 10, 0);
            walls.add(w1);
        }

        for (int c = 0; c < 800; c += 100)
        {
            wall w1 = new wall(0, c + 10, 0);
            walls.add(w1);
        }

        //shield in center
        shield = new shield(350, 350);

        if (ran == 0)
        {
            initMap1();
            player2 = new Player2(758, 750);
            player1 = new Player(20, 20);
        }
        else if (ran == 1)
        {
            initMap2();
            player2 = new Player2(658, 750);
            player1 = new Player(120, 250);
        }
        else if (ran == 2)
        {
            initMap3();
            player2 = new Player2(758, 750);
            player1 = new Player(20, 20);
        }
        else if (ran == 3)
        {
            initMap4();
            player2 = new Player2(758, 750);
            player1 = new Player(20, 20);
        }
        else if (ran == 4)
        {
            initMap5();
            player2 = new Player2(750, 20);
            player1 = new Player(70, 740);
        }
        else
        {
            player2 = new Player2(600, 600);
            player1 = new Player(25, 25);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        checkCol();
        player1.move();
        player2.move();
        updateBullets();
        repaint();
    }

    private void updateBullets()
    {
        bullets = player1.getBullets();
        bullets2 = player2.getBullets();
        changeBullet(bullets);
        changeBullet(bullets2);
    }

    private void checkCol()
    {
        //shield
        Rectangle s = shield.getBounds();
        Rectangle r1 = player1.getBounds();
        Rectangle r2 = player2.getBounds();
        if (r1.intersects(s))
        {
            if (!shield.getUse())
            {
                player1.setShield(true);
                shield.setUse(true);
            }
            else if (!player2.isShield())
            {
                shield.follow(player1);
            }
        }

        if(r2.intersects(s))
        {
            if (!shield.getUse())
            {
                player2.setShield(true);
                shield.setUse(true);
            }
            else if (!player1.isShield())
            {
                shield.follow(player2);
            }
        }

        //player 1 bullets
        bullets = player1.getBullets();
        for(Bullet b: bullets)
        {
            r1 = b.getBounds();
            if (player2.isShield())
            {
                r2 = shield.getBounds();
            }
            else
            {
                r2 = player2.getBounds();
            }

            if (r1.intersects(r2))
            {
                if (player2.isShield() && !shield.getUsed())
                {
                    shield.setVisible(false);
                    shield.setUsed(true);
                    player2.setShield(false);
                    b.setVisible(false);
                }
                else
                {
                    player2.setVisible(false);
                    b.setVisible(false);
                    main.p1++;
                    roundOver(b);
                }
            }

            for (wall w: walls)
            {
                Rectangle r3 = w.getBounds();
                if (r1.intersects(r3))
                {
                	b.setVisible(false);
                }
            }
        }

        //player 2 bullets
        bullets2 = player2.getBullets();
        for(Bullet b: bullets2)
        {
            r1 = b.getBounds();
            if (player1.isShield())
            {
                r2 = shield.getBounds();
            }
            else
            {
                r2 = player1.getBounds();
            }

            if (r1.intersects(r2))
            {
                if (player1.isShield() && !shield.getUsed())
                {
                    shield.setVisible(false);
                    shield.setUsed(true);
                    player1.setShield(false);
                    b.setVisible(false);
                }
                else
                {
                    player1.setVisible(false);
                    b.setVisible(false);
                    main.p2++;
                    roundOver(b);
                }
            }

            for (wall w: walls)
            {
                Rectangle r3 = w.getBounds();
                if (r1.intersects(r3))
                {
                	b.setVisible(false);
                }
            }
        }

        //player movement

        for(wall w: walls)
        {
            Rectangle r3 = w.getBounds();
            r1 = player1.getBounds();
            r2 = player2.getBounds();

            if (r1.intersects(r3))
            {
                player1.setCollide(true, w);
            }

            if (r2.intersects(r3))
            {
                player2.setCollide(true, w);
            }
        }
    }

    public void changeBullet(ArrayList<Bullet> bullets)
    {
        for (int i = 0; i < bullets.size(); i++)
        {
            if (bullets.get(i).isVisible())
            {
                bullets.get(i).move();
            }
            else
            {
                bullets.remove(i);
                i--;
            }
        }
    }

    public void roundOver(Bullet b)
    {
        SoundEffect oof = SoundEffect.OOF;
        oof.play(false);
        if (main.p1 > 3 || main.p2 > 3)
        {
            return;
        }
        ingame = false;
        endX = b.getX();
        endY = b.getY();
        if (main.p1 == 1)
        {
            p1wins = win1;
        }
        else if (main.p1 == 2)
        {
            p1wins = win2;
        }
        else if (main.p1 == 3)
        {
            p1wins = win3;
            URL imageURL = this.getClass().getResource("images/player1_victory.png");
            ImageIcon ii = new ImageIcon(imageURL);
            victory = ii.getImage();
        }

        if (main.p2 == 1)
        {
            p2wins = win1;
        }
        else if (main.p2 == 2)
        {
            p2wins = win2;
        }
        else if (main.p2 == 3)
        {
            p2wins = win3;
            URL imageURL = this.getClass().getResource("images/player2_victory.png");
            ImageIcon ii = new ImageIcon(imageURL);
            victory = ii.getImage();
        }
    }

    public void initMap1()
    {
        wall middle0 = new wall(210, 210,0);
        wall middle1 = new wall(210, 310,0);
        wall middle2 = new wall(210, 410,0);
        wall middle3 = new wall(210, 510,0);
        wall middle4 = new wall(210, 210);
        wall middle5 = new wall(310, 210);
        wall middle6 = new wall(410, 210);
        wall middle7 = new wall(600, 210, 0); 
        wall middle8 = new wall(600, 310, 0); 
        wall middle9 = new wall(600, 410, 0); 
        wall middle10 = new wall(600, 510, 0); 
        wall middle11 = new wall(310, 600);
        wall middle12 = new wall(410, 600);
        wall middle13 = new wall(510, 600);
        wall wall1 = new wall(10,210);
        wall wall2 = new wall(110,110,0);
        wall wall3 = new wall(110,310,0);
        wall wall4 = new wall(110,410);
        wall wall5 = new wall(210, 110, 0);
        wall wall6 = new wall(410,110,0);
        wall wall7 = new wall(410,10,0);
        wall wall8 = new wall(500,110,0);
        wall wall9 = new wall(510,110);
        wall wall10 = new wall(610,110);
        wall wall11 = new wall(700,110,0);
        wall wall12 = new wall(610,310);
        wall wall13 = new wall(610,400);
        wall wall14 = new wall(700,410,0);
        wall wall15 = new wall(710,600);
        wall wall16 = new wall(700,610,0);
        wall wall17 = new wall(600,610,0);
        wall wall18 = new wall(400,610,0);
        wall wall19 = new wall(400,710,0);
        wall wall20 = new wall(310,610,0);
        wall wall21 = new wall(110,700);
        wall wall22 = new wall(210,700);
        wall wall23 = new wall(110,610,0);
        wall wall24 = new wall(110,500);
        wall wall25 = new wall(410,705);
        wall wall26 = new wall(310,105);

        walls.add(middle0);
        walls.add(middle1);
        walls.add(middle2);
        walls.add(middle3);
        walls.add(middle4);
        walls.add(middle5);
        walls.add(middle6);
        walls.add(middle7);
        walls.add(middle8);
        walls.add(middle9);
        walls.add(middle10);
        walls.add(middle11);
        walls.add(middle12);
        walls.add(middle13);

        walls.add(wall1);
        walls.add(wall2);
        walls.add(wall3);
        walls.add(wall4);
        walls.add(wall5);
        walls.add(wall6);
        walls.add(wall7);
        walls.add(wall8);
        walls.add(wall9);
        walls.add(wall10);
        walls.add(wall11);
        walls.add(wall12);
        walls.add(wall13);
        walls.add(wall14);
        walls.add(wall15);
        walls.add(wall16);
        walls.add(wall17);
        walls.add(wall18);
        walls.add(wall19);
        walls.add(wall20);
        walls.add(wall21);
        walls.add(wall22);
        walls.add(wall23);
        walls.add(wall24);
        walls.add(wall25);
        walls.add(wall26);

        wall w1 = new wall(710, 310, "s");
        wall w2 = new wall(605,95,false);
        wall w3 = new wall(95,500,"s");
        wall w4 = new wall(205,710,false);
        walls.add(w1);
        walls.add(w2);
        walls.add(w3);
        walls.add(w4);
    }

    public void initMap2()
    {
        //middle
        wall w1 = new wall(210,10,0);
        wall w2 = new wall(210,110,0);
        wall w3 = new wall(210,210,0);
        wall w4 = new wall(210,310,0);
        wall w5 = new wall(210,410,0);
        wall w6 = new wall(210,510,0);
        wall w7 = new wall(210,610,0);
        wall w8 = new wall(600,110,0);
        wall w9 = new wall(600,210,0);
        wall w10 = new wall(600,310,0);
        wall w11 = new wall(600,410,0);
        wall w12 = new wall(600,510,0);
        wall w13 = new wall(600,610,0);
        wall w14 = new wall(600,710,0);
        wall w15 = new wall(210,600);
        wall w16 = new wall(310,600);
        wall w17 = new wall(410,600,"s ");
        wall w18 = new wall(510,600);
        wall w19 = new wall(210,210);
        wall w20 = new wall(385,210);
        wall w21 = new wall(410,210);
        wall w22 = new wall(510,210);
        //left side
        wall w23 = new wall(110,110);
        wall w24 = new wall(10,210);
        wall w25 = new wall(100,195,false);
        wall w26 = new wall(10,310,"s");
        wall w27 = new wall(110,310,0);
        wall w28 = new wall(110,410);
        wall w29 = new wall(10,510);
        wall w30 = new wall(110,510,"s");
        wall w31 = new wall(110,710);
        wall w32 = new wall(110,610);
        wall w33 = new wall(310,710,0);
        wall w34 = new wall(510,610,0);
        wall w36 = new wall(410,700);
        //right side
        wall w38 = new wall(300,110,0);
        wall w39 = new wall(500,10,0);
        wall w40 = new wall(500,110,false);
        wall w41 = new wall(610,100);
        wall w42 = new wall(510,210);
        wall w43 = new wall(710,300);
        wall w44 = new wall(695,300,"s");
        wall w45 = new wall(610,400);
        wall w46 = new wall(700,410,0);
        wall w47 = new wall(795,500,"s");
        wall w48 = new wall(710,600);
        wall w49 = new wall(710,610,false);
        wall w50 = new wall(610,700);
        wall w51 = new wall(610,200);
        wall w52 = new wall(310,110);

        walls.add(w1);
        walls.add(w2);
        walls.add(w3);
        walls.add(w4);
        walls.add(w5);
        walls.add(w6);
        walls.add(w7);
        walls.add(w8);
        walls.add(w9);
        walls.add(w10);
        walls.add(w11);
        walls.add(w12);
        walls.add(w13);
        walls.add(w14);
        walls.add(w15);
        walls.add(w16);
        walls.add(w17);
        walls.add(w18);
        walls.add(w19);
        walls.add(w20);
        walls.add(w21);
        walls.add(w22);
        walls.add(w23);
        walls.add(w24);
        walls.add(w25);
        walls.add(w26);
        walls.add(w27);
        walls.add(w28);
        walls.add(w29);
        walls.add(w30);
        walls.add(w31);
        walls.add(w32);
        walls.add(w33);
        walls.add(w34);
        walls.add(w36);
        walls.add(w38);
        walls.add(w39);
        walls.add(w40);
        walls.add(w41);
        walls.add(w42);
        walls.add(w43);
        walls.add(w44);
        walls.add(w45);
        walls.add(w46);
        walls.add(w47);
        walls.add(w48);
        walls.add(w49);
        walls.add(w50);
        walls.add(w51);
        walls.add(w52);
    }

    public void initMap3()
    {
        //middle
        wall w1 = new wall(210,10,0);
        wall w2 = new wall(210,110,0);
        wall w3 = new wall(210,210,0);
        wall w4 = new wall(210,310,0);
        wall w5 = new wall(210,410,0);
        wall w6 = new wall(210,510,0);
        wall w7 = new wall(210,610,0);
        wall w8 = new wall(600,110,0);
        wall w9 = new wall(600,210,0);
        wall w10 = new wall(600,310,0);
        wall w11 = new wall(600,410,0);
        wall w12 = new wall(600,510,0);
        wall w13 = new wall(600,610,0);
        wall w14 = new wall(600,710,0);
        wall w15 = new wall(210,600);
        wall w16 = new wall(300,510,0);
        wall w17 = new wall(510,600);
        wall w18 = new wall(310,510);
        wall w19 = new wall(410,510);
        wall w20 = new wall(405,510,0);
        wall w22 = new wall(210,210);
        wall w23 = new wall(310,300);
        wall w24 = new wall(410,300);
        wall w25 = new wall(510,210);
        wall w26 = new wall(510,210,0);
        wall w27 = new wall(405,210,0);
        wall w28 = new wall(305,10,0);
        wall w29 = new wall(405,10,0);
        wall w30 = new wall(505,10,0);
        //left
        wall l1 = new wall(110,10,0);
        wall l2 = new wall(110,210,0);
        wall l3 = new wall(110,310);
        wall l4 = new wall(10,410);
        wall l6 = new wall(110,510);
        wall l8 = new wall(10,610);
        wall l9 = new wall(110,710);
        wall l12 = new wall(305,710,0);
        wall l13 = new wall(405,710,0);
        wall l14 = new wall(505,710,0);
        //right
        wall r1 = new wall(700,710,0);
        wall r2 = new wall(700,510,0);
        wall r3 = new wall(610,510);
        wall r4 = new wall(710,400);
        wall r5 = new wall(610,300);
        wall r6 = new wall(710,200);
        wall r7 = new wall(610,100);

        walls.add(w1);
        walls.add(w2);
        walls.add(w3);
        walls.add(w4);
        walls.add(w5);
        walls.add(w6);
        walls.add(w7);
        walls.add(w8);
        walls.add(w9);
        walls.add(w10);
        walls.add(w11);
        walls.add(w12);
        walls.add(w13);
        walls.add(w14);
        walls.add(w15);
        walls.add(w16);
        walls.add(w17);
        walls.add(w18);
        walls.add(w19);
        walls.add(w20);
        walls.add(w22);
        walls.add(w23);
        walls.add(w24);
        walls.add(w25);
        walls.add(w26);
        walls.add(w27);
        walls.add(w28);
        walls.add(w29);
        walls.add(w30);

        walls.add(l1);
        walls.add(l2);
        walls.add(l3);
        walls.add(l4);
        //walls.add(l5);
        walls.add(l6);
        //walls.add(l7);
        walls.add(l8);
        walls.add(l9);
        //walls.add(l10);
        //walls.add(l11);
        walls.add(l12);
        walls.add(l13);
        walls.add(l14);
        //walls.add(l15);
        //walls.add(l16);
        //walls.add(l17);
        //walls.add(l18);

        walls.add(r1);
        walls.add(r2);
        walls.add(r3);
        walls.add(r4);
        walls.add(r5);
        walls.add(r6);
        walls.add(r7);
    }

    public void initMap4()
    {
        //middle
        wall w1 = new wall(210,210,0);
        wall w2 = new wall(210,310,0);
        wall w3 = new wall(210,410,0);
        wall w4 = new wall(210,510,0);
        wall w5 = new wall(600,210,0);
        wall w6 = new wall(600,310,0);
        wall w7 = new wall(600,410,0);
        wall w8 = new wall(600,510,0);
        wall w9 = new wall(210,210);
        wall w10 = new wall(310,210);
        wall w11 = new wall(410,210);
        wall w12 = new wall(310,600);
        wall w13 = new wall(410,600);
        wall w14 = new wall(510,600);
        wall w15 = new wall(405,10,0);
        wall w16 = new wall(405,110,0);
        wall w17 = new wall(405,610,0);
        wall w18 = new wall(405,710,0);

        //left
        wall l1 = new wall(210,10,0);
        wall l2 = new wall(305,110,0);
        wall l3 = new wall(110,110,0);
        wall l4 = new wall(120,200,"s");
        wall l5 = new wall(135,200,"s");
        wall l6 = new wall(95,200,"s");
        wall l7 = new wall(80,200,"s");
        wall l8 = new wall(10,410);
        wall l9 = new wall(110,210,0);
        wall l10 = new wall(110,300);
        wall l11 = new wall(110,610);
        wall l12 = new wall(110,610,0);
        wall l13 = new wall(310,610,0);
        wall l14 = new wall(210,700);
        wall l16 = new wall(70,495,false);
        wall l17 = new wall(70,510,false);
        wall l18 = new wall(70,525,false);
        wall l19 = new wall(80,540,"s");
        wall l20 = new wall(95,540,"s");
        wall l21 = new wall(110,540,"s");

        //right
        wall r1 = new wall(505,610,0);
        wall r2 = new wall(600,710,0);
        wall r3 = new wall(610,510);
        wall r4 = new wall(700,510,0);
        wall r5 = new wall(700,610,0);
        wall r6 = new wall(710,400);
        wall r7 = new wall(610,200);
        wall r8 = new wall(500,110,0);
        wall r9 = new wall(700,110,0);
        wall r10 = new wall(710,610,"s");
        wall r11 = new wall(725,610,"s");
        wall r12 = new wall(685,610,"s");
        wall r13 = new wall(670,610,"s");
        wall r14 = new wall(510,110);
        wall r15 = new wall(740,280,false);
        wall r16 = new wall(740,295,false);
        wall r17 = new wall(740,310,false);
        wall r18 = new wall(695,270,"s");
        wall r19 = new wall(710,270,"s");
        wall r20 = new wall(725,270,"s");

        walls.add(w1);
        walls.add(w2);
        walls.add(w3);
        walls.add(w4);
        walls.add(w5);
        walls.add(w6);
        walls.add(w7);
        walls.add(w8);
        walls.add(w9);
        walls.add(w10);
        walls.add(w11);
        walls.add(w12);
        walls.add(w13);
        walls.add(w14);
        walls.add(w15);
        walls.add(w16);
        walls.add(w17);
        walls.add(w18);

        walls.add(l1);
        walls.add(l2);
        walls.add(l3);
        walls.add(l4);
        walls.add(l5);
        walls.add(l6);
        walls.add(l7);
        walls.add(l8);
        walls.add(l9);
        walls.add(l10);
        walls.add(l11);
        walls.add(l12);
        walls.add(l13);
        walls.add(l14);
        walls.add(l16);
        walls.add(l17);
        walls.add(l18);
        walls.add(l19);
        walls.add(l20);
        walls.add(l21);

        walls.add(r1);
        walls.add(r2);
        walls.add(r3);
        walls.add(r4);
        walls.add(r5);
        walls.add(r6);
        walls.add(r7);
        walls.add(r8);
        walls.add(r9);
        walls.add(r10);
        walls.add(r11);
        walls.add(r12);
        walls.add(r13);
        walls.add(r14);
        walls.add(r15);
        walls.add(r16);
        walls.add(r17);
        walls.add(r18);
        walls.add(r19);
        walls.add(r20);
    }

    public void initMap5()
    {
        //middle
        wall middle0 = new wall(210, 210,0);
        wall middle1 = new wall(210, 310,0);
        wall middle2 = new wall(210, 410,0);
        wall middle14 = new wall(300,510,0);
        wall middle3 = new wall(210, 510);
        wall middle4 = new wall(210, 210);
        wall middle6 = new wall(410, 210);
        wall middle7 = new wall(510, 300); 
        wall middle8 = new wall(600, 310, 0); 
        wall middle9 = new wall(600, 410, 0); 
        wall middle10 = new wall(600, 510, 0); 
        wall middle11 = new wall(310, 600);
        wall middle13 = new wall(510, 600);
        wall middle15 = new wall(510,210,0);

        //left
        wall l1 = new wall(10,710);
        wall l2 = new wall(10,210);
        wall l3 = new wall(110,210);
        wall l4 = new wall(210,710,0);
        wall l5 = new wall(210,610,0);
        wall l6 = new wall(110,610);
        wall l7 = new wall(110,510,0);
        wall l8 = new wall(110,410,0);
        wall l9 = new wall(110,210,0);
        wall l10 = new wall(310,710,0);
        wall l11 = new wall(400,610,0);
        wall l12 = new wall(410,710);
        wall l14 = new wall(610,710);
        wall l15 = new wall(700,695,false);
        wall l16 = new wall(700,680,false);
        wall l17 = new wall(510,625,false);
        wall l18 = new wall(510,610,false);

        //right
        wall r1 = new wall(710,100);
        wall r2 = new wall(610,600);
        wall r3 = new wall(710,600);
        wall r4 = new wall(700,510,0);
        wall r5 = new wall(700,310,0);
        wall r6 = new wall(700,210,0);
        wall r7 = new wall(110,100);
        wall r8 = new wall(310,100);
        wall r9 = new wall(610,200);
        wall r10 = new wall(600,10,0);
        wall r11 = new wall(600,110,0);
        wall r12 = new wall(500,10,0);
        wall r13 = new wall(410,110,0);
        wall r14 = new wall(300,195,false);
        wall r15 = new wall(300,180,false);
        wall r16 = new wall(110,110,false);
        wall r17 = new wall(110,125,false);

        walls.add(middle0);
        walls.add(middle1);
        walls.add(middle2);
        walls.add(middle3);
        walls.add(middle4);
        walls.add(middle6);
        walls.add(middle7);
        walls.add(middle8);
        walls.add(middle9);
        walls.add(middle10);
        walls.add(middle11);
        walls.add(middle13);
        walls.add(middle14);
        walls.add(middle15);

        walls.add(l1);
        walls.add(l2);
        walls.add(l3);
        walls.add(l4);
        walls.add(l5);
        walls.add(l6);
        walls.add(l7);
        walls.add(l8);
        walls.add(l9);
        walls.add(l10);
        walls.add(l11);
        walls.add(l12);
        walls.add(l14);
        walls.add(l15);
        walls.add(l16);
        walls.add(l17);
        walls.add(l18);

        walls.add(r1);
        walls.add(r2);
        walls.add(r3);
        walls.add(r4);
        walls.add(r5);
        walls.add(r6);
        walls.add(r7);
        walls.add(r8);
        walls.add(r9);
        walls.add(r10);
        walls.add(r11);
        walls.add(r12);
        walls.add(r13);
        walls.add(r14);
        walls.add(r15);
        walls.add(r16);
        walls.add(r17);
    }

    public void reset()
    {
        if (main.p1 >= 3)
        {
            alpha = 0;
            flasher = false;
            ingame = false;
            main.p1 = 0;
            main.p2 = 0;
            timer.start();
        }
        else if (main.p2 >= 3)
        {
            alpha = 0;
            flasher = false;
            ingame = false;
            main.p1 = 0;
            main.p2 = 0;
            timer.start();
        }
        else
        {
            endX = 0;   
            endY = 0;
            //initialize map
            int r = (int) (Math.random() * main.maps.size());
            walls.clear();
            initMap(main.maps.get(r));
            main.maps.remove(r);
            flasher = true;
            timer.start();
            ingame = true;
        }
    }

    public void backToMenu()
    {
        Menu menu = new Menu(frame);
        frame.add(menu);
        frame.requestFocus();
        frame.requestFocusInWindow();
        frame.validate();
        frame.setVisible(true);

        SoundEffect menuSound = SoundEffect.MENU;
        menuSound.play(false);
    }

    public class KeyHandle implements KeyListener
    {
        public void keyReleased(KeyEvent e)
        {
            if (ingame)
            {
                player1.keyReleased(e);
                player2.keyReleased(e);
            }
        }

        public void keyPressed(KeyEvent e)
        {
            if (ingame)
            {
                player1.keyPressed(e);
                player2.keyPressed(e);
            }
        }

        public void keyTyped(KeyEvent e)
        {
        }
    }
}
