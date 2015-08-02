package P2I1;

/**
 * Represents a button to set the desired fridge
 * temperature.
 *
 */
@SuppressWarnings("serial")
public class SetDesiredFridgeTempButton extends GUIButton {
	
	/**
	 * The button
	 * @param string 
	 * 				name of the button
	 */
	public SetDesiredFridgeTempButton(String string) {
		super(string);
	}

	@Override
	public void inform(RefrigeratorDisplay source) {
		
		
	}

}
