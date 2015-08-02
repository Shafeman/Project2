package P2I1;

import java.util.EventObject;

/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * Door close idle request
 */
@SuppressWarnings("serial")
public class DoorClosedIdleEvent extends EventObject {
	
	public DoorClosedIdleEvent(Object source) {
		super(source);
	}

}
