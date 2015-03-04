package gameOfLife;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Random;
/*
 	<applet code ="AppletFrame" width=300 height =50>
 	</applet>
 */

public class GameOfLife extends Frame implements ActionListener{
	Button cBStart;
	TextField cTSeed;
	Label cLSeed;
	String cSMsg = "";
	GOLCanvas cCanvas;
	int iNumElements = 4;
	int iGameWidth = 600;
	int iGameHeight = 600;
	
	GameOfLife (String aTitle) {
		super(aTitle);
		
		MyWindowAdapter cAdapter = new MyWindowAdapter(this);
		addWindowListener(cAdapter);
		
		GridBagLayout cLayout = new GridBagLayout();
		GridBagConstraints[] cConstraints = new GridBagConstraints[iNumElements];		
		
		for(int i = 0; i < iNumElements; i++) {
			cConstraints[i] = new GridBagConstraints();
		}
	
		cCanvas = new GOLCanvas(iGameWidth, iGameHeight, 0.35);
		cCanvas.init();
		cCanvas.setSize(iGameWidth, iGameHeight);		
		
		
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
		this.repaint();
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

class GOLCanvas extends Canvas {
	private int m_iWidth;
	private int m_iHeight;
	private int[][] m_iPoints;
	private int[][] m_iNeighbours;
	private double m_dSeed;
	private Random m_cRnd;
	
	GOLCanvas(int aWidth, int aHeight, double aSeed) {
		m_iWidth = aWidth;
		m_iHeight = aHeight;
		m_dSeed = aSeed;
		m_cRnd = new Random();
		m_cRnd.setSeed(m_cRnd.nextLong());
		m_iPoints = new int[m_iHeight][m_iWidth];
		m_iNeighbours = new int[m_iHeight][m_iWidth];
	}
	
	public void init() {
		for(int i = 0; i < m_iHeight; i++) {
			for(int j = 0; j < m_iWidth; j++) {
				m_iPoints[i][j] = this.getRandomInt(m_dSeed);
			}
		}
	}
	
	private int getRandomInt(double aProbability) {
		return (m_cRnd.nextDouble() < m_dSeed) ? 1 : 0;
	}
	
	public void countNeighbours () {
		// count neighbours for corner elements
		m_iNeighbours[0][0] = m_iNeighbours[0][1] + m_iNeighbours[1][1] + m_iNeighbours[1][0];
		m_iNeighbours[0][m_iHeight-1] = m_iNeighbours[0][m_iHeight-2] + m_iNeighbours[1][m_iHeight-2] + m_iNeighbours[1][m_iHeight-1];
		m_iNeighbours[m_iWidth-1][m_iHeight-1] = m_iNeighbours[m_iWidth-1][m_iHeight-2] + m_iNeighbours[m_iWidth-2][m_iHeight-2] + m_iNeighbours[m_iWidth-2][m_iHeight-1];
		m_iNeighbours[m_iWidth-1][0] = m_iNeighbours[m_iWidth-1][1] + m_iNeighbours[m_iWidth-2][1] + m_iNeighbours[m_iWidth-2][0];

		// TODO count first row (without corners)
		
		// TODO count last row  (without corners)
		
		// TODO count first column  (without corners)
		
		// TODO count last column  (without corners)
		
		// count neighbours of inner matrix elements without corners and first/last rows
		for(int i = 1; i < m_iHeight - 1; i++) {
			for(int j = 1; j < m_iWidth - 1; j++) {
				if (m_iPoints[i][j] == 1){
					// TODO count all 8 neighbours;
				}
			}
		}	
	}
	
	public void calcNextStep () {
		// TODO apply rules here
	}
	public void paint(Graphics aGraphics) {
		
			for(int i = 0; i < m_iHeight; i++) {
				for(int j = 0; j < m_iWidth; j++) {
					if (m_bPoints[i][j]){
						aGraphics.drawLine(i, j, i, j);
					}
				}
			}	
		}
}