package P2I1;


/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * Represents the Door Open button
 *
 */
@SuppressWarnings("serial")
public class DoorOpenButton extends GUIButton{
	
	/**
	 * The Button
	 * @param string
	 * 				the name of the button
	 */
	public DoorOpenButton(String string) {
		super(string);
	}

	/**
	 * Depending upon the current state this method
	 * will change the current state to the correct
	 * door open state
	 */
	@Override
	public void inform(RefrigeratorDisplay source) {

		if(GUIDisplay.context.getCurrentState() instanceof DoorClosedCoolingState) {
			DoorOpenCoolingManager.instance().processEvent(new DoorOpenCoolingEvent(source));
		}
		
		if(GUIDisplay.context.getCurrentState() instanceof DoorClosedIdleState) {
			DoorOpenIdleManager.instance().processEvent(new DoorOpenIdleEvent(source));
		
		}		
	}		
}

