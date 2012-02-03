package nu.tengstrand.contextswitcher.version2.car.business;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;
import nu.tengstrand.contextswitcher.version2.car.CarStates;
import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.exportimport.CarsAsRow;
import nu.tengstrand.contextswitcher.version2.car.state.CarCreator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cars implements Iterable<Car> {
    private List<Car> cars = new ArrayList<Car>();

    /**
     * Creates a list of cars that has no coupling to the original
     * list of car states (ensured by the constructor of Car).
     *
     * @param states
     * @param context
     */
    public Cars(CarStates states, Context context) {
        for (CarState state : states) {
            cars.add(new CarCreator(state).asCar(context));
        }
    }

    public Iterator<Car> iterator() {
        return cars.iterator();
    }

    public CarsAsRow asCarsAsRow() {
        CarsAsRow carsAsRow = new CarsAsRow();
        for (Car car : cars) {
            carsAsRow.add(car.asCarAsRow());
        }
        return carsAsRow;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        return cars.equals(((Cars)o).cars);
    }

    @Override
    public int hashCode() {
        return cars.hashCode();
    }

    @Override
    public String toString() {
        return "Cars{" + cars + '}';
    }
}
