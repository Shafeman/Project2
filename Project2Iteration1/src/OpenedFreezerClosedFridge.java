/**
 * Created by Nathan on 7/12/2015.
 */
public class OpenedFreezerClosedFridge implements RefrigeratorState {

    RefrigeratorMachine refrigeratorMachine;

    public OpenedFreezerClosedFridge(RefrigeratorMachine refrigeratorMachine) {

        this.refrigeratorMachine = refrigeratorMachine;
    }

    @Override
    public void openFreezer() {

        System.out.println("Freezer is already open");
    }

    @Override
    public void openFridge() {

        System.out.println("Closing Fridge");
        refrigeratorMachine.setRefrigeratorState(refrigeratorMachine.getOpenedFreezerOpenedFridge());
    }

    @Override
    public void closeFreezer() {

        System.out.println("Closing Freezer");
        refrigeratorMachine.setRefrigeratorState(refrigeratorMachine.getClosedFreezerClosedFridge());
    }

    @Override
    public void closeFridge() {

        System.out.println("Fridge is already closed");
    }
}
