package P2I1;


/**
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

	@Override
	public void inform(RefrigeratorDisplay source) {
		GUIDisplay display = (GUIDisplay) source;
		int roomTemperature = display.getRoomTemperatureSetting();
		int roomLow = display.getProperty("RoomLow");
		int roomHigh = display.getProperty("RoomHigh");
		if (roomTemperature >= roomLow && roomTemperature <= roomHigh){
			Timer.instance().setRoomTemperature(display.getRoomTemperatureSetting());
		} else {
			display.resetRoomTemperatureDisplay(Timer.instance().getRoomTemperature());
		}
	}
}
