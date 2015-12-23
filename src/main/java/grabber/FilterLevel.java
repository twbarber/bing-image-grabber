package grabber;

/**
 * Created by Tyler on 12/23/15.
 */
public enum FilterLevel {

    OFF("Off"), MODERATE("Moderate"), STRICT("Strict");

    private final String filterLevel;

    FilterLevel(final String filterLevel) {
        this.filterLevel = filterLevel;
    }

    @Override
    public String toString() {
        return this.filterLevel;
    }
}
