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
public class Credits extends JPanel implements ActionListener
{
    private JButton menu;
    private JLabel title;
    private JPanel panel;
    private JFrame frame;
    private ImageIcon cred;

    public Credits(JFrame myframe)
    {
        frame = myframe;
        requestFocus();
        requestFocusInWindow();
        requestFocus(true);
        revalidate();

        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMinimumSize(new Dimension(900,900));
        panel.setMaximumSize(new Dimension(900,900));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Menu Button
        Buttons menubtn = Buttons.MENU;
        menu = menubtn.getJButton();
        menu.setAlignmentX(Component.CENTER_ALIGNMENT);

        URL url = this.getClass().getResource("images/credits.png");
        cred = new ImageIcon(url);
        title = new JLabel(cred);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalGlue());
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
            frame.validate();

            SoundEffect menuSound = SoundEffect.MENU;
            menuSound.play(false);
        }
    }
}