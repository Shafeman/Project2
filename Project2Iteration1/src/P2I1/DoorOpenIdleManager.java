package P2I1;

import java.util.EventListener;

import javax.swing.event.EventListenerList;

public class DoorOpenIdleManager {
	private EventListenerList listenerList = new EventListenerList();
	private static DoorOpenIdleManager instance;
	
	/**
	 * Private to make it a singleton
	 */
	private DoorOpenIdleManager() {
	}

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static DoorOpenIdleManager instance() {
		if (instance == null) {
			instance = new DoorOpenIdleManager();
		}
		return instance;
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addDoorOpenIdleListener(DoorOpenIdleListener listener) {
		listenerList.add(DoorOpenIdleListener.class, listener);
	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the object to be removed
	 */
	public void removeDoorOpenIdleListener(DoorOpenIdleListener listener) {
		listenerList.remove(DoorOpenIdleListener.class, listener);
	}

	/**
	 * Handles the request .
	 * @param <T>
	 * 
	 * @param event
	 *            the DoorOpenIdleEvent object
	 */
	public void processEvent(DoorOpenIdleEvent event) {
		EventListener[] listeners = listenerList.getListeners(DoorOpenIdleListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((DoorOpenIdleListener) listeners[index]).doorOpenedIdle(event);
			
		}
	}

}


