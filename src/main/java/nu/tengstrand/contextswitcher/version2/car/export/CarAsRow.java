package nu.tengstrand.contextswitcher.version2.car.export;

import nu.tengstrand.contextswitcher.version2.car.CarState;

import java.io.PrintStream;

public class CarAsRow {
    private String carAsRow;

    public CarAsRow(CarState state) {
        carAsRow = state.lengthInCentimeters + "," + state.name + "," + state.color;
    }

    public void export(PrintStream output) {
        output.print("Exported: " + carAsRow);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        return carAsRow.equals(o);
    }

    @Override
    public int hashCode() {
        return carAsRow.hashCode();
    }

    @Override
    public String toString() {
        return "CarAsRow{" + carAsRow + '}';
    }
}
