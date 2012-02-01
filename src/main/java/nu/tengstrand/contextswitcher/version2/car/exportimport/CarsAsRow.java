package nu.tengstrand.contextswitcher.version2.car.exportimport;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.CarStates;
import nu.tengstrand.contextswitcher.version2.car.state.CarDresser;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarsAsRow implements Iterable<CarAsRow> {
    private final List<CarAsRow> cars = new ArrayList<CarAsRow>();

    /**
     * Creates an empty list
     */
    public CarsAsRow() {
    }

    public CarsAsRow(CarStates states) {
        for (CarState state : states) {
            cars.add(new CarDresser(state).asCarAsRow());
        }
    }

    public void add(CarAsRow carAsRow) {
        cars.add(carAsRow);
    }

    public void export(PrintStream output) {
        for (CarAsRow car : cars) {
            car.export(output);
        }
    }

    public Iterator<CarAsRow> iterator() {
        return cars.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        return cars.equals(((CarsAsRow)o).cars);
    }

    @Override
    public int hashCode() {
        return cars.hashCode();
    }

    @Override
    public String toString() {
        return "CarsAsRow{" + cars + '}';
    }
}
