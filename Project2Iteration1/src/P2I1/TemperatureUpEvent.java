package P2I1;

import java.util.EventObject;

/**
 * Barbarians: Douglas Brian Shaffer, Johnathan Franco
 * Door close cooling request
 */

@SuppressWarnings("serial")
public class TemperatureUpEvent extends EventObject {
	
	public TemperatureUpEvent(Object source) {
		super(source);
	}

}