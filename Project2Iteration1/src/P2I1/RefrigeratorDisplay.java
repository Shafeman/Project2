
package P2I1;
/**
 * Barbarians: Douglas Brian Shaffer and Jonathan Franco
 * Specifies what the display system should do. Note that the implementation has
 * a lot of freedom to choose its display.
 */

import java.util.Observable;

public abstract class RefrigeratorDisplay extends Observable{
	
	protected static RefrigeratorContext context;
	protected static RefrigeratorDisplay instance;
	
	
	/**
	 * Initialized the context and instance
	 */
	protected RefrigeratorDisplay() {
		instance = this;
		context = RefrigeratorContext.instance();
	}
	
	/**
	 * Singleton
	 * 
	 * @return the instance
	 */
	public static RefrigeratorDisplay instance() {
		return instance;
	}
	
	/**
	 * Do the initialization to make the context an observer
	 */
	public void initialize() {
		context.initialize();
	}	
	
	/**
	 * Notify that the fridge light is on
	 */
	public abstract void turnFridgeLightOn();
	
	/**
	 * Notify that the fridge light is off
	 */
	public abstract void turnFridgeLightOff();
	
	/**
	 * Notify that the fridge is cooling
	 */
	public abstract void fridgeCooling();
	
	/**
	 * Notify that the fridge is NOT cooling
	 */
	public abstract void fridgeIdle();
	
	/**
	 * Updates the Fridge temperature
	 * @param temp 
	 */
	public abstract void updateFridgeTemp(int temp);
	
	/**
	 * Updates the door being open
	 */
	public abstract void doorOpen();
	
	
	/**
	 * Updates the door being closed
	 */
	public abstract void doorClosed();
	
	/**
	 * returns the int value of the property requested.
	 * @param property
	 * @return
	 */
	public abstract int getProperty(String property);
}
