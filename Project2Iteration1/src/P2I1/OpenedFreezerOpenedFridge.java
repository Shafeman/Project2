package P2I1;

public class OpenedFreezerOpenedFridge implements RefrigeratorState {

    RefrigeratorMachine refrigeratorMachine;

    public OpenedFreezerOpenedFridge(RefrigeratorMachine refrigeratorMachine) {

        this.refrigeratorMachine = refrigeratorMachine;
    }

    @Override
    public void openFreezer() {

        System.out.println("Freezer is already open");
    }

    @Override
    public void openFridge() {

        System.out.println("Fridge is already open");
    }

    @Override
    public void closeFreezer() {

        System.out.println("Closing Freezer");
        refrigeratorMachine.setRefrigeratorState(refrigeratorMachine.getClosedFreezerOpenedFridge());
    }

    @Override
    public void closeFridge() {

        System.out.println("Closing Fridge");
        refrigeratorMachine.setRefrigeratorState(refrigeratorMachine.getOpenedFreezerClosedFridge());
    }
}
