package gameOfLife;

import java.awt.Toolkit;
import java.awt.Dimension;

public class cMain {

	public static void main(String[] args) 
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int windowWidth = (int)screenSize.getWidth() - 200;
		int windowHeight = (int)screenSize.getHeight() - 50;
		int gameWidth = (int)screenSize.getWidth() - 600;
		int gameHeight = (int)screenSize.getHeight() - 150;
		
		GameOfLife cGameOfLife = new GameOfLife("Conway's Game Of Life", windowWidth, windowHeight, gameWidth, gameHeight, 4);		
		
		cGameOfLife.setVisible(true);
		while(true){
			cGameOfLife.cCanvas.countNeighbours();
			cGameOfLife.cCanvas.calcNextStep();
			cGameOfLife.cCanvas.update(cGameOfLife.cCanvas.getGraphics());			
			for(long i = 0; i < 0xAFFFFFFL; i++){
				int x = 5;
			};
		}
	}

}
