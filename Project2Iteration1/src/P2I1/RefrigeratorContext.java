package P2I1;

public class RefrigeratorContext {
	private static RefrigeratorDisplay refrigeratorDisplay;
	private RefrigeratorState currentState;
	private static Timer timer;
	private static RefrigeratorContext instance;
	
	/**
	 * Singleton
	 */
	private RefrigeratorContext() {
		instance = this;
		refrigeratorDisplay = RefrigeratorDisplay.instance();
		currentState = DoorClosedCoolingState.instance();
		timer = Timer.instance();
	}
	
	/**
	 * Return the instance
	 * 
	 * @return RefrigeratorContext()
	 */
	public static RefrigeratorContext instance() {
		if (instance == null) {
			instance = new RefrigeratorContext();
		}
		return instance;
	}
	
	public void initialize() {
		instance.changeCurrentState(DoorClosedCoolingState.instance());
	}
	
	public void changeCurrentState(RefrigeratorState nextState) {
		currentState.leave();
		currentState = nextState;
		nextState.run();
		
	}
	
	public RefrigeratorDisplay getDisplay() {
		return refrigeratorDisplay;
	}
	
	public RefrigeratorState getCurrentState() {
		return currentState;		
	}
	
}
