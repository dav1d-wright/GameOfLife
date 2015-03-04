package gameOfLife;

public class cMain {

	public static void main(String[] args) {
		GameOfLife cGameOfLife = new GameOfLife("Conway's Game Of Life", 1000, 1000, 600, 600);		
		
		cGameOfLife.setVisible(true);
		while(true){
			cGameOfLife.cCanvas.countNeighbours();
			cGameOfLife.cCanvas.calcNextStep();
			cGameOfLife.cCanvas.repaint();		
			
			for(int i = 0; i < 0xffffffff; i++);
		}
	}

}
