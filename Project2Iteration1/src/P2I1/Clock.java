/**
 * Barbarians: Douglas Brian Shaffer, Jonathan Franco
 * Implements Runnable and creates a Clock that calls refrigerator.clockTicked()
 * every second.
 */
package P2I1;

public class Clock implements Runnable {
	private static Refrigerator refrigerator;
	
	/**
	 * Constructor
	 */
	public Clock(){
		refrigerator = Refrigerator.instance();
		new Thread(this).start();
	}
	
	/**
	 * calls refrigerator.clockTicked() ever second
	 */
	@Override
	public void run() {
		try{
			while (true){
				Thread.sleep(1000);
				refrigerator.clockTicked();
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
