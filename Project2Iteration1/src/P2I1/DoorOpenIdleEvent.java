package P2I1;

import java.util.EventObject;
/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * Door close request
 */

@SuppressWarnings("serial")
public class DoorOpenIdleEvent extends EventObject {
	public DoorOpenIdleEvent(Object source) {
		super(source);
	}

}
