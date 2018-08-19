package au.com.rainmore.cards;

import java.util.Comparator;

public class ValueComparator implements Comparator<Value> {

    @Override
    public int compare(Value o1, Value o2) {
        return Integer.compare(o1.value(), o2.value());
    }
}
