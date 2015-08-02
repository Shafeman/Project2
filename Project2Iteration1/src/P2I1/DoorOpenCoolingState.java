package P2I1;

import P2I1.GUIDisplay.Settings;



/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * State of the Refrigerator when the door is open and cooling.
 * When the Refrigerator is cooling and the door is opened, the
 * run method of this class is called. Then when an event occurs, 
 * the handle method is invoked.
 *
 */
public class DoorOpenCoolingState extends RefrigeratorState 
	implements DoorClosedCoolingListener, DoorOpenIdleListener,
	TemperatureUpListener, TemperatureDownListener{
	
	private static DoorOpenCoolingState instance;
	
	/**
	 * Singleton
	 */
	private DoorOpenCoolingState() {
		
	}
	/**
	 * Singleton
	 * @return the object
	 */
	public static DoorOpenCoolingState instance() {
		if(instance == null) {
			instance = new DoorOpenCoolingState();			
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
		TemperatureDownManager.instance().addTemperatureDownListener(instance);
		Timer.instance().setCoolingRate(display.getProperty(Settings.FRIDGE_COOL_RATE));
		Timer.instance().setWarmingRate(display.getProperty(Settings.FRIDGE_RATE_LOSS_DOOR_OPEN));
		Timer.instance().resetCompressorTime();
		display.turnFridgeLightOn();
		display.fridgeCooling();
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
		TemperatureDownManager.instance().removeTemperatureDownListener(this);			
	}

	/**
	 * Handles door closed event while the refrigerator is cooling
	 */
	@Override
	public void doorClosedCooling(DoorClosedCoolingEvent event) {
		context.changeCurrentState(DoorClosedCoolingState.instance());
		
	}
	
	/**
	 * Handles door open event when the refrigerator is idle
	 */
	@Override
	public void doorOpenedIdle(DoorOpenIdleEvent event) {
		context.changeCurrentState(DoorOpenIdleState.instance());
		
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
			DoorOpenIdleManager.instance().processEvent(new DoorOpenIdleEvent(instance));
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
