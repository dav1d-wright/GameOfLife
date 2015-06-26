package gameOfLife;


public class cMain {

	public static void main(String[] args) {
		GameOfLife cGameOfLife = new GameOfLife("Conway's Game Of Life", 1200, 1200, 30, 30);		
		
		cGameOfLife.setVisible(true);
		while(true){
			cGameOfLife.cCanvas.countNeighbours();
			cGameOfLife.cCanvas.calcNextStep();
			cGameOfLife.cCanvas.update(cGameOfLife.cCanvas.getGraphics());			
			for(long i = 0; i < 0xFFFFFFFL; i++){
				int x = 5;
			};
		}
	}

}
