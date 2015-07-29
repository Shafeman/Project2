/**
 * Barbarians: Douglas Brian Shaffer, Jonathan Franco
 * Refrigerator is a singleton class that manages the states of the refrigerator.
 * It tracks the fridge and freezer states independently and has methods for processing
 * the clock ticks from the timer thread and the activities of the doors. Works closely
 * with a RefrigeratorDisplay.
 */
package P2I1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Refrigerator {
	public enum States {DOOR_OPEN_IDLE, DOOR_OPEN_COOLING, DOOR_CLOSED_IDLE, DOOR_CLOSED_COOLING};
	private int roomTemperature;
	private int desiredFridgeTemperature;
	private int desiredFreezerTemperature;
	private States fridgeState;
	private States freezerState;
	private int fridgeTemperature;
	private int freezerTemperature;
	private int fridgeDoorClock;
	private int freezerDoorClock;
	private int fridgeCoolingClock;
	private int freezerCoolingClock;
	private static Refrigerator instance;
	private RefrigeratorDisplay display;
	private static Properties defaults;
	private int freezerCoolRate;
	private int freezerClosedWarmRate;
	private int freezerOpenWarmRate;
	private int fridgeCoolRate;
	private int fridgeClosedWarmRate;
	private int fridgeOpenWarmRate;
	
	/**
	 * Constructor
	 */
	private Refrigerator(){
		fridgeState = States.DOOR_CLOSED_COOLING;
		freezerState = States.DOOR_CLOSED_COOLING;
		fridgeDoorClock = 0;
		freezerDoorClock = 0;
		fridgeCoolingClock = 0;
		freezerCoolingClock = 0;
		roomTemperature = Integer.parseInt(defaults.getProperty("RoomHigh"));
		fridgeTemperature = roomTemperature;
		freezerTemperature = roomTemperature;
		desiredFridgeTemperature = Integer.parseInt(defaults.getProperty("FridgeHigh"));
		desiredFreezerTemperature = Integer.parseInt(defaults.getProperty("FreezerHigh"));
		freezerCoolRate = Integer.parseInt(defaults.getProperty("FreezerCoolRate"));
		freezerClosedWarmRate = Integer.parseInt(defaults.getProperty("FreezerRateLossDoorClosed"));
		freezerOpenWarmRate = Integer.parseInt(defaults.getProperty("FreezerRateLossDoorOpen"));
		fridgeCoolRate = Integer.parseInt(defaults.getProperty("FridgeCoolRate"));
		fridgeClosedWarmRate = Integer.parseInt(defaults.getProperty("FridgeRateLossDoorClosed"));
		fridgeOpenWarmRate = Integer.parseInt(defaults.getProperty("FridgeRateLossDoorOpen"));		
		
		display = new GUIDisplay();
		display.setRefrigerator(this);
		display.setStartTemps(roomTemperature, desiredFridgeTemperature, desiredFreezerTemperature);
	}
	
	/**
	 * Main method takes a file from the command line args and uses it to set the 
	 * refrigerator's properties. Also starts a Clock.
	 * @param args
	 */
	public static void main (String[] args){
		if (args.length != 1){
			System.err.println("Please only enter one argument - the full name with the default properties");
		} else {
			String fileName = args[0];
			defaults = new Properties();
			FileReader file;
			try {
				file = new FileReader(fileName);
				BufferedReader in = new BufferedReader(file);
				defaults.load(in);
				file.close();
				in.close();
				new Clock();
			} catch (IOException e) {
				System.err.println(fileName + " not found in system.");
			}			
		}
	}

	/**
	 * returns a refrigerator and ensures the singleton property.
	 * @return
	 */
	public static Refrigerator instance() {
		if (instance ==  null){
			return instance = new Refrigerator();
		}
		return instance;
	}

	/**
	 * Processes a clockTicked message from the Clock based on the states of 
	 * the fridge and freezer. Increments their independent internal timers
	 * for adjusting the temperature and changing cooling/idle states when
	 * necessary.
	 */
	public void clockTicked() {
		/*REMOVE THESE LINES BEFORE TURNING IN*/
		System.out.println("Fridge " + fridgeState + " door counter " + fridgeDoorClock + " cool counter " + fridgeCoolingClock);
		System.out.println("Freezer " + freezerState + " door counter " + freezerDoorClock + " cool counter " + freezerCoolingClock);
		/*REMOVE THE ABOVE LINES BEFORE TURNING IN*/
		switch(fridgeState){
			case DOOR_OPEN_IDLE:
				fridgeDoorClock++;
				if(fridgeDoorClock % fridgeOpenWarmRate == 0){
					fridgeTemperature++;
					display.updateFridgeTemp(fridgeTemperature);
				}
				/////
				if(fridgeTemperature >= roomTemperature){
					fridgeState = States.DOOR_OPEN_COOLING;
					display.fridgeCooling();
				}
				///////
				if(fridgeTemperature >= (desiredFridgeTemperature + 
						Integer.parseInt(defaults.getProperty("FridgeCompressorStartDiff")))){
					fridgeState = States.DOOR_OPEN_COOLING;
					display.fridgeCooling();
				}
				break;
			case DOOR_OPEN_COOLING:
				fridgeDoorClock++;
				fridgeCoolingClock++;
				if(fridgeCoolingClock % fridgeCoolRate == 0){
					fridgeTemperature--;
					display.updateFridgeTemp(fridgeTemperature);
				}
				if(fridgeDoorClock % fridgeClosedWarmRate == 0){
					fridgeTemperature++;
					display.updateFridgeTemp(fridgeTemperature);
				}
				if(fridgeTemperature <= desiredFridgeTemperature){
					fridgeState = States.DOOR_OPEN_IDLE;
					fridgeCoolingClock = 0;
					display.fridgeIdle();
				}
				break;
			case DOOR_CLOSED_COOLING:
				fridgeCoolingClock++;
				fridgeDoorClock++;
				if(fridgeCoolingClock % fridgeCoolRate == 0){
					fridgeTemperature--;
					display.updateFridgeTemp(fridgeTemperature);
				}
				if(fridgeDoorClock % fridgeClosedWarmRate == 0){
					fridgeTemperature++;
					display.updateFridgeTemp(fridgeTemperature);
				}
				if(fridgeTemperature <= desiredFridgeTemperature){
					fridgeState = States.DOOR_CLOSED_IDLE;
					fridgeCoolingClock = 0;
					display.fridgeIdle();
				}
				break;
			case DOOR_CLOSED_IDLE:
				fridgeDoorClock++;
				if(fridgeDoorClock % fridgeClosedWarmRate == 0){
					fridgeTemperature++;
					display.updateFridgeTemp(fridgeTemperature);
				}
				////
				if(fridgeTemperature >= roomTemperature){
					fridgeState = States.DOOR_CLOSED_COOLING;
					display.fridgeCooling();
				}
				////
				if(fridgeTemperature >= (desiredFridgeTemperature + 
						Integer.parseInt(defaults.getProperty("FridgeCompressorStartDiff")))){
					fridgeState = States.DOOR_CLOSED_COOLING;
					display.fridgeCooling();
				}
				break;
			default:
				break;
		}
		
		switch(freezerState){
			case DOOR_OPEN_IDLE:
				freezerDoorClock++;
				if(freezerDoorClock % freezerOpenWarmRate == 0){
					freezerTemperature++;
					display.updateFreezerTemp(freezerTemperature);
				}
				//////
				if(freezerTemperature >= roomTemperature){
					fridgeState = States.DOOR_OPEN_COOLING;
					display.freezerCooling();
				}
				//////
				if(freezerTemperature >= (desiredFreezerTemperature + 
						Integer.parseInt(defaults.getProperty("FreezerCompressorStartDiff")))){
					freezerState = States.DOOR_OPEN_COOLING;
					display.freezerCooling();
				}
				break;
			case DOOR_OPEN_COOLING:
				freezerDoorClock++;
				freezerCoolingClock++;
				if(freezerCoolingClock % freezerCoolRate == 0){
					freezerTemperature--;
					display.updateFreezerTemp(freezerTemperature);
				}
				if(freezerDoorClock % freezerOpenWarmRate == 0){
					freezerTemperature++;
					display.updateFreezerTemp(freezerTemperature);
				}
				if(freezerTemperature <= desiredFreezerTemperature){
					freezerState = States.DOOR_OPEN_IDLE;
					freezerCoolingClock = 0;
					display.freezerIdle();
				}
				break;
			case DOOR_CLOSED_COOLING:
				freezerCoolingClock++;
				freezerDoorClock++;
				if(freezerCoolingClock % freezerCoolRate == 0){
					freezerTemperature--;
					display.updateFreezerTemp(freezerTemperature);
				}
				if(freezerDoorClock % freezerClosedWarmRate == 0){
					freezerTemperature++;
					display.updateFreezerTemp(freezerTemperature);
				}
				if(freezerTemperature <= desiredFreezerTemperature){
					freezerState = States.DOOR_CLOSED_IDLE;
					freezerCoolingClock = 0;
					display.freezerIdle();
				}
				break;
			case DOOR_CLOSED_IDLE:
				freezerDoorClock++;
				if(freezerDoorClock % freezerClosedWarmRate == 0){
					freezerTemperature++;
					display.updateFreezerTemp(freezerTemperature);
				}
				///////
				if(freezerTemperature >= roomTemperature){
					fridgeState = States.DOOR_CLOSED_COOLING;
					display.freezerCooling();
				}
				///////
				if(freezerTemperature >= (desiredFreezerTemperature + 
						Integer.parseInt(defaults.getProperty("FreezerCompressorStartDiff")))){
					freezerState = States.DOOR_CLOSED_COOLING;
					display.freezerCooling();
				}
				break;
			default:
				break;
		}	
	}
	

	/**
	 * @return the roomTemp
	 */
	public int getRoomTemperature() {
		return roomTemperature;
	}

	/**
	 * @param roomTemp the roomTemp to set
	 */
	public void setRoomTemperature(int roomTemp) {
		this.roomTemperature = roomTemp;
		if (roomTemperature < fridgeTemperature){
			this.fridgeTemperature = roomTemp;
			display.updateFridgeTemp(roomTemp);
		}
		if (roomTemperature < freezerTemperature){
			this.freezerTemperature = roomTemp;
			display.updateFreezerTemp(roomTemp);
		}
	}

	/**
	 * @return the desiredFridgeTemperature
	 */
	public int getDesiredFridgeTemperature() {
		return desiredFridgeTemperature;
	}

	/**
	 * @param desiredFridgeTemperature the desiredFridgeTemperature to set
	 */
	public void setDesiredFridgeTemperature(int desiredFridgeTemperature) {
		this.desiredFridgeTemperature = desiredFridgeTemperature;
	}

	/**
	 * @return the desiredFreezerTemperature
	 */
	public int getDesiredFreezerTemperature() {
		return desiredFreezerTemperature;
	}

	/**
	 * @param desiredFreezerTemperature the desiredFreezerTemperature to set
	 */
	public void setDesiredFreezerTemperature(int desiredFreezerTemperature) {
		this.desiredFreezerTemperature = desiredFreezerTemperature;
	}

	/**
	 * Turns on the fridge light and changes state appropriately if the fridge door
	 * is not already open.
	 */
	public void processOpenFridgeDoor() {
		display.turnFridgeLightOn();
		switch(fridgeState){
			case DOOR_CLOSED_IDLE:
				fridgeDoorClock = 0;
				fridgeState = States.DOOR_OPEN_IDLE;
				break;
			case DOOR_CLOSED_COOLING:
				fridgeDoorClock = 0;
				fridgeState = States.DOOR_OPEN_COOLING;
				break;
			default:
				break;
		}
	}

	/**Turns on the freezer light and changes state appropriately if the freezer door
	 * is not already open.
	 * 
	 */
	public void processOpenFreezerDoor() {
		display.turnFreezerLightOn();
		switch(freezerState){
		case DOOR_CLOSED_IDLE:
			freezerDoorClock = 0;
			freezerState = States.DOOR_OPEN_IDLE;
			break;
		case DOOR_CLOSED_COOLING:
			freezerDoorClock = 0;
			freezerState = States.DOOR_OPEN_COOLING;
			break;
		default:
			break;
		}		
	}

	/**
	 * Turns off the fridge light and changes state appropriately if the fridge door
	 * is not already closed.
	 */
	public void processCloseFridgeDoor() {
		display.turnFridgeLightOff();
		switch(fridgeState){
		case DOOR_OPEN_IDLE:
			fridgeDoorClock = 0;
			fridgeState = States.DOOR_CLOSED_IDLE;
			break;
		case DOOR_OPEN_COOLING:
			fridgeDoorClock = 0;
			fridgeState = States.DOOR_CLOSED_COOLING;
			break;
		default:
			break;
		}		
	}

	/**Turns off the freezer light and changes state appropriately if the freezer door
	 * is not already closed.
	 * 
	 */
	public void processCloseFreezerDoor() {
		display.turnFreezerLightOff();
		switch(freezerState){
		case DOOR_OPEN_IDLE:
			freezerDoorClock = 0;
			freezerState = States.DOOR_CLOSED_IDLE;
			break;
		case DOOR_OPEN_COOLING:
			freezerDoorClock = 0;
			freezerState = States.DOOR_CLOSED_COOLING;
			break;
		default:
			break;
		}		
	}
	
	/**
	 * Returns true if the temp is in between the refrigerator's default room temp
	 * settings
	 * @param temp
	 * @return
	 */
	public boolean checkValidRoomTemperature(int temp){
		int roomLow = Integer.parseInt(defaults.getProperty("RoomLow"));
		int roomHigh = Integer.parseInt(defaults.getProperty("RoomHigh"));
		if(temp <= roomHigh && temp >= roomLow){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the temp is in between the refrigerator's default fridge temp
	 * settings
	 * @param temp
	 * @return
	 */
	public boolean checkValidFridgeTemperature(int temp){
		int fridgeLow = Integer.parseInt(defaults.getProperty("FridgeLow"));
		int fridgeHigh = Integer.parseInt(defaults.getProperty("FridgeHigh"));
		if(temp <= fridgeHigh && temp >= fridgeLow){
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the temp is in between the refrigerator's default freezer temp
	 * settings
	 * @param temp
	 * @return
	 */
	public boolean checkValidFreezerTemperature(int temp){
		int freezerLow = Integer.parseInt(defaults.getProperty("FreezereLow"));
		int freezerHigh = Integer.parseInt(defaults.getProperty("FreezerHigh"));
		if(temp <= freezerHigh && temp >= freezerLow){
			return true;
		}
		return false;
	}
}
