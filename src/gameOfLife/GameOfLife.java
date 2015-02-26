package gameOfLife;

import java.awt.*;
import java.awt.event.*;

/*
 	<applet code ="AppletFrame" width=300 height =50>
 	</applet>
 */

public class GameOfLife extends Frame{
	GameOfLife (String aTitle) {
		super(aTitle);
		
		MyWindowAdapter cAdapter = new MyWindowAdapter(this);
		
		addWindowListener(cAdapter);
	}
	
	public void paint (Graphics aGraphics){
		aGraphics.drawString("This is in frame window", 10, 40);
	}
}

class MyWindowAdapter extends WindowAdapter {
	GameOfLife m_cGameOfLife;
	
	public MyWindowAdapter(GameOfLife a_cGameOfLife) {
		this.m_cGameOfLife = a_cGameOfLife;
	}
	
	public void windowClosing(WindowEvent a_cWindowEvent) {
		m_cGameOfLife.setVisible(false);
	}
}