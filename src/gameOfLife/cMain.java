package gameOfLife;

public class cMain {

	public static void main(String[] args) {
		GameOfLife cGameOfLife = new GameOfLife("Conway's Game Of Life", 1200, 1200, 350, 350);		
		
		cGameOfLife.setVisible(true);
		while(true){
			cGameOfLife.cCanvas.countNeighbours();
			cGameOfLife.cCanvas.calcNextStep();
			cGameOfLife.cCanvas.update(cGameOfLife.cCanvas.getGraphics());			
		}
	}

}
