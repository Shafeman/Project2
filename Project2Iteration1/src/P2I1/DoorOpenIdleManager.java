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


