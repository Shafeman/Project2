package P2I1;

public class ClosedFreezerOpenedFridge implements RefrigeratorState {

    RefrigeratorMachine refrigeratorMachine;

    public ClosedFreezerOpenedFridge(RefrigeratorMachine refrigeratorMachine) {

        this.refrigeratorMachine = refrigeratorMachine;
    }

    @Override
    public void openFreezer() {

        System.out.println("Opening Freezer");
        refrigeratorMachine.setRefrigeratorState(refrigeratorMachine.getOpenedFreezerOpenedFridge());
    }

    @Override
    public void openFridge() {

        System.out.println("Fridge is already open");
    }

    @Override
    public void closeFreezer() {

        System.out.println("Freezer is already closed");
    }

    @Override
    public void closeFridge() {

        System.out.println("Closing Fridge");
        refrigeratorMachine.setRefrigeratorState(refrigeratorMachine.getClosedFreezerClosedFridge());
    }
}
