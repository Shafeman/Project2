package P2I1;

import java.util.Observable;

/**
 * Implements a clock as a Runnable. Extends Observable to ease communication
 *
 */
public class Clock extends Observable implements Runnable {
	private Thread thread = new Thread(this);
	private static Clock instance;

	public enum Events {
		CLOCK_TICKED_EVENT
	};

	/**
	 * Start the thread
	 */
	private Clock() {
		thread.start();
	}

	/**
	 * To get the instance
	 * 
	 * @return returns the clock
	 */
	public static Clock instance() {
		if (instance == null) {
			instance = new Clock();
		}
		return instance;
	}

	/**
	 * Infinite loop to generate the clock ticks Notify all users when clock
	 * ticks
	 */
	@Override
	public void run() {
		try {
			while (true) {
				Thread.sleep(1000);
				setChanged();
				notifyObservers(Events.CLOCK_TICKED_EVENT);
			}
		} catch (InterruptedException ie) {
		}
	}
}