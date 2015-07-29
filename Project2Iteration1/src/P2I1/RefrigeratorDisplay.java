
package P2I1;

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
	
	public abstract void setRefrigerator(Refrigerator refrigerator);
	
	
	/**
	 * Notify that the fridge light is on
	 */
	public abstract void turnFridgeLightOn();
	
	/**
	 * Notify that the fridge light is off
	 */
	public abstract void turnFridgeLightOff();
	
	/**
	 * Notify that the freezer light is on
	 */
	public abstract void turnFreezerLightOn();
	
	/**
	 * Notify that the freezer light is off
	 */
	public abstract void turnFreezerLightOff();
	
	/**
	 * Notify that the freezer is cooling
	 */
	public abstract void freezerCooling();
	
	/**
	 * Notify that the freezer is NOT cooling
	 */
	public abstract void freezerIdle();
	
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
	 * Updates the Freezer temperature
	 * @param temp
	 */
	public abstract void updateFreezerTemp(int temp);
	
	/**
	 * Sets the temperatures at the start of the program
	 * @param room
	 * @param fridge
	 * @param freezer
	 */
	public abstract void setStartTemps(int room, int fridge, int freezer);

}
