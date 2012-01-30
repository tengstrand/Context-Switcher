package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.persistence.CarStateRepository;

public class CarRepository {
    private CarStateRepository repository = new CarStateRepository();

    public CarSwitchers findBy(String criteria) {
        return new CarSwitchers(repository.findBy(criteria));
    }
}
