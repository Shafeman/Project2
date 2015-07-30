package P2I1;

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
	
	
	@Override
	public void DoorClosedIdle(DoorClosedIdleEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DoorOpenCooling(DoorOpenCoolingEvent event) {
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
