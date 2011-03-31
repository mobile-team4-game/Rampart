package mobile.team4.game;

import java.util.ArrayList;

public class Game {
	
	Boolean isRunning;
	Boolean isPaused;
	float deltaTime;
	
	ArrayList<Cannon> cannon_list;
	// WallPieces are currently lists of points according to shape, want wall_list
	// to be individual wall squares
	ArrayList<WallPiece> wall_list;
	ArrayList<BackgroundPiece> background_list;
	ArrayList<Castle> castle_list;
	
	// pretty rough outline
	
	public void run() {
	    while (isRunning) {
	        while (isPaused && isRunning) {
	            // sleep(100);
	        }
	        update();
	    }
	}
	
	private void update() {
	    updateState();
	    updateInput();
	    updateAnimations();
	    updateSound();
	}

	private void updateAnimations() {
		// TODO Auto-generated method stub
		
	}

	private void updateSound() {
		// TODO Auto-generated method stub
		
	}

	private void updateInput() {
		// TODO Auto-generated method stub
		
	}

	private void updateState() {
		// TODO Auto-generated method stub
		
	}


}