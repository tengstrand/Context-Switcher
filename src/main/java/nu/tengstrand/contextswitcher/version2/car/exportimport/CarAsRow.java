package nu.tengstrand.contextswitcher.version2.car.exportimport;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;

import java.io.PrintStream;

public class CarAsRow {
    private String row;

    public CarAsRow(CarState state) {
        row = state.lengthInCentimeters + "," + state.name + "," + state.color;
    }

    public void export(PrintStream output) {
        output.println("Exported: " + row);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        return row.equals(o);
    }

    @Override
    public int hashCode() {
        return row.hashCode();
    }

    @Override
    public String toString() {
        return "CarAsRow{'" + row + "'}";
    }
}
