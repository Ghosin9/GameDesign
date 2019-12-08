import java.awt.*;
import javax.swing.*;
import java.net.URL;
import javax.swing.BoxLayout;
import java.awt.event.*;

/**
 * Write a description of class Buttons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public enum Buttons 
{
    //FIND OUT WHY IT OPENS UP SO MANY TABS WHEN CLICKING ON BUTTONS
    
    MENU("images/menu_button.png", "images/menu_glow.png"),
    
    PLAY("images/play_button.png", "images/play_glow.png"),
    
    INSTRUCTIONS("images/instructions_button.png", "images/instruction_glow.png"),
    
    CONTROLS("images/control_button.png", "images/control_glow.png"),
    
    CREDITS("images/credits_button.png", "images/credits_glow.png"),
    
    EXIT("images/exit_button.png", "images/exit_glow.png");
    
    private JButton button;
    
    Buttons(String fileName, String gloName)
    {
        URL url = this.getClass().getResource(fileName);
        URL gloUrl = this.getClass().getResource(gloName);
        ImageIcon image = new ImageIcon(url);
        ImageIcon gloImage = new ImageIcon(gloUrl);
        button = new JButton(image);
        button.setRolloverIcon(gloImage);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    public JButton getJButton()
    {
        return button;
    }
}