import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.BoxLayout;
import java.net.URL;
import javax.sound.sampled.*;
import java.util.*;
import java.lang.Math;

/**
 * 
 * 
 */
public class Menu extends JPanel implements ActionListener 
{
    private JButton play, inst, ctrl, cred, exit;
    private JLabel title;
    private JFrame frame;
    private JPanel panel;
    private ImageIcon playImage, playGlo, ctrlImage, ctrlGlo, instImage, instGlo, exitImage, exitGlo, titleImage, titleGlo;
    public static ArrayList<Integer> maps;

    public Menu(JFrame myframe)
    {
        frame = myframe;

        frame.setTitle("GLO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(900,900));
        panel.setMaximumSize(new Dimension(900,900) );
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Title
        URL titleURL = this.getClass().getResource("images/GLO.png");
        titleImage = new ImageIcon(titleURL);

        title = new JLabel(titleImage);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Play Button
        Buttons playBtn = Buttons.PLAY;
        play = playBtn.getJButton();

        //Instruction Button
        Buttons instBtn = Buttons.INSTRUCTIONS;
        inst = instBtn.getJButton();

        //Controls Button
        Buttons ctrlBtn = Buttons.CONTROLS;
        ctrl = ctrlBtn.getJButton();

        //Credits button
        Buttons credBtn = Buttons.CREDITS;
        cred = credBtn.getJButton();

        //Exit Button
        Buttons exitBtn = Buttons.EXIT;
        exit = exitBtn.getJButton();

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalGlue());
        panel.add(play);
        panel.add(Box.createVerticalGlue());
        panel.add(inst);
        panel.add(Box.createVerticalGlue());
        panel.add(ctrl);
        panel.add(Box.createVerticalGlue());
        panel.add(cred);
        panel.add(Box.createVerticalGlue());
        panel.add(exit);
        panel.add(Box.createVerticalGlue());

        play.addActionListener(this);
        inst.addActionListener(this);
        ctrl.addActionListener(this);
        cred.addActionListener(this);
        exit.addActionListener(this);

        frame.setContentPane(panel);
        frame.setSize(900,900);
        frame.setMinimumSize(new Dimension (900,900));
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == play)
        {
            frame.getContentPane().removeAll();
            SoundEffect btnS = SoundEffect.MENU;
            btnS.play(false);
            int r = (int) (Math.random() * main.maps.size());
            Board game = new Board(main.maps.get(r), frame);
            main.maps.remove(r);
            frame.add(game);
            game.setVisible(true);
            game.requestFocus();
            game.requestFocusInWindow();
            frame.validate();
        }
        else if (event.getSource() == inst)
        {
            frame.getContentPane().removeAll();
            Instruction inst = new Instruction(frame);
            frame.add(inst);
            frame.requestFocus();
            frame.requestFocusInWindow();
            frame.validate();

            SoundEffect btnS = SoundEffect.MENU;
            btnS.play(false);
        }
        else if (event.getSource() == ctrl)
        {
            frame.getContentPane().removeAll();
            Controls ctrl = new Controls(frame);
            frame.add(ctrl);
            frame.requestFocus();
            frame.requestFocusInWindow();
            frame.validate();

            SoundEffect btnS = SoundEffect.MENU;
            btnS.play(false);
        }
        else if (event.getSource() == cred)
        {
            frame.getContentPane().removeAll();
            Credits cred = new Credits(frame);
            frame.add(cred);
            frame.requestFocus();
            frame.requestFocusInWindow();
            frame.validate();

            SoundEffect btnS = SoundEffect.MENU;
            btnS.play(false);
        }
        else if (event.getSource() == exit)
        {
            System.exit(0);
        }
    }
}