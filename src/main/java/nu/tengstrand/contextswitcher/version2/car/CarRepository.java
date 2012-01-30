package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.persistence.CarStateRepository;

public class CarRepository {
    private CarStateRepository repository = new CarStateRepository();

    public CarsDresser findBy(String criteria) {
        return new CarsDresser(repository.findBy(criteria));
    }
}
