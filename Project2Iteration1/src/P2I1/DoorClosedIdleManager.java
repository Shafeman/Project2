/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
package P2I1;

import java.util.EventListener;
import javax.swing.event.EventListenerList;

/**
 * Maintains a list of listeners for the event and invokes their method when
 * the event is "heard." 
 */
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
	public void processEvent(DoorClosedIdleEvent event) {
		EventListener[] listeners = listenerList.getListeners(DoorClosedIdleListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((DoorClosedIdleListener) listeners[index]).doorClosedIdle(event);
		}
	}

}
