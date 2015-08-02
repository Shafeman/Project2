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
	

	@Override
	public void run() {
		DoorClosedCoolingManager.instance().addDoorClosedCoolingListener(instance);
		DoorOpenIdleManager.instance().addDoorOpenIdleListener(instance);
		display.turnFridgeLightOn();
		//display.doorOpen();
		
	}

	@Override
	public void leave() {
		DoorClosedCoolingManager.instance().removeDoorClosedCoolingListener(this);
		DoorOpenIdleManager.instance().removeDoorOpenIdleListener(this);
		
	}

	@Override
	public void doorClosedCooling(DoorClosedCoolingEvent event) {
		context.changeCurrentState(DoorClosedCoolingState.instance);
		
	}
	
	@Override
	public void doorOpenedIdle(DoorOpenIdleEvent event) {
		context.changeCurrentState(DoorOpenIdleState.instance);
		
	}

}
