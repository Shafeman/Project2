package P2I1;

/**
 * Super class for all states
 */
public abstract class RefrigeratorState {
	protected static RefrigeratorContext context;
	protected static RefrigeratorDisplay display;
	
	/**
	 * Initialize the context and display
	 */
	protected RefrigeratorState() {
		
		context = RefrigeratorContext.instance();
		display = context.getDisplay();
		
	}
	
	/**
	 * initializes the state
	 */
	public abstract void run();
	
	/**
	 * When the Refrigerator leaves from this state, this method is called
	 * to remove the state as a listener for the appropriate events.
	 */
	public abstract void leave();

}
