package P2I1;

import java.util.EventListener;

public interface DoorOpenCoolingListener extends EventListener{

	public void doorOpenedCooling(DoorOpenIdleEvent event);
	
}
