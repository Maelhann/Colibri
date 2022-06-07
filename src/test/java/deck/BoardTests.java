package deck;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardTests {

    @Test
    public void testAddCard(){
        Board board = new Board();
        Deck deck = new Deck();
        List<Card> expectedCards = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            Card nextCard = deck.getNextCard();
            expectedCards.add(nextCard);
            Assertions.assertTrue(board.addCard(nextCard));
        }
        Assertions.assertEquals(expectedCards, board.cards);
        Assertions.assertEquals(7, board.cards.size());
        List<Card> cards = board.cards;
        Assertions.assertFalse(board.addCard(deck.getNextCard()));
        Assertions.assertEquals(cards, board.cards);
    }

    @Test
    public void testClearHand(){
        Board board = new Board();
        Deck deck = new Deck();
        List<Card> expectedCards = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            board.addCard(deck.getNextCard());
        }
        board.clearHand();
        Assertions.assertEquals(expectedCards, board.cards);
    }

}
