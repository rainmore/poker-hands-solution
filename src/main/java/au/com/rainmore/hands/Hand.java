package au.com.rainmore.hands;

import au.com.rainmore.cards.Card;
import au.com.rainmore.cards.CardComparator;
import au.com.rainmore.cards.Suit;
import au.com.rainmore.cards.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Hand {

    public static final int MAX_CARDS = 5;

    private final Card[] cards;

    public Hand(Card[] cards) {
        if (cards.length > MAX_CARDS) throw new IllegalArgumentException();
        // sort the cards
        this.cards = Arrays.stream(cards).sorted(new CardComparator()).toArray(Card[]::new);
    }

    public Card[] getCards() {
        return cards;
    }

    public Card getMax() {
        return cards[MAX_CARDS - 1];
    }

    public Card getMin() {
        return cards[0];
    }

    public Rank getRank() {
        Rank rank = Rank.HIGH_CARD;

        Map<Value, List<Card>> valueMap = Arrays.stream(cards).collect(Collectors.groupingBy(Card::getValue));

        if (valueMap.size() == 2) {
            if (valueMap.values().stream().anyMatch(cards -> cards.size() == 4)) rank = Rank.FOUR_OF_A_KIND;
            else rank = Rank.FULL_HOUSE;
        }
        else if (valueMap.size() == 3) {
            if (valueMap.values().stream().anyMatch(cards -> cards.size() == 3)) rank = Rank.THREE_OF_A_KIND;
            else rank = Rank.TWO_PAIRS;
        }
        else if (valueMap.size() == 4) {
            rank = Rank.PAIR;
        }
        else {
            // check is straight
            int diff = 0;

            for (int i = cards.length - 1; i > 0; i--) {
                diff += cards[i].getValue().value() - cards[i - 1].getValue().value();
            }

            boolean isStraight = diff == cards.length - 1;

            Map<Suit, List<Card>> suitMap = Arrays.stream(cards).collect(Collectors.groupingBy(Card::getSuit));

            boolean isFlush = suitMap.size() == 1;

            if (isStraight && isFlush) {
                if (cards[0].getValue() == Value.TEN) rank = Rank.ROYAL_FLUSH;
                else rank = Rank.STRAIGHT_FLUSH;
            }
            else if (isFlush) rank = Rank.FLUSH;
            else if (isStraight) rank = Rank.STRAIGHT;

        }

        return rank;
    }
}
