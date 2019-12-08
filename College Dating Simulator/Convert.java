import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.color.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.text.DecimalFormat;

public class Convert{
	
	private JLabel convertLabel, toLabel, finished;
	private JTextField input;
	private JComboBox us, metric;
	private JButton go;
	private String [] usBox = new String [] {"Mile", "Yard", "Foot", "Inch"};
	private String [] metricBox = new String [] {"Kilometer", "Meter", "Centimeter"};
	
	public static void main(String []args)
	{
		Convert main = new Convert();
		main.createWindow();
	}
	
	public void createWindow()
	{
		//frame
		JFrame frame = new JFrame("Convert System");
		frame.setLayout(new FlowLayout());
		frame.setSize(500, 250);
		frame.setBackground(Color.YELLOW);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//labels
		convertLabel = new JLabel("Convert");
		toLabel = new JLabel("To");
		finished = new JLabel();
		
		//input text field
		input = new JTextField(5);
		input.setEditable(true);
		
		//combobox
		us = new JComboBox(usBox);
		metric = new JComboBox(metricBox);
		
		//button
		go = new JButton("GO!");
		go.addActionListener((ActionListener) new ButtonListener());
		
		//adding
		frame.add(convertLabel);
		frame.add(input);
		frame.add(us);
		frame.add(toLabel);
		frame.add(metric);
		frame.add(go);
		frame.add(finished);
		
		frame.setVisible(true);
	}
	
	public static String convertStuff(String us, double units, String metric)
	{
		if (us.equals("Foot"));
		else if(us.equals("Mile"))
			units *= 5280;
		else if (us.equals("Yard"))
			units *= 3;
		else if (us.equals("Inch"))
			units /= 12;
		
		if(metric.equals("Kilometer"))
			units /= 3280.8399;
		else if (metric.equals("Meter"))
			units /= 3.28084;
		else if (metric.equals("Centimeter"))
			units *= 30.48;
		
		DecimalFormat f = new DecimalFormat("##.00");
		
		return f.format(units);
	}
	
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(go))
			{
				try
				{
					Double.valueOf(input.getText());
				}
				catch (NumberFormatException l)
				{
					finished.setText("You did not input a number");
					return;
				}
				String text = convertStuff(us.getSelectedItem().toString(), Double.valueOf(input.getText()), metric.getSelectedItem().toString());
				finished.setText(text);
			}
		}
		
	}

}
