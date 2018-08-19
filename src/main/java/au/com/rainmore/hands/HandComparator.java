package au.com.rainmore.hands;

import au.com.rainmore.cards.Card;
import au.com.rainmore.cards.CardComparator;
import au.com.rainmore.cards.Value;
import au.com.rainmore.cards.ValueComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HandComparator implements Comparator<Hand> {

    private CardComparator cardComparator = new CardComparator();

    private ValueComparator valueComparator = new ValueComparator();

    private RankComparator rankComparator = new RankComparator();

    @Override
    public int compare(Hand o1, Hand o2) {
        Rank rank1 = o1.getRank();
        Rank rank2 = o2.getRank();
        int result = rankComparator.compare(rank1, rank2);

        if (result == 0) {
            switch (rank1) {
                case ROYAL_FLUSH:
                case STRAIGHT_FLUSH:
                case STRAIGHT:
                    result = cardComparator.compare(o1.getMax(), o2.getMax());
                    break;
                case FLUSH:
                case HIGH_CARD:
                    result = compareHighCard(o1.getCards(), o2.getCards());
                    break;
                case PAIR:
                case TWO_PAIRS:
                    result = comparePairCards(o1, o2);
                    break;
                case THREE_OF_A_KIND:
                case FULL_HOUSE:
                    result = valueComparator.compare(getThreeOfAKindValue(o1), getThreeOfAKindValue(o2));
                    break;
                case FOUR_OF_A_KIND:
                    result = valueComparator.compare(getFourOfAKindValue(o1), getFourOfAKindValue(o2));
                    break;
            }
        }

        return result;
    }

    private Value getThreeOfAKindValue(Hand hand) {
        return filterKindValue(hand.getCards(), 3).get(0);
    }

    private Value getFourOfAKindValue(Hand hand) {
        return filterKindValue(hand.getCards(), 4).get(0);
    }

    private int comparePairCards(Hand hand1, Hand hand2) {
        List<Value> paresValues1 = filterKindValue(hand1.getCards(), 2);
        List<Value> paresValues2 = filterKindValue(hand2.getCards(), 2);

        int result = 0;
        for (int i = paresValues1.size() - 1; i >= 0; i--) {
            result = valueComparator.compare(paresValues1.get(i), paresValues2.get(i));
            if (result != 0) break;
        }

        if (result == 0) {
            paresValues1 = filterKindValue(hand1.getCards(), 1);
            paresValues2 = filterKindValue(hand2.getCards(), 1);

            for (int i = paresValues1.size() - 1; i >= 0; i--) {
                result = valueComparator.compare(paresValues1.get(i), paresValues2.get(i));
                if (result != 0) break;
            }
        }

        return result;
    }

    private List<Value> filterKindValue(Card[] cards, int count) {
        return getKindValue(cards)
                .entrySet().stream()
                .filter(map -> map.getValue().size() == count)
                .map(Map.Entry::getKey)
                .sorted(valueComparator.reversed())
                .collect(Collectors.toList());
    }

    private Map<Value, List<Card>> getKindValue(Card[] cards) {
        return Arrays.stream(cards)
                .collect(Collectors.groupingBy(Card::getValue));
    }

    private int compareHighCard(Card[] cards1, Card[] cards2) {
        int result = 0;
        for (int i = cards1.length - 1; i >= 0; i--) {
            result = cardComparator.compare(cards1[i], cards2[i]);
            if (result != 0) break;
        }
        return result;
    }

}
