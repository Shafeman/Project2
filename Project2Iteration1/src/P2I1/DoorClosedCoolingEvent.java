package P2I1;

import java.util.EventObject;

/**
 * Door close request
 */

@SuppressWarnings("serial")
public class DoorClosedCoolingEvent extends EventObject {
	
	public DoorClosedCoolingEvent(Object source) {
		super(source);
	}

}
