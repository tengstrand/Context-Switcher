package nu.tengstrand.contextswitcher.version2.car.state;

import nu.tengstrand.contextswitcher.version2.car.business.Car;
import nu.tengstrand.contextswitcher.version2.car.export.CarAsRowInFile;
import nu.tengstrand.contextswitcher.version2.car.persistence.CarInDb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Used to choose representation for a list of public car states.
 */
public class PublicCarStates {
    private List<PublicCarState> carStates = new ArrayList<PublicCarState>();

    public void add(PublicCarState publicCarState) {
        carStates.add(publicCarState);
    }

    public boolean isValid() {
        for (PublicCarState state : carStates) {
            if (!state.isValid()) {
                return false;
            }
        }
        return true;
    }

    public List<Car> asCars() {
        List<Car> cars = new ArrayList<Car>();
        for (PublicCarState state : carStates) {
            cars.add(state.asCar());
        }
        return cars;
    }

    public List<CarAsRowInFile> asCarsAsRow() {
        List<CarAsRowInFile> cars = new ArrayList<CarAsRowInFile>();
        for (PublicCarState state : carStates) {
            cars.add(state.asCarAsRow());
        }
        return cars;
    }

    public Set<CarInDb> asCarsInDb() {
        Set<CarInDb> cars = new HashSet<CarInDb>();
        for (PublicCarState state : carStates) {
            cars.add(state.asCarInDb());
        }
        return cars;
    }
}
