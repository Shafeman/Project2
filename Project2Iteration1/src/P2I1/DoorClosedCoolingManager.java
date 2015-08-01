package P2I1;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class DoorClosedCoolingManager {
	private EventListenerList listenerList = new EventListenerList();
	private static DoorClosedCoolingManager instance;
	
	/**
	 * Private to make it a singleton
	 */
	private DoorClosedCoolingManager() {
	}

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static DoorClosedCoolingManager instance() {
		if (instance == null) {
			instance = new DoorClosedCoolingManager();
		}
		return instance;
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addDoorClosedCoolingListener(DoorClosedCoolingListener listener) {
		listenerList.add(DoorClosedCoolingListener.class, listener);
	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the object to be removed
	 */
	public void removeDoorClosedCoolingListener(DoorClosedCoolingListener listener) {
		listenerList.remove(DoorClosedCoolingListener.class, listener);
	}

	/**
	 * Handles the request to cook.
	 * @param <T>
	 * 
	 * @param event
	 *            the DoorClosedCoolingEvent object
	 */
	public <T> void processEvent(DoorClosedCoolingEvent event) {
		EventListener[] listeners = listenerList.getListeners(DoorClosedCoolingListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((DoorClosedCoolingListener) listeners[index]).doorClosedCooling(event);
		}
	}

}
