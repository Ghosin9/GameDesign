import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * Write a description of class Instruction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Controls extends JPanel implements ActionListener
{
    private JFrame frame;
    private JPanel panel;
    private JButton menu;
    private JLabel label;
    private ImageIcon ctrl;

    public Controls(JFrame myframe)
    {
        frame = myframe;
        requestFocus();
        requestFocusInWindow();
        requestFocus(true);

        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(900,900));
        panel.setMaximumSize(new Dimension(900,900));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Title
        URL url = this.getClass().getResource("images/controls.png");
        ctrl = new ImageIcon(url);
        label = new JLabel(ctrl);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //Menu Button
        Buttons menubtn = Buttons.MENU;
        menu = menubtn.getJButton();
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(label);
        panel.add(menu);

        menu.addActionListener(this);

        frame.add(panel);
    }

    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == menu)
        {
            frame.getContentPane().removeAll();
            Menu menu = new Menu(frame);
            frame.add(menu);
            frame.requestFocus();
            frame.requestFocusInWindow();
            frame.pack();
            frame.validate();

            SoundEffect menuSound = SoundEffect.MENU;
            menuSound.play(false);
        }
    }
}