package gameOfLife;

import java.awt.*;
import java.awt.event.*;

/*
 	<applet code ="AppletFrame" width=300 height =50>
 	</applet>
 */

public class GameOfLife extends Frame implements ActionListener{
	Button cBStart;
	TextField cTSeed;
	Label cLSeed;
	String cSMsg = "";
	
	GameOfLife (String aTitle) {
		super(aTitle);
		
		MyWindowAdapter cAdapter = new MyWindowAdapter(this);
		addWindowListener(cAdapter);
		
		GridBagLayout cLayout = new GridBagLayout();
		GridBagConstraints cConstraints = new GridBagConstraints();
		
		cConstraints.gridx = 0;
		cConstraints.gridy = 1;
		
		cBStart = new Button("Start");
		cBStart.addActionListener(this);
		cTSeed = new TextField();
		cLSeed = new Label("Seed percentage (0 <= x <= 1):")
		// TODO: HANDLE AND POSITION LABEL AND TEXTFIELDE
		cLayout.setConstraints(cBStart, cConstraints);
		
		this.setLayout(cLayout);
		this.setBackground(Color.white);
		this.add(cBStart);
	}
	
	public void paint (Graphics aGraphics){
		aGraphics.drawString("This is in frame window", 10, 40);
		
		aGraphics.drawString(cSMsg, 10, 50);
	}
	
	public void actionPerformed(ActionEvent aActionEvent) {
		String str = aActionEvent.getActionCommand();
		
		if(str.equals("Start")) {
			cSMsg = "You pressed Start";
		}
		
		this.repaint();
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