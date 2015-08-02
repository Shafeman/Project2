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
		GUIDisplay display = (GUIDisplay) source;
		int desiredFridgeTemperature = display.getDesiredFridgeTemperature();
		int fridgeLow = display.getProperty("FridgeLow");
		int fridgeHigh = display.getProperty("FridgeHigh");
		if (desiredFridgeTemperature >= fridgeLow && desiredFridgeTemperature <= fridgeHigh){
			Timer.instance().setDesiredFridgeTemperature(display.getDesiredFridgeTemperature());
		} else {
			display.resetDesiredFridgeTemperature(Timer.instance().getDesiredFridgeTemperature());
		}		
	}

}
