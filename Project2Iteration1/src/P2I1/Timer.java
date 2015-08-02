package P2I1;

import java.util.Observable;
import java.util.Observer;

/**
 * The Timer for the microwave
 *
 */
public class Timer implements Observer {
	private static Timer instance;
	private int doorTime;
	private int compressorTime;
	private int openWarmingRate;
	private int closedWarmingRate;
	private int coolingRate;

	/**
	 * For singleton
	 */
	private Timer() {
		instance = this;
		Clock.instance().addObserver(instance);
		coolingRate = GUIDisplay.getProperty("FridgeCoolRate");
		openWarmingRate = GUIDisplay.getProperty("FridgeRateLossDoorOpen");
		closedWarmingRate = GUIDisplay.getProperty("FridgeRateLossDoorClosed");
	}

	/**
	 * For singleton pattern
	 * 
	 * @return the instance
	 */
	public static Timer instance() {
		if (instance == null) {
			instance = new Timer();
		}
		return instance;
	}

	/**
	 * Set the time for the door timer
	 */
	public void resetDoorTime() {
		this.doorTime = 0;
	}
	
	/**
	 * Set the time for the compressor timer
	 */
	public void resetCompressorTime(){
		this.compressorTime = 0;
	}

	/**
	 * Get the clock tick and process it
	 */
	@Override
	public void update(Observable clock, Object value) {
		//if (doorTime

	}
}
