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

    // Have 10 of these.
    public void testComputeBestHand1(){
        Board board = new Board();
        // TODO write those tests.
//        Card deuceHearts = new Card(Rank.DEUCE, Suit.HEARTS);
//        board.addCard(deuceHearts);
//        Hand expectedBestHand = new Hand(HandType.HIGH_CARD, Rank.DEUCE);
//        Assertions.assertEquals(board.evaluateBestHand(), expectedBestHand);
//
//        Card fiveClubs = new Card(Rank.FIVE, Suit.CLUBS);
//        board.addCard(fiveClubs);
//        expectedBestHand = new Hand(HandType.HIGH_CARD, Rank.FIVE);
//        Assertions.assertEquals(board.evaluateBestHand(), expectedBestHand);
//
//        Card deuceSpades = new Card(Rank.DEUCE, Suit.SPADES);
//        board.addCard(deuceSpades);
//        expectedBestHand = new Hand(HandType.PAIR, Rank.DEUCE);
//        Assertions.assertEquals(board.evaluateBestHand(), expectedBestHand);
    }
}
