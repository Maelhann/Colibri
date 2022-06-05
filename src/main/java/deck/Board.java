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
        HandEvaluator evaluator = new HandEvaluator(this.cards);
        Rank kicker = evaluator.handHighCard();

        Rank handRank = evaluator.handIsStraightFlush();
        if(handRank != null) return new Hand(HandType.STRAIGHT_FLUSH, handRank);

        handRank = evaluator.handIsQuads();
        if(handRank != null) return new Hand(HandType.FOUR_OF_A_KIND, handRank);

        handRank = evaluator.handIsFullHouse();
        if(handRank != null) return new Hand(HandType.FULL_HOUSE, handRank);

        handRank = evaluator.handIsFlush();
        if(handRank != null) return new Hand(HandType.FLUSH, handRank);

        handRank = evaluator.handIsStraight();
        if(handRank != null) return new Hand(HandType.STRAIGHT, handRank);

        handRank = evaluator.handIsSet();
        if(handRank != null) return new Hand(HandType.THREE_OF_A_KIND, handRank);

        handRank = evaluator.handIsTwoPairs();
        if(handRank != null) return new Hand(HandType.TWO_PAIRS, handRank);

        handRank = evaluator.handIsPair();
        if(handRank != null) return new Hand(HandType.PAIR, handRank);
        return new Hand(HandType.HIGH_CARD, kicker);
    }
}