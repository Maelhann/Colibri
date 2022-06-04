package deck;

import java.util.Collections;
import java.util.LinkedList;

public class Deck implements IDeck{
    private LinkedList<Card> cards;

    Deck(){
        this.shuffle();
    }

    public void shuffle(){
        LinkedList<Card> cards = new LinkedList<>();
        Rank[] ranks =  Rank.values();
        Suit[] suits = Suit.values();
        for(Suit suit : suits) {
            for(Rank rank : ranks){
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
        this.cards = cards;
    }

    public boolean contains(Card card){
        return this.cards.contains(card);
    }

    @Override
    public Card getNextCard() {
        return cards.pollFirst();
    }

}
