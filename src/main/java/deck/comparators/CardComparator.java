package deck.comparators;

import deck.Card;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card a, Card b) {
        return a.getRank().compareTo(b.getRank());
    }
}
