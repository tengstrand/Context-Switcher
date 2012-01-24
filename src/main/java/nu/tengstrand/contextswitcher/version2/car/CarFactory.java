package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.export.CarStateAsRow;
import nu.tengstrand.contextswitcher.version2.car.state.PublicCarState;

public class CarFactory {
    private Context context;

    public CarFactory(Context context) {
        this.context = context;
    }

    public PublicCarState create(int lengthInCentimeters, String name, CarColor color) {
        return new PublicCarState(lengthInCentimeters, name, color, context);
    }

    public CarStateAsRow createFromRow(String row) {
        return new CarStateAsRow(row, context);
    }

    public PublicCarStateCreator.LenghInCentimeter create() {
        return PublicCarStateCreator.create(context);
    }
}
