package P2I1;

import java.util.Observable;
import java.util.Observer;

/**
 * The Timer for the microwave
 *
 */
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
		coolingRate = display.getProperty("FridgeCoolRate");
		openWarmingRate = display.getProperty("FridgeRateLossDoorOpen");
		closedWarmingRate = display.getProperty("FridgeRateLossDoorClosed");
		roomTemperature = display.getProperty("RoomHigh");
		fridgeTemperature = display.getProperty("RoomHigh");
		desiredFridgeTemperature = display.getProperty("FridgeHigh");
		fridgeDifferential = display.getProperty("FridgeCompressorStartDiff");
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
				display.fridgeIdle();
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
				display.fridgeCooling();
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
				display.fridgeIdle();
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
				display.fridgeCooling();
			}
		}

	}
}
