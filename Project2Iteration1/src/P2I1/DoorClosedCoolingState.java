package P2I1;

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
	

	@Override
	public void run() {
		DoorClosedIdleManager.instance().addDoorClosedIdleListener(instance);
		DoorOpenCoolingManager.instance().addDoorOpenCoolingListener(instance);
		display.fridgeCooling();
		display.turnFridgeLightOff();
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doorOpenedCooling(DoorOpenCoolingEvent event) {
		context.changeCurrentState(DoorOpenCoolingState.instance());
		
	}
	@Override
	public void doorClosedIdle(DoorClosedIdleEvent event) {
		context.changeCurrentState(DoorClosedIdleState.instance());
		
	}
}
