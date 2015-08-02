
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
	 * returns the int value of the property requested.
	 * @param property
	 * @return
	 */
	public abstract int getProperty(String property);
	
	/**
	 * gets the room temperature from the GUI
	 * @return
	 */
	public abstract int getRoomTemperatureSetting();
	
	/**
	 * gets the desired room temperature from the GUI
	 * @return
	 */
	public abstract int getDesiredFridgeTemperature();
	
	/**
	 * resets the room temperature text box
	 * @param value
	 */
	public abstract void resetRoomTemperatureDisplay(int value);
	
	/**
	 * Updates the desired fridge temperature text box
	 * @param value
	 */
	public abstract void resetDesiredFridgeTemperature(int value);
}
