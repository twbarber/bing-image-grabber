package big;

/**
 * Created by Tyler on 12/23/15.
 */
public enum AdultOption {

    OFF("Off"), MODERATE("Moderate"), STRICT("Strict");

    private final String adultOption;

    AdultOption(final String adultOption) {
        this.adultOption = adultOption;
    }

    @Override
    public String toString() {
        return this.adultOption;
    }
}
