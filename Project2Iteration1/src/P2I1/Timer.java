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
	private int warmingRate;

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
	 * sets rate that fridge is currently warming
	 * @param rate
	 * @return
	 */
	public void setWarmingRate(int rate){
		this.warmingRate = rate;
	}
	
	/**
	 * Sets the unit's current cooling rate
	 * @param rate
	 */
	public void setCoolingRate(int rate){
		this.coolingRate = rate;
	}

	/**
	 * Get the clock tick and process it
	 */
	@Override
	public void update(Observable clock, Object value) {
		doorTime++;
		if(coolingRate != 0){
			compressorTime++;
		}
		if(doorTime % warmingRate == 0){
			TemperatureUpManager.instance().processEvent(new TemperatureUpEvent(instance));
		}
		if(coolingRate != 0 && compressorTime % coolingRate == 0){
			TemperatureDownManager.instance().processEvent(new TemperatureDownEvent(instance));
		}

	}
}