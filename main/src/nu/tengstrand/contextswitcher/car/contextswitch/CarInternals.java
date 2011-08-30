package nu.tengstrand.contextswitcher.car.contextswitch;

/**
 * Put the internal representation of a car, that is shared between
 * different representations, in this class!
 */
public class CarInternals {
    // We don't want to lose track of the PK when switching context
    // so we need to put it here together with the other shared attributes.
    public Integer primaryKey;
    public int lengthInCentimeters;
    public String name;

    public CarInternals(int lengthInCentimeters, String name) {
        if (lengthInCentimeters < 0 || lengthInCentimeters > 999) {
            throw new IllegalArgumentException("Illegal length: " + lengthInCentimeters);
        }
        this.lengthInCentimeters = lengthInCentimeters;
        this.name = name;
    }

    public String toString() {
        return "lengthInCentimeters=" + lengthInCentimeters + ", name=" + name;
    }
}
