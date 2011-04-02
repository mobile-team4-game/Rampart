package mobile.team4.game;

import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Rampart extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Player p = new Player("joe"/* username, mac address, either way is fine */, true);
        GameMap map = new GameMap(10, 10);
        map.placeWall(new WallPiece(WallPiece.Shape.Line), new Point(5, 5));
        map.print_map();
    }
}