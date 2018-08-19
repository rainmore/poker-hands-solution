package au.com.rainmore.cards;

import java.util.Arrays;

public enum Suit {
    DIAMONDS("D"), HEARTS("H"), SPADES("S"), CLUBS("C");

    private final String code;

    Suit(String code) {
        this.code = code;
    }

    public String value() {
        return this.code;
    }

    public String toString() {
        return this.value();
    }

    public static Suit forValue(String value) {
        return Arrays.stream(Suit.values()).filter(code -> code.value().equals(value)).findFirst().orElse(null);
    }
}
