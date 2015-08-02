package P2I1;

import P2I1.GUIDisplay.Settings;


/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * Represents the button to set the room
 * temperature.
 *
 */
@SuppressWarnings("serial")
public class SetRoomTempButton extends GUIButton {

	/**
	 * The button
	 * 
	 * @param string
	 * 
	 * 				name of the button
	 */
	public SetRoomTempButton(String string) {
		super(string);
	}

	/**
	 * Updates the room temperature so the system can
	 * act appropriately.
	 */
	@Override
	public void inform(RefrigeratorDisplay source) {
		GUIDisplay display = (GUIDisplay) source;
		int roomTemperature = display.getRoomTemperatureSetting();
		int roomLow = display.getProperty(Settings.ROOM_LOW);
		int roomHigh = display.getProperty(Settings.ROOM_HIGH);
		if (roomTemperature >= roomLow && roomTemperature <= roomHigh){
			display.instance().context.instance().setRoomTemperature(roomTemperature);
		} else {
			display.resetRoomTemperatureDisplay(display.instance().context.instance().getRoomTemperature());
		}
		if (roomTemperature <= display.instance().context.instance().getCurrentTemperature()){
			display.updateFridgeTemp(roomTemperature);
		}
	}
}
