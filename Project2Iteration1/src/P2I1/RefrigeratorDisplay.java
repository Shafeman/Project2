/**
 * Barbarians: Douglas Brian Shaffer, Jonathan Franco
 * The RefrigeratorDisplay is an interface for the GUIDisplay to implement.
 * It defines methods for setting the Refrigerator, controlling the lights
 * and notifying the GUI of the state and temperatures.
 */
package P2I1;

public interface RefrigeratorDisplay {
	
	public void setRefrigerator(Refrigerator refrigerator);
	public void turnFridgeLightOn();
	public void turnFridgeLightOff();
	public void turnFreezerLightOn();
	public void turnFreezerLightOff();
	public void freezerCooling();
	public void freezerIdle();
	public void fridgeCooling();
	public void fridgeIdle();
	public void updateFridgeTemp(int temp);
	public void updateFreezerTemp(int temp);
	public void setStartTemps(int room, int fridge, int freezer);

}
