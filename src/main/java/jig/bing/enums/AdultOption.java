package jig.bing.enums;

/**
 * AdultOption enumeration for Bing Search Parameters.
 */
public enum AdultOption {

    OFF("Off"),
    MODERATE("Moderate"),
    STRICT("Strict");

    private final String adultOption;

    AdultOption(final String adultOption) {
        this.adultOption = adultOption;
    }

    @Override
    public String toString() {
        return this.adultOption;
    }
}
