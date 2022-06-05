package deck;

import deck.comparators.CardComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private final HandType type;
    private final Rank handRank;

    Hand(HandType type, Rank handRank){
        this.type = type;
        this.handRank = handRank;
    }

    public HandType getType() {
        return type;
    }

    //TODO : remove
    public static void main(String[] args) {
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(Rank.FIVE, Suit.CLUBS));
        cards.add(new Card(Rank.JACK, Suit.SPADES));
        System.out.println("TESTING PAIR");
        HandEvaluator eval = new HandEvaluator(cards);
        Rank rank = eval.handIsPair();
        System.out.println(rank);

        System.out.println("---");
        cards.add(new Card(Rank.FIVE, Suit.HEARTS));
        cards.add(new Card(Rank.JACK, Suit.HEARTS));
        eval = new HandEvaluator(cards);
        rank = eval.handIsPair();
        System.out.println(rank);

    }

    public Rank getHandRank() {
        return handRank;
    }
}
