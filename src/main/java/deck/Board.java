package deck;

import java.util.ArrayList;
import java.util.List;

public class Board implements IBoard {
    Hand bestHand;
    HandEvaluator evaluator;
    Board(){
       this.evaluator = new HandEvaluator();
    }

    @Override
    public boolean addCard(Card card){
        if (this.evaluator.cards.size() >= 7) return false;
        this.evaluator.updateHand(card);
        this.bestHand = evaluator.evaluateBestHand();
        return true;
    }
    public void clearHand(){
        this.evaluator.clearHand();
    }
}