/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * Represents the close door button
 */
package P2I1;

@SuppressWarnings("serial")
public class DoorCloseButton extends GUIButton{
	
	/**
	 * The button
	 * 
	 * @param string
	 * 				The name of the button
	 */
	public DoorCloseButton(String string) {
		super(string);
	}

	/**
	 * Depending upon the current state, this method 
	 * will change the current state to the correct 
	 * closed door state
	 */
	@Override
	public void inform(RefrigeratorDisplay source) {
		
		if(GUIDisplay.context.getCurrentState() instanceof DoorOpenCoolingState) {
			System.out.println("Door Closed Cooling");
			DoorClosedCoolingManager.instance().processEvent(new DoorClosedCoolingEvent(source));
		}
		if(GUIDisplay.context.getCurrentState() instanceof DoorOpenIdleState) {
			System.out.println("Door Closed Idle");
			DoorClosedIdleManager.instance().processEvent(new DoorClosedIdleEvent(source));
		}		
	}
	
	
}
