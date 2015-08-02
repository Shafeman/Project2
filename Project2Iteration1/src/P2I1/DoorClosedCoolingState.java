package P2I1;

import P2I1.GUIDisplay.Settings;

/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * State of the Refrigerator when the door is closed and idle.
 */

public class DoorClosedCoolingState extends RefrigeratorState 
	implements DoorClosedIdleListener, DoorOpenCoolingListener, 
	TemperatureUpListener, TemperatureDownListener{
	
	public static DoorClosedCoolingState instance;
	
	/**
	 * Singleton
	 */
	private DoorClosedCoolingState() {
	}
	/**
	 * Singleton
	 * @return the object
	 */
	public static DoorClosedCoolingState instance() {
		if(instance == null) {
			instance = new DoorClosedCoolingState();			
		}
		return instance;
	}	
	

	/**
	 * This will initialize the state
	 */
	@Override
	public void run() {
		DoorClosedIdleManager.instance().addDoorClosedIdleListener(instance);
		DoorOpenCoolingManager.instance().addDoorOpenCoolingListener(instance);
		TemperatureUpManager.instance().addTemperatureUpListener(instance);
		TemperatureDownManager.instance().addTemperatureDownListener(instance);
		Timer.instance().setCoolingRate(display.getProperty(Settings.FRIDGE_COOL_RATE));
		Timer.instance().setWarmingRate(display.getProperty(Settings.FRIDGE_RATE_LOSS_DOOR_CLOSED));
		Timer.instance().resetCompressorTime();
		display.fridgeCooling();
		display.turnFridgeLightOff();
	}

	/**
	 * When the Refrigerator leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		DoorClosedIdleManager.instance().removeDoorClosedIdleListener(this);
		DoorOpenCoolingManager.instance().removeDoorOpenCoolingListener(this);
		TemperatureUpManager.instance().removeTemperatureUpListener(this);
		TemperatureDownManager.instance().removeTemperatureDownListener(this);		
	}
	
	/**
	 * Handles door open event while the refrigerator is cooling
	 */
	@Override
	public void doorOpenedCooling(DoorOpenCoolingEvent event) {
		context.changeCurrentState(DoorOpenCoolingState.instance());
		
	}
	/**
	 * Handles door closed event while the refrigerator is idle
	 */
	@Override
	public void doorClosedIdle(DoorClosedIdleEvent event) {
		context.changeCurrentState(DoorClosedIdleState.instance());
		
	}
	
	/**
	 * Handles temperature lowering events
	 */
	@Override
	public void lowerTemp(TemperatureDownEvent event) {
		context.lowerCurrentTemperature();
		int temp = context.getCurrentTemperature();
		display.updateFridgeTemp(temp);
		if(temp <= display.getDesiredFridgeTemperature()){
			DoorClosedIdleManager.instance().processEvent(new DoorClosedIdleEvent(instance));
		}	
	}
	
	/**
	 * Handles temperature raising events
	 */
	@Override
	public void raiseTemp(TemperatureUpEvent event) {
		int temp = context.getCurrentTemperature();
		if (temp < context.getRoomTemperature()){
			context.raiseCurrentTemperature();
			display.updateFridgeTemp(++temp);
		}		
	}
}
