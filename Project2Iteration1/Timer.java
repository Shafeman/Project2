/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 *  The Timer for the microwave
 */
package P2I1;

import java.util.Observable;
import java.util.Observer;
import P2I1.GUIDisplay.Settings;

public class Timer implements Observer {
	private static Timer instance;
	private static RefrigeratorDisplay display;
	private int doorTime;
	private int compressorTime;
	private int openWarmingRate;
	private int closedWarmingRate;
	private int coolingRate;
	private int roomTemperature;
	private int fridgeTemperature;
	private int desiredFridgeTemperature;
	private int fridgeDifferential;

	/**
	 * For singleton
	 */
	private Timer() {
		instance = this;
		display = RefrigeratorDisplay.instance();
		Clock.instance().addObserver(instance);
		coolingRate = display.getProperty(Settings.FRIDGE_COOL_RATE);
		openWarmingRate = display.getProperty(Settings.FRIDGE_RATE_LOSS_DOOR_OPEN);
		closedWarmingRate = display.getProperty(Settings.FRIDGE_RATE_LOSS_DOOR_CLOSED);
		roomTemperature = display.getProperty(Settings.ROOM_HIGH);
		fridgeTemperature = display.getProperty(Settings.ROOM_HIGH);
		desiredFridgeTemperature = display.getProperty(Settings.FRIDGE_HIGH);
		fridgeDifferential = display.getProperty(Settings.FRIDGE_COMPRESSOR_START_DIFF);
		compressorTime = 0;
		doorTime = 0;
	}

	/**
	 * For singleton pattern
	 * 
	 * @return the instance
	 */
	public static Timer instance() {
		if (instance == null) {
			instance = new Timer();
		}
		return instance;
	}

	/**
	 * Set the time for the door timer
	 */
	public void resetDoorTime() {
		this.doorTime = 0;
	}
	
	/**
	 * Set the time for the compressor timer
	 */
	public void resetCompressorTime(){
		this.compressorTime = 0;
	}
	
	/**
	 * Sets the room Temperature
	 * @param temp
	 */
	public void setRoomTemperature(int temp){
		this.roomTemperature = temp;
		if(temp < fridgeTemperature){
			this.fridgeTemperature = temp;
			display.updateFridgeTemp(fridgeTemperature);
		}
	}
	
	/**
	 * Sets the desired fridge temperature
	 * @param temp
	 */
	public void setDesiredFridgeTemperature(int temp){
		this.desiredFridgeTemperature = temp;
	}
	
	/**
	 * gets room temperature
	 * @return
	 */
	public int getRoomTemperature(){
		return roomTemperature;
	}
	
	/**
	 * gets desired fridge temperature
	 * @return
	 */
	public int getDesiredFridgeTemperature(){
		return desiredFridgeTemperature;
	}

	/**
	 * Get the clock tick and process it
	 */
	@Override
	public void update(Observable clock, Object value) {		
		if (GUIDisplay.context.getCurrentState() instanceof DoorClosedCoolingState){
			compressorTime++;
			doorTime++;
			if(compressorTime % coolingRate == 0){
				fridgeTemperature--;
				display.updateFridgeTemp(fridgeTemperature);
			}
			if(doorTime % closedWarmingRate == 0){
				if(fridgeTemperature < roomTemperature){
					fridgeTemperature++;
					display.updateFridgeTemp(fridgeTemperature);
				}
			}
			if(fridgeTemperature <= desiredFridgeTemperature){
				DoorClosedIdleManager.instance().processEvent(new DoorClosedIdleEvent(instance));
				this.resetCompressorTime();
			}
		} else if (GUIDisplay.context.getCurrentState() instanceof DoorClosedIdleState){
			doorTime++;
			if(doorTime % closedWarmingRate == 0){
				if(fridgeTemperature < roomTemperature){
					fridgeTemperature++;
					display.updateFridgeTemp(fridgeTemperature);
				}
			}
			if(fridgeTemperature >= (desiredFridgeTemperature + fridgeDifferential)){
				DoorClosedCoolingManager.instance().processEvent(new DoorClosedCoolingEvent(instance));
			}
		} else if (GUIDisplay.context.getCurrentState() instanceof DoorOpenCoolingState){
			doorTime++;
			compressorTime++;
			if(compressorTime % coolingRate == 0){
				fridgeTemperature--;
				display.updateFridgeTemp(fridgeTemperature);
			}
			if(doorTime % openWarmingRate == 0){
				if(fridgeTemperature < roomTemperature){
					fridgeTemperature++;
					display.updateFridgeTemp(fridgeTemperature);
				}
			}
			if(fridgeTemperature <= desiredFridgeTemperature){
				DoorOpenIdleManager.instance().processEvent(new DoorOpenIdleEvent(instance));
				this.resetCompressorTime();
			}
		} else if (GUIDisplay.context.getCurrentState() instanceof DoorOpenIdleState){
			doorTime++;
			if(doorTime % openWarmingRate == 0){
				if(fridgeTemperature < roomTemperature){
					fridgeTemperature++;
					display.updateFridgeTemp(fridgeTemperature);
				}
			}
			if(fridgeTemperature >= (desiredFridgeTemperature + fridgeDifferential)){
				DoorOpenCoolingManager.instance().processEvent(new DoorOpenCoolingEvent(instance));
				this.resetCompressorTime();
			}
		}

	}
}
