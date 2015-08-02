package P2I1;

import P2I1.GUIDisplay.Settings;

/**
* Barbarians: Douglas Brian Shaffer, Johnathan Franco
* State of the Refrigerator when the door is open and idle.
*/

public class DoorOpenIdleState extends RefrigeratorState 
	implements DoorOpenCoolingListener, DoorClosedIdleListener,
	TemperatureUpListener{

	public static DoorOpenIdleState instance;
	
	/**
	 * Singleton
	 */
	private DoorOpenIdleState() {
		
	}
	/**
	 * Singleton
	 * @return the object
	 */
	public static DoorOpenIdleState instance() {
		if(instance == null) {
			instance = new DoorOpenIdleState();			
		}
		return instance;
	}

	/**
	 * This will initialize the state
	 */
	@Override
	public void run() {
		DoorOpenCoolingManager.instance().addDoorOpenCoolingListener(instance);
		DoorClosedIdleManager.instance().addDoorClosedIdleListener(instance);
		TemperatureUpManager.instance().addTemperatureUpListener(instance);
		Timer.instance().setCoolingRate(0);
		Timer.instance().setWarmingRate(display.getProperty(Settings.FRIDGE_RATE_LOSS_DOOR_OPEN));
		Timer.instance().resetCompressorTime();
		display.turnFridgeLightOn();
		display.fridgeIdle();
	}

	/**
	 * When the Refrigerator leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		DoorOpenCoolingManager.instance().removeDoorOpenCoolingListener(this);
		DoorClosedIdleManager.instance().removeDoorClosedIdleListener(this);
		TemperatureUpManager.instance().removeTemperatureUpListener(this);
	}

	/**
	 * Handles door closed event when the refrigerator is idle
	 */
	@Override
	public void doorClosedIdle(DoorClosedIdleEvent event) {
		context.changeCurrentState(DoorClosedIdleState.instance());
	}
	
	/**
	 * Handles door open event when the refrigerator is cooling
	 */
	@Override
	public void doorOpenedCooling(DoorOpenCoolingEvent event) {
		context.changeCurrentState(DoorOpenCoolingState.instance());
		
	}
	
	/**
	 * Handles temperature raising events
	 */
	@Override
	public void raiseTemp(TemperatureUpEvent event) {
		int temp = context.getCurrentTemperature();
		if (temp < context.getRoomTemperature()){
			context.raiseCurrentTemperature();
			temp++;
			display.updateFridgeTemp(temp);
			if(temp >= (context.getDesiredTemperature() + 
					display.getProperty(Settings.FRIDGE_COMPRESSOR_START_DIFF))){
				DoorOpenCoolingManager.instance().processEvent(new DoorOpenCoolingEvent(instance));
			}
		}
	}

}
