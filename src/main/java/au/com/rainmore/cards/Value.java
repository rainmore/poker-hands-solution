package au.com.rainmore.cards;

import java.util.Arrays;

public enum Value {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String displayName;
    private final int code;

    Value(String displayName, int code) {
        this.displayName = displayName;
        this.code = code;
    }

    public int value() {
        return this.code;
    }

    public String displayName() {
        return this.displayName;
    }

    public String toString() {
        return this.displayName();
    }

    public static Value forValue(String value) {
        return Arrays.stream(Value.values()).filter(v -> v.displayName().equals(value)).findFirst().orElse(null);
    }

}
