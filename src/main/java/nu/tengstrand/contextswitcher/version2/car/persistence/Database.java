package nu.tengstrand.contextswitcher.version2.car.persistence;

public class Database {
    private int primaryKey = 1;

    public int getNextCarPrimaryKey() {
        return primaryKey++;
    }
}
