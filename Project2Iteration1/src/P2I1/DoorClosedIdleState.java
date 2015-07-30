package P2I1;

/**
 * State of the Refrigerator when the door is closed and idle.
 *
 */

public class DoorClosedIdleState extends RefrigeratorState 
implements DoorClosedCoolingListener, DoorOpenIdleListener{
	
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

	@Override
	public void doorOpenedIdle(DoorOpenIdleEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doorClosedCooling(DoorCloseCoolingEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leave() {
		// TODO Auto-generated method stub
		
	}

}
