package gameOfLife;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

/*
 	<applet code ="AppletFrame" width=300 height =50>
 	</applet>
 */

public class GameOfLife extends Frame implements ActionListener{
	Button cBStart;
	TextField cTSeed;
	Label cLSeed;
	String cSMsg = "";
	Canvas cCanvas;
	int iNumElements = 4;
	int iGameWidth = 600;
	int iGameHeight = 600;
	Point[][] cPoints;
	
	GameOfLife (String aTitle) {
		super(aTitle);
		
		MyWindowAdapter cAdapter = new MyWindowAdapter(this);
		addWindowListener(cAdapter);
		
		GridBagLayout cLayout = new GridBagLayout();
		GridBagConstraints[] cConstraints = new GridBagConstraints[iNumElements];		
		
		for(int i = 0; i < iNumElements; i++) {
			cConstraints[i] = new GridBagConstraints();
		}
	
		cCanvas = new Canvas();
		cCanvas.setSize(iGameWidth, iGameHeight);
		
		cPoints = new Point[iGameHeight][iGameWidth];
		for(int i = 0; i < iGameHeight; i++) {
			for(int j = 0; j < iGameWidth; j++) {
				cPoints[i][j] = new Point(j,i);
			}
		}
		
		
		
		cConstraints[0].gridx = 0;
		cConstraints[0].gridy = 1;
		cConstraints[0].insets = new Insets(1,1,1,1);

		cConstraints[1].gridx = 1;
		cConstraints[1].gridy = 1;
		cConstraints[1].insets = new Insets(1,1,1,1);

		
		cConstraints[2].gridx = 0;
		cConstraints[2].gridy = 2;
		cConstraints[2].insets = new Insets(1,1,1,1);
		
		cConstraints[3].gridx = 0;
		cConstraints[3].gridy = 0;
		cConstraints[3].insets = new Insets(2,2,2,2);

		
		cBStart = new Button("Start");
		cBStart.addActionListener(this);
		cTSeed = new TextField(5);
		cTSeed.addActionListener(this);
		cLSeed = new Label("Seed percentage (0 <= x <= 1):");
		
		cLayout.setConstraints(cLSeed, cConstraints[0]);
		cLayout.setConstraints(cTSeed, cConstraints[1]);
		cLayout.setConstraints(cBStart, cConstraints[2]);
		cLayout.setConstraints(cCanvas, cConstraints[3]);
		
		this.setLayout(cLayout);
		this.setBackground(Color.white);
		this.add(cTSeed);
		this.add(cLSeed);
		this.add(cBStart);
		this.add(cCanvas);
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