package P2I1;

public class ClosedFreezerClosedFridge implements RefrigeratorState {

    RefrigeratorMachine refrigeratorMachine;

    public ClosedFreezerClosedFridge(RefrigeratorMachine refrigeratorMachine) {

        this.refrigeratorMachine = refrigeratorMachine;
    }

    @Override
    public void openFreezer() {

        System.out.println("Opening Freezer");
        refrigeratorMachine.setRefrigeratorState(refrigeratorMachine.getOpenedFreezerClosedFridge());
    }

    @Override
    public void openFridge() {

        System.out.println("Opening Fridge");
        refrigeratorMachine.setRefrigeratorState(refrigeratorMachine.getClosedFreezerOpenedFridge());
    }

    @Override
    public void closeFreezer() {

        System.out.println("Freezer is already closed");
    }

    @Override
    public void closeFridge() {

        System.out.println("Fridge is already closed");
    }
}
