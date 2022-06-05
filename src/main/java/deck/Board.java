package deck;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {
    List<Card> cards;
    Hand bestHand;
    Board(){
       this.cards = new ArrayList<>();
    }

    @Override
    public boolean addCard(Card card){
        if (cards.size() >= 7) return false;
        cards.add(card);
        this.bestHand = computeBestHand();
        return true;
    }

    @Override
    public void clearHand(){
        cards = new ArrayList<>();
    }

    @Override
    public Hand computeBestHand() {
        HandEvaluator evaluator = new HandEvaluator(this.cards);
        return evaluator.evaluateBestHand();
    }
}