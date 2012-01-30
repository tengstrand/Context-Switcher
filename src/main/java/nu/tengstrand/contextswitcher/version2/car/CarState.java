package nu.tengstrand.contextswitcher.version2.car;

/**
 * This is just a data carrier so we don't need (and don't want) to add getters and setters!
 */
public class CarState {
    // The primary need to be put here so we don't lose track of it when switching task.
    public Integer primaryKey;

    public int lengthInCentimeters;
    public String name;
    public CarColor color;

    /**
     * Any mutable incoming parameters need to be cloned to guarantee that
     * they can not be modified from the outside of this class!
     */
    public CarState(int lengthInCentimeters, String name, CarColor color) {
        this.lengthInCentimeters = lengthInCentimeters;
        this.name = name;
        this.color = color;
    }

    public CarState(Integer primaryKey, int lengthInCentimeters, String name, CarColor color) {
        this(lengthInCentimeters, name, color);
        this.primaryKey = primaryKey;
    }

    /**
     * If any of the instance variables are mutable, they need to be copied
     * and a new instance of CarState should be returned.
     */
    public CarState validCopy() {
        if (!isValid()) {
            throw new IllegalStateException("Invalid car state");
        }
        return this;
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

    @Override
    public String toString() {
        return toString(color.toString());
    }

    public String toString(String color) {
        return "length=" + lengthInCentimeters +
                ", name='" + name + '\'' +
                ", color=" + color;
    }
}
