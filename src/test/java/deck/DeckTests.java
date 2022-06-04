package deck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class DeckTests {

    @Test
    public void testDeckInitialization(){
        Deck deck = new Deck();
        deck.shuffle();
        // Test deck is initialized correctly and contains all cards.
        for(Rank rank : Rank.values()){
            for(Suit suit : Suit.values()){
                Card current = new Card(rank,suit);
                Assertions.assertTrue(deck.contains(current));
            }
        }
    }
    @Test
    public void testCardDrawing(){
        Deck deck = new Deck();
        deck.shuffle();
        Card card = deck.getNextCard();
        // Check that cards are correctly removed from the deck.
        Assertions.assertNotEquals(card, deck.getNextCard());
        boolean isDeckRandomized = false;
        Set<Card> seenCards = new HashSet<>();
        // Shuffle deck five times, check we don't get five times the same head card.
        for(int i = 0; i < 10; i++){
            deck.shuffle();
            card = deck.getNextCard();
            if(!seenCards.contains(card) && !seenCards.isEmpty()) {
                isDeckRandomized = true;
                break;
            }
            seenCards.add(card);
        }
        Assertions.assertTrue(isDeckRandomized);
    }

}
