package nu.tengstrand.contextswitcher.version2.car.business;

import nu.tengstrand.contextswitcher.version2.car.state.CarState;

public class RestrictedAttributes {
    private final CarState state;

    public RestrictedAttributes(CarState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return state.toString("***");
    }
}
