package mobile.team4.game;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;

public class Rampart extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        GameMap map = new GameMap(10, 10);
        map.placeWall(new WallPiece(WallPiece.Shape.Square), new Point(5, 5));
        map.print_map();
    }
}