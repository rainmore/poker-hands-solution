package au.com.rainmore.cards;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    private ValueComparator valueComparator = new ValueComparator();

    @Override
    public int compare(Card o1, Card o2) {
        return valueComparator.compare(o1.getValue(), o2.getValue());
    }
}
