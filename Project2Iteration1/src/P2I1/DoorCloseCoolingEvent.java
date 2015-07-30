package P2I1;

import java.util.EventObject;

/**
 * Door close request
 */

@SuppressWarnings("serial")
public class DoorCloseCoolingEvent extends EventObject {
	
	public DoorCloseCoolingEvent(Object source) {
		super(source);
	}

}
