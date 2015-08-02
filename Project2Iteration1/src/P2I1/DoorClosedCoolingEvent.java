package P2I1;

import java.util.EventObject;

/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * Door close cooling request
 */

@SuppressWarnings("serial")
public class DoorClosedCoolingEvent extends EventObject {
	
	public DoorClosedCoolingEvent(Object source) {
		super(source);
	}

}
