package deck;

import deck.comparators.CardComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
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
        expectedCards.sort(new CardComparator());
        Assertions.assertEquals(expectedCards, board.evaluator.cards);
        Assertions.assertEquals(7, board.evaluator.cards.size());
        List<Card> cards = board.evaluator.cards;
        Assertions.assertFalse(board.addCard(deck.getNextCard()));
        Assertions.assertEquals(cards, board.evaluator.cards);
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
        Assertions.assertEquals(expectedCards, board.evaluator.cards);
    }

}
