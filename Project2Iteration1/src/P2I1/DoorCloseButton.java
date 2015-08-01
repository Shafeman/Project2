package P2I1;

/**
 * Represents the close door button
 */

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

	@Override
	public void inform(RefrigeratorDisplay source) {
		
//		DoorClosedCoolingManager.instance().processEvent(new DoorClosedCoolingEvent(source));
//		DoorClosedIdleManager.instance().processEvent(new DoorClosedIdleEvent(source));
		
	}
	
	
}
