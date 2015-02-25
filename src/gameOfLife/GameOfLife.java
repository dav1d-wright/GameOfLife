package gameOfLife;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class GameOfLife extends Frame{
	GameOfLife (String aTitle) {
		super(aTitle);
		
		MyWindowAdapter cAdapter = new MyWindowAdapter(this);
		
		addWindowListener(cAdapter);
	}
}

class MyWindowAdapter extends WindowAdapter {
	GameOfLife m_cGameOfLife;
	
	public MyWindowAdapter(GameOfLife a_cGameOfLife) {
		this.m_cGameOfLife = a_cGameOfLife;
	}
}