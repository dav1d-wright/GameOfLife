package gameOfLife;

public class cMain {

	public static void main(String[] args) {
		GameOfLife cGameOfLife = new GameOfLife("Conway's Game Of Life");
		
		
		cGameOfLife.setSize(1000, 1000);
		cGameOfLife.setVisible(true);
		
	}

}
