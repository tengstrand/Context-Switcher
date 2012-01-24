package nu.tengstrand.contextswitcher.version2.car;

import nu.tengstrand.contextswitcher.version2.car.context.Context;
import nu.tengstrand.contextswitcher.version2.car.state.PublicCarState;

/**
 * This class make use of the pattern Chained Creator to improve the API
 * when creating an instance of PublicCarState.
 */
public class PublicCarStateCreator {
    private int lengthInCentimeters;
    private String name;
    private CarColor color;

    private final Context context;

    public PublicCarStateCreator(Context context) {
        this.context = context;
    }

    /**
     * Starting point of the constructor chain which ends with a PublicCarState.
     */
    public static LenghInCentimeter create(Context context) {
        return new PublicCarStateCreator(context).new LenghInCentimeter();
    }

    public class LenghInCentimeter {
        public Name lengthInCentimeters(int length) {
            PublicCarStateCreator.this.lengthInCentimeters = length;
            return new Name();
        }
    }

    public class Name {
        public Color name(String name) {
            PublicCarStateCreator.this.name = name;
            return new Color();
        }
    }

    public class Color {
        public PublicCarState color(CarColor color) {
            PublicCarStateCreator.this.color = color;
            return new PublicCarState(lengthInCentimeters, name, color, context);
        }
    }
}
