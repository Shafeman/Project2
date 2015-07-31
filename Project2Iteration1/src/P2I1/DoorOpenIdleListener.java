package P2I1;

import java.util.EventListener;

public interface DoorOpenIdleListener extends EventListener {
	
	public void doorOpenedIdle(DoorOpenIdleEvent event);

}
