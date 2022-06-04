package deck;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {
    List<Card> cards;
    Hand bestHand;
    Board(){
       this.cards = new ArrayList<>();
    }

    public boolean addCard(Card card){
        if (cards.size() >= 7) return false;
        cards.add(card);
        this.bestHand = evaluateBestHand();
        return true;
    }

    public void clearHand(){
        cards = new ArrayList<>();
    }

    public Hand evaluateBestHand(){
        Rank handRank = HandEvaluator.handIsStraightFlush(this.cards);
        if(handRank != null) return new Hand(HandType.STRAIGHT_FLUSH, handRank);

        handRank = HandEvaluator.handIsQuads(this.cards);
        if(handRank != null) return new Hand(HandType.FOUR_OF_A_KIND, handRank);

        handRank = HandEvaluator.handIsFullHouse(this.cards);
        if(handRank != null) return new Hand(HandType.FULL_HOUSE, handRank);

        handRank = HandEvaluator.handIsFlush(this.cards);
        if(handRank != null) return new Hand(HandType.FLUSH, handRank);

        handRank = HandEvaluator.handIsStraight(this.cards);
        if(handRank != null) return new Hand(HandType.STRAIGHT, handRank);

        handRank = HandEvaluator.handIsSet(this.cards);
        if(handRank != null) return new Hand(HandType.THREE_OF_A_KIND, handRank);

        handRank = HandEvaluator.handIsTwoPairs(this.cards);
        if(handRank != null) return new Hand(HandType.TWO_PAIRS, handRank);

        handRank = HandEvaluator.handIsPair(this.cards);
        if(handRank != null) return new Hand(HandType.PAIR, handRank);

        handRank = HandEvaluator.handHighCard(cards);
        return new Hand(HandType.HIGH_CARD, handRank);
    }
}