package mobile.team4.game;

import android.app.Activity;
import android.os.Bundle;

public class Rampart extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		LShape L = new LShape();
		L.setPosition(2,3);
		L.print();
		L.rotate();
		L.print();
		L.rotate();
		L.print();
    }
}