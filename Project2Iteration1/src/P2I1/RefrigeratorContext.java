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
/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * The context is an obserer for the clock and stores the context info for
 * states
 *
 */

public class RefrigeratorContext {
	private static RefrigeratorDisplay refrigeratorDisplay;
	private RefrigeratorState currentState;
	private static Timer timer;
	private static RefrigeratorContext instance;
	
	/**
	 * Singleton
	 */
	private RefrigeratorContext() {
		instance = this;
		refrigeratorDisplay = RefrigeratorDisplay.instance();
		currentState = DoorClosedCoolingState.instance();
		timer = Timer.instance();
	}
	
	/**
	 * Return the instance
	 * 
	 * @return RefrigeratorContext()
	 */
	public static RefrigeratorContext instance() {
		if (instance == null) {
			instance = new RefrigeratorContext();
		}
		return instance;
	}
	
	/**
	 * lets door closed cooling state be the starting state adds the object as an
	 * observable for clock
	 */
	public void initialize() {
		instance.changeCurrentState(DoorClosedCoolingState.instance());
	}
	
	/**
	 * Called from the states to change the current state
	 * 
	 * @param nextState
	 *            the next state
	 */
	public void changeCurrentState(RefrigeratorState nextState) {
		currentState.leave();
		currentState = nextState;
		nextState.run();
		
	}
	
	/**
	 * Gets the display
	 * 
	 * @return the display
	 */
	public RefrigeratorDisplay getDisplay() {
		return refrigeratorDisplay;
	}
	
	/**
	 * Gets the current state
	 * 
	 * @return the state
	 */
	public RefrigeratorState getCurrentState() {
		return currentState;		
	}
	
}
