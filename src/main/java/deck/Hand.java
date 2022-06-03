package deck;

import java.util.ArrayList;
import java.util.List;

public class Hand implements IHand{
    List<Card> cards;

    Hand(){
       this.cards = new ArrayList<>();
    }

    public void addCard(Card card){
        assert cards.size() < 8;
        cards.add(card);
    }

    public void clearHand(){
        cards = new ArrayList<>();
    }
    // TODO Write algorithm for comparison
}