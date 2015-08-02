package P2I1;



/**
 * State of the Refrigerator when the door is open and cooling.
 * When the Refrigerator is cooling and the door is opened, the
 * run method of this class is called. Then when an event occurs, 
 * the handle method is invoked.
 *
 */
public class DoorOpenCoolingState extends RefrigeratorState 
	implements DoorClosedCoolingListener, DoorOpenIdleListener{
	
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
		display.turnFridgeLightOn();
		display.fridgeCooling();
		//display.doorOpen();
		
	}

	/**
	 * When the Refrigerator leaves from this state, this method is called to
	 * remove the state as a listener for the appropriate events.
	 */
	@Override
	public void leave() {
		DoorClosedCoolingManager.instance().removeDoorClosedCoolingListener(this);
		DoorOpenIdleManager.instance().removeDoorOpenIdleListener(this);
		
	}

	/**
	 * Handles door closed event while the refrigerator is cooling
	 */
	@Override
	public void doorClosedCooling(DoorClosedCoolingEvent event) {
		context.changeCurrentState(DoorClosedCoolingState.instance);
		
	}
	
	/**
	 * Handles door open event when the refrigerator is idle
	 */
	@Override
	public void doorOpenedIdle(DoorOpenIdleEvent event) {
		context.changeCurrentState(DoorOpenIdleState.instance);
		
	}

}
