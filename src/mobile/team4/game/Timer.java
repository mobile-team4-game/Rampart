package mobile.team4.game;

public class Timer {
	Timer() {
		time = 0;
	}
	
	public void start() {		// Also acts as a reset.
		time = System.currentTimeMillis();
	}
	
	public long getElapsedTime() {
		return System.currentTimeMillis() - time;
	}
	
	public long getFrameDifference(long recentTime) {
		return recentTime - time;
	}
	
	private long time;
}
