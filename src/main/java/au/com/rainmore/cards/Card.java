package au.com.rainmore.cards;

public class Card {

    private final Value value;
    private final Suit  suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return String.format("%s%s", value.toString(), suit.toString());
    }
}
