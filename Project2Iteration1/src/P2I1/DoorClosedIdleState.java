package P2I1;

import P2I1.GUIDisplay.Settings;

/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * State of the Refrigerator when the door is closed and idle.
 */

public class DoorClosedIdleState extends RefrigeratorState 
	implements DoorClosedCoolingListener, DoorOpenIdleListener,
	TemperatureUpListener{
	
private static DoorClosedIdleState instance;
	
	/**
	 * Singleton
	 */
	private DoorClosedIdleState() {
		
	}
	/**
	 * Singleton
	 * @return the object
	 */
	public static DoorClosedIdleState instance() {
		if(instance == null) {
			instance = new DoorClosedIdleState();			
		}
		return instance;
	}
	
	/**
	 * This will initialize the state
	 */
	@Override
	public void run() {		
		DoorClosedCoolingManager.instance().addDoorClosedCoolingListener(instance);		
		DoorOpenIdleManager.instance().addDoorOpenIdleListener(instance);
		TemperatureUpManager.instance().addTemperatureUpListener(instance);
		Timer.instance().setCoolingRate(0);
		Timer.instance().setWarmingRate(display.getProperty(Settings.FRIDGE_RATE_LOSS_DOOR_CLOSED));
		Timer.instance().resetCompressorTime();
		display.turnFridgeLightOff();		
		display.fridgeIdle();
		
	}

	/**
	 * When the Refrigerator leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		DoorClosedCoolingManager.instance().removeDoorClosedCoolingListener(this);
		DoorOpenIdleManager.instance().removeDoorOpenIdleListener(this);
		TemperatureUpManager.instance().removeTemperatureUpListener(this);
	}
	
	/**
	 * Handles door open event when the refrigerator is idle
	 */
	@Override
	public void doorOpenedIdle(DoorOpenIdleEvent event) {		
		context.changeCurrentState(DoorOpenIdleState.instance());		
	}
	
	/**
	 * Handles door closed event when the refrigerator is cooling
	 */
	@Override
	public void doorClosedCooling(DoorClosedCoolingEvent event) {
		context.changeCurrentState(DoorClosedCoolingState.instance());
	}
	
	/**
	 * Handles temperature going up events
	 */
	@Override
	public void raiseTemp(TemperatureUpEvent event) {
		int temp = context.getCurrentTemperature();
		if (temp < context.getRoomTemperature()){
			context.raiseCurrentTemperature();
			display.updateFridgeTemp(context.getCurrentTemperature());
			if(temp >= (context.getDesiredTemperature() + 
					display.getProperty(Settings.FRIDGE_COMPRESSOR_START_DIFF))){
				DoorClosedCoolingManager.instance().processEvent(new DoorClosedCoolingEvent(instance));
			}
		}		
	}
		
}
