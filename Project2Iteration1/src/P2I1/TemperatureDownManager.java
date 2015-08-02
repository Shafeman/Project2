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
public class TemperatureDownManager {
	private EventListenerList listenerList = new EventListenerList();
	private static TemperatureDownManager instance;
	
	/**
	 * Private to make it a singleton
	 */
	private TemperatureDownManager() {
	}

	/**
	 * Returns the only instance of the class
	 * 
	 * @return the only instance of the class
	 */
	public static TemperatureDownManager instance() {
		if (instance == null) {
			instance = new TemperatureDownManager();
		}
		return instance;
	}

	/**
	 * Adds a listener
	 * 
	 * @param listener
	 *            an object that wants to listen to the event
	 */
	public void addTemperatureDownListener(TemperatureDownListener listener) {
		listenerList.add(TemperatureDownListener.class, listener);
	}

	/**
	 * Removes a listener
	 * 
	 * @param listener
	 *            the object to be removed
	 */
	public void removeTemperatureDownListener(TemperatureDownListener listener) {
		listenerList.remove(TemperatureDownListener.class, listener);
	}

	/**
	 * Handles the request to cook.
	 * @param <T>
	 * 
	 * @param event
	 *            the TemperatureUpEvent object
	 */
	public void processEvent(TemperatureDownEvent event) {
		EventListener[] listeners = listenerList.getListeners(TemperatureDownListener.class);
		for (int index = 0; index < listeners.length; index++) {
			((TemperatureDownListener) listeners[index]).lowerTemp(event);
		}
	}

}
