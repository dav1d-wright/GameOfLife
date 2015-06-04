package gameOfLife;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.text.AttributedCharacterIterator;
import javax.swing.*;
import java.util.Random;

public class GameOfLife extends JFrame implements ActionListener{
	JButton cBStart;
	JTextField cTSeed;
	JLabel cLSeed;
	String cSMsg = "";
	GOLCanvas cCanvas;
	int iNumElements = 4;
	int iGameWidth;
	int iGameHeight;
	private Boolean m_bRunning;
	
	GameOfLife (String aTitle, int aFrameWidth, int aFrameHeight, int aGameWidth, int aGameHeight) {
		super(aTitle);
		this.setSize(aFrameWidth, aFrameHeight);
		iGameWidth = aGameWidth;
		iGameHeight = aGameHeight;
		m_bRunning = false;
		
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                stop();
                super.windowClosing(e);
                System.exit(0);
            }
        });
        
        GridBagLayout cLayout = new GridBagLayout();
		GridBagConstraints[] cConstraints = new GridBagConstraints[iNumElements];		
		
		for(int i = 0; i < iNumElements; i++) {
			cConstraints[i] = new GridBagConstraints();
		}
	
		cCanvas = new GOLCanvas(iGameWidth, iGameHeight, 0.99);
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

		cBStart = new JButton("Start");
		cBStart.addActionListener(this);
		cTSeed = new JTextField(5);
		cTSeed.addActionListener(this);
		cLSeed = new JLabel("Seed percentage (0 <= x <= 1):");
		
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
	public synchronized void stop() {
		if (m_bRunning) {
			m_bRunning = false;
			boolean bRetry = true;
			while (bRetry) {
				// TODO implement rest of stop from stackexchange answer
			}
		}
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

class GOLCanvas extends Canvas {
	private int m_iWidth;
	private int m_iHeight;
	private int[][] m_iPoints;
	private int[][] m_iNeighbours;
	private double m_dSeed;
	private Random m_cRnd;
	private BufferedImage cBackGroundImage = null;
	
	GOLCanvas(int aWidth, int aHeight, double aSeed) {
		m_iWidth = aWidth;
		m_iHeight = aHeight;
		m_dSeed = aSeed;
		m_cRnd = new Random();
		m_cRnd.setSeed(m_cRnd.nextLong());
		m_iPoints = new int[m_iHeight][m_iWidth];
		m_iNeighbours = new int[m_iHeight][m_iWidth];
		cBackGroundImage = new BufferedImage(m_iWidth, m_iHeight, BufferedImage.TYPE_INT_RGB);

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
		m_iNeighbours[0][0] = m_iPoints[0][1] + m_iPoints[1][1] + m_iPoints[1][0];
		m_iNeighbours[0][m_iHeight-1] = m_iPoints[0][m_iHeight-2] + m_iPoints[1][m_iHeight-2] + m_iPoints[1][m_iHeight-1];
		m_iNeighbours[m_iWidth-1][m_iHeight-1] = m_iPoints[m_iWidth-1][m_iHeight-2] + m_iPoints[m_iWidth-2][m_iHeight-2] + m_iPoints[m_iWidth-2][m_iHeight-1];
		m_iNeighbours[m_iWidth-1][0] = m_iPoints[m_iWidth-1][1] + m_iPoints[m_iWidth-2][1] + m_iPoints[m_iWidth-2][0];

		// count neighbours of first and last columns without corners
		for (int i = 1; i < m_iWidth - 1; i++) {
			m_iNeighbours[i][0] = m_iPoints[i-1][0] + m_iPoints[i][1] + m_iPoints[i+1][0];
			m_iNeighbours[i][m_iHeight-1] = m_iPoints[i-1][m_iHeight-1] + m_iPoints[i][m_iHeight-2] + m_iPoints[i+1][m_iHeight-1];
		}
		
		// count neighbours of first and last rows without corners
		for (int i = 1; i < m_iHeight - 1; i++) {
			m_iNeighbours[0][i] = m_iPoints[0][i-1] + m_iPoints[1][i] + m_iPoints[0][i+1];
			m_iNeighbours[m_iWidth-1][i] = m_iPoints[m_iWidth-1][i-1] + m_iPoints[m_iWidth-2][i] + m_iPoints[m_iWidth-1][i+1];
		}
		
		// count neighbours of inner matrix elements without corners and first/last rows
		for(int i = 1; i < m_iHeight - 1; i++) {
			for(int j = 1; j < m_iWidth - 1; j++) {
					m_iNeighbours[i][j] = m_iPoints[i-1][j] + m_iPoints[i-1][j-1] + m_iPoints[i-1][j+1] + m_iPoints[i][j-1]  +
							m_iPoints[i][j+1] + m_iPoints[i+1][j+1] + m_iPoints[i+1][j-1] + m_iPoints[i+1][j];		
			}
		}	
	}
	
	public void calcNextStep () {
		for(int i = 0; i < m_iHeight - 1; i++) {
			for(int j = 0; j < m_iWidth - 1; j++) {
				if (m_iPoints[i][j] == 1){
					if((m_iNeighbours[i][j] < 2) || (m_iNeighbours[i][j] > 3)){
						// cell dies of underpopulation or overcrowding
						m_iPoints[i][j] = 0;
					}
				}
				else {
					if (m_iNeighbours[i][j] == 3) {
						// cell becomes alive thanks to reproduction
						m_iPoints[i][j] = 1;
					}
				}
			}
		}
	}
	@Override
	public void paint(Graphics aGraphics) {
		// store on screen graphics
		Graphics cScreenGraphics = aGraphics;
		// render on background image
		aGraphics = cBackGroundImage.getGraphics();

		for(int i = 0; i < m_iHeight; i++) {
			for(int j = 0; j < m_iWidth; j++) {
				if (m_iPoints[i][j] == 1){
					aGraphics.drawLine(i, j, i, j);
				}
			}
		}
		
		// rendering is done, draw background image to on screen graphics
		cScreenGraphics.drawImage(cBackGroundImage, 0, 0, null);
	}
}

//class GOLCalculator implements Runnable {
//	
//}