package P2I1;

public class RefrigeratorMachine {

    private RefrigeratorState openedFreezerOpenedFridge;
    private RefrigeratorState openedFreezerClosedFridge;
    private RefrigeratorState closedFreezerOpenedFridge;
    private RefrigeratorState closedFreezerClosedFridge;

    RefrigeratorState refrigeratorState;

    public RefrigeratorMachine() {

        openedFreezerOpenedFridge = new OpenedFreezerOpenedFridge(this);
        openedFreezerClosedFridge = new ClosedFreezerOpenedFridge(this);
        closedFreezerOpenedFridge = new ClosedFreezerClosedFridge(this);
        closedFreezerClosedFridge = new ClosedFreezerClosedFridge(this);

        refrigeratorState = closedFreezerClosedFridge;
    }

    public void setRefrigeratorState(RefrigeratorState refrigeratorState) {

        this.refrigeratorState = refrigeratorState;
    }

    public void openFreezer() {

        refrigeratorState.openFreezer();
    }

    public void closeFreezer() {

        refrigeratorState.closeFreezer();
    }

    public void openFridge() {

        refrigeratorState.openFridge();
    }

    public void closeFridge() {

        refrigeratorState.closeFridge();
    }

    public RefrigeratorState getOpenedFreezerOpenedFridge() {
        return openedFreezerOpenedFridge;
    }

    public RefrigeratorState getOpenedFreezerClosedFridge() {
        return openedFreezerClosedFridge;
    }

    public RefrigeratorState getClosedFreezerOpenedFridge() {
        return closedFreezerOpenedFridge;
    }

    public RefrigeratorState getClosedFreezerClosedFridge() {
        return closedFreezerClosedFridge;
    }
}
