package P2I1;


/**
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

	@Override
	public void inform(RefrigeratorDisplay display) {

//		DoorOpenCoolingManager.instance().processEvent(new DoorOpenCoolingEvent(source));
//		DoorOpenIdleManager.instance().processEvent(new DoorOpenIdleEvent(source));
		
	}
		
}

