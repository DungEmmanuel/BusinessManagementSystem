package bms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FirstPanel extends JPanel 
{

	public FirstPanel()
	{
		
	}
	
	 public void paint(Graphics g)
	 {
	    	super.paintComponent(g);
	    	g.setColor(Color.BLACK);
	    	g.translate(1, -1);
	    	g.drawOval(150, 30, 100, 45);
	    	g.setColor(Color.green);
	    	g.fillOval(150, 30, 100, 45);
	    	g.setColor(Color.BLUE);
	    	g.drawOval(110, 55, 50, 35);
	    	g.fillOval(110, 55, 50, 35);
	    	g.setColor(Color.white);
	    	g.drawOval(180, 60, 300, 95);
	    	g.fillOval(180, 60, 300, 95);
	    	g.setColor(Color.red);
	    	g.drawOval(450, 130, 50, 30);
	    	g.fillOval(450, 130, 50, 30);
	    	g.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
	    	g.drawString("  DOASCO NIGERIA ENTERPRISE", 180, 30);
	    	
	    	
	    }
}