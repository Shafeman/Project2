package P2I1;

import P2I1.GUIDisplay.Settings;

/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
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

	/**
	 * Updates the desired fridge temperature setting
	 */
	@Override
	public void inform(RefrigeratorDisplay source) {
		GUIDisplay display = (GUIDisplay) source;
		int desiredFridgeTemperature = display.getDesiredFridgeTemperature();
		int fridgeLow = display.getProperty(Settings.FRIDGE_LOW);
		int fridgeHigh = display.getProperty(Settings.FRIDGE_HIGH);
		if (desiredFridgeTemperature >= fridgeLow && desiredFridgeTemperature <= fridgeHigh){
			display.instance().context.instance().setDesiredTemperature(desiredFridgeTemperature);
		} else {
			display.resetDesiredFridgeTemperature(display.instance().context.instance().getDesiredTemperature());
		}		
	}

}
