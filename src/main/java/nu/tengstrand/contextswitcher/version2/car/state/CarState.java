package nu.tengstrand.contextswitcher.version2.car.state;

import nu.tengstrand.contextswitcher.version2.car.CarColor;

/**
 * Represents the immutable state of all different representations of the concept Car.
 * This is just a data carrier so we don't need (and don't want) to add getters and setters!
 *
 * The strategy to ensure valid state is to set the attributes to final.
 * The exception is "primaryKey" that is not part of the state and should not be part
 * of the implementations of equals(), hashCode() and toString().
 *
 * The CarInDb is the only representation that need to take primaryKey into account,
 * and as a logical consequence had to override the implementations of equals(),
 * hashCode() and toString().
 */
public class CarState {
    // The primary need to be put here so we don't lose track of it when switching context.
    public Integer primaryKey;

    // Our immutable attributes that represents the state
    // A state object, like this CarState, does not have to be immutable,
    // the important thing is that we can ensure that always is valid!
    // To set them as final is an effective way to ensure that.
    public final int lengthInCentimeters;
    public final String name;
    public final CarColor color;

    /**
     * Make sure this constructor is package-private!
     */
    CarState(int lengthInCentimeters, String name, CarColor color) {
        this.lengthInCentimeters = lengthInCentimeters;
        this.name = name;
        this.color = color;
    }

    CarState(int primaryKey, int lengthInCentimeters, String name, CarColor color) {
        this(lengthInCentimeters, name, color);
        this.primaryKey = primaryKey;
    }

    public boolean isValid() {
        return name != null && color != null && lengthInCentimeters >= 100;
    }

    /**
     * If the primary key i set, only compare against the primary keys,
     * otherwise do a "normal" compare against the attributes.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarState state = (CarState) o;

        if (primaryKey != null) return primaryKey.equals(state.primaryKey);

        if (lengthInCentimeters != state.lengthInCentimeters) return false;
        if (color != state.color) return false;
        if (name != null ? !name.equals(state.name) : state.name != null) return false;

        return true;
    }

    /**
     * Remember to exclude "primaryKey" from the hashCode calculation!
     * It should only be considered in the context "CarInDb".
     */
    @Override
    public int hashCode() {
        if (primaryKey != null) return primaryKey.hashCode();

        int result = lengthInCentimeters;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }

    public String allAttributes() {
        return "primaryKey=" + primaryKey + ", " + toString();
    }

    /**
     * Remember to exclude "primaryKey",  it should only be considered in the context "CarInDb".
     */
    @Override
    public String toString() {
        return "length=" + lengthInCentimeters +
                ", name='" + name + '\'' +
                ", color=" + color;
    }
}
