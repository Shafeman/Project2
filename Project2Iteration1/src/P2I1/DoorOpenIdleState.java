package P2I1;
/**
* Barbarians: Douglas Brian Shaffer, Johnathan Franco
* State of the Refrigerator when the door is open and idle.
*/

public class DoorOpenIdleState extends RefrigeratorState 
implements DoorOpenCoolingListener, DoorClosedIdleListener{

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

}
