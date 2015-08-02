package P2I1;
/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * State of the Refrigerator when the door is closed and idle.
 */

public class DoorClosedCoolingState extends RefrigeratorState 
implements DoorClosedIdleListener, DoorOpenCoolingListener{

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
}
