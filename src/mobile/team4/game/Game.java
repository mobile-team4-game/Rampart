package mobile.team4.game;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

	GameLoopThread _thread;
	Boolean isRunning;
	Boolean isPaused;
	float deltaTime;
	
	static int MAP_WIDTH = 10;
	static int MAP_HEIGHT = 10;
	
	ArrayList<Cannon> cannon_list;
	// WallPieces are currently lists of points according to shape, want wall_list
	// to be individual wall squares
	ArrayList<WallPiece> wall_list;		// Need to implement getPosition()
	ArrayList<BackgroundPiece> background_list;
	ArrayList<Castle> castle_list;
	ArrayList<GameObject> shot_list;	//  For cannonballs.
	GameMap map = new GameMap(MAP_WIDTH, MAP_HEIGHT);
	
	// pretty rough outline
	
	public Game(Context context) {
		super(context);
		getHolder().addCallback(this);
        _thread = new GameLoopThread(getHolder(), this);
        setFocusable(true);
	}
	
	public void placeWall(Point position, Shape s) {
		
	}

	public void updateAnimations() {
		// TODO Auto-generated method stub
		
	}

	public void updateSound() {
		// TODO Auto-generated method stub
		
	}

	public void updateInput() {
		// TODO Auto-generated method stub
		
	}

	public void updateState() {
		// TODO Auto-generated method stub
	}
	
	public void updateVideo(Canvas c) {
		Bitmap wall = BitmapFactory.decodeResource(getResources(), R.drawable.wall);
		Bitmap castle = BitmapFactory.decodeResource(getResources(), R.drawable.castle);
		Bitmap cannonball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
		Bitmap cannon = BitmapFactory.decodeResource(getResources(), R.drawable.cannon);
		
		int gridWidth = getWidth() / map.getWidth();
		int gridHeight = getHeight() / map.getHeight();
		
		for(int i = 0; i < castle_list.size(); i++) {
			Point p = castle_list.get(i).getPosition();
			c.drawBitmap(castle, p.x * gridWidth, p.y * gridHeight, null);
		}
		for(int i = 0; i < cannon_list.size(); i++) {
			Point p = cannon_list.get(i).getPosition();
			c.drawBitmap(cannon, p.x * gridWidth, p.y * gridHeight, null);
		}
		for(int i = 0; i < wall_list.size(); i++) {
			Point p = wall_list.get(i).getPosition();
			c.drawBitmap(wall, p.x * gridWidth, p.y * gridHeight, null);
		}
		for(int i = 0; i < shot_list.size(); i++) {
			Point p = shot_list.get(i).getPosition();
			c.drawBitmap(cannonball, p.x * gridWidth, p.y * gridHeight, null);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		_thread.setRunning(true);
        _thread.start();		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
        _thread.setRunning(false);
        while (retry) {
            try {
                _thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }		
	}
}