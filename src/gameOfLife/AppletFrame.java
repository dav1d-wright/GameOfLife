package gameOfLife;

import java.applet.Applet;
import java.awt.Frame;
import java.awt.Graphics;

public class AppletFrame extends Applet {
	Frame m_cFrame;
	public void init() {
		m_cFrame = new GameOfLife("Game of Life");
		m_cFrame.setSize(500, 500);
		m_cFrame.setVisible(true);
	}
	
	public void start()	{
		m_cFrame.setVisible(true);
	}
	
	public void stop()	{
		m_cFrame.setVisible(false);
	}
	
	public void paint(Graphics aGraphics) {
		aGraphics.drawString("This is in applet window", 10, 20);
	}
}
