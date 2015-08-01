package P2I1;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class DoorClosedIdleManager {
	private EventListenerList listenerList = new EventListenerList();
	private static DoorClosedIdleManager instance;
	
	/**
	 * Private to make it a singleton
	 */
	private DoorClosedIdleManager() {
	}

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static DoorClosedIdleManager instance() {
		if (instance == null) {
			instance = new DoorClosedIdleManager();
		}
		return instance;
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addDoorClosedIdleListener(DoorClosedIdleListener listener) {
		listenerList.add(DoorClosedIdleListener.class, listener);
	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the object to be removed
	 */
	public void removeDoorClosedIdleListener(DoorClosedIdleListener listener) {
		listenerList.remove(DoorClosedIdleListener.class, listener);
	}

	/**
	 * Handles the request to cook.
	 * @param <T>
	 * 
	 * @param event
	 *            the DoorClosedIdleEvent object
	 */
	public <T> void processEvent(DoorClosedIdleEvent event) {
		EventListener[] listeners = listenerList.getListeners(DoorClosedIdleListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((DoorClosedIdleListener) listeners[index]).doorClosedIdle(event);
		}
	}

}
