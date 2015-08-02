package P2I1;

/**
 * Represents the close door button
 */

@SuppressWarnings("serial")
public class DoorCloseButton extends GUIButton{
	
	private DoorOpenCoolingState doorOpenCooling;
	private DoorOpenIdleState doorOpenIdle;
	
	/**
	 * The button
	 * 
	 * @param string
	 * 				The name of the button
	 */
	public DoorCloseButton(String string) {
		super(string);
	}

	@Override
	public void inform(RefrigeratorDisplay source) {
		
		if(GUIDisplay.context.getCurrentState().equals(doorOpenCooling)) {
			DoorClosedCoolingManager.instance().processEvent(new DoorClosedCoolingEvent(source));
		}
		if(GUIDisplay.context.getCurrentState().equals(doorOpenIdle)) {
			DoorClosedIdleManager.instance().processEvent(new DoorClosedIdleEvent(source));
		}		
	}
	
	
}
