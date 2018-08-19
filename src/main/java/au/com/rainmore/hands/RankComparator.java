package au.com.rainmore.hands;

import java.util.Comparator;

public class RankComparator implements Comparator<Rank> {

    @Override
    public int compare(Rank o1, Rank o2) {
        return Integer.compare(o1.getCode(), o2.getCode());
    }
}
