package deck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HandEvaluationTests {

    @Test
    public void testPair(){
        HandEvaluator evaluator = new HandEvaluator();
        // We're creating cards in the order they'll be added to the board.
        Card jS = new Card(Rank.JACK, Suit.SPADES);
        Card kC = new Card(Rank.KING, Suit.CLUBS);
        Card aH = new Card(Rank.ACE, Suit.HEARTS);
        Card tS = new Card(Rank.TREY, Suit.SPADES);
        Card dC = new Card(Rank.DEUCE, Suit.CLUBS);
        Card jD = new Card(Rank.JACK, Suit.DIAMONDS);

        evaluator.updateHand(jS);
        Assertions.assertFalse(evaluator.handIsPair());
        evaluator.updateHand(kC);
        Assertions.assertFalse(evaluator.handIsPair());
        evaluator.updateHand(aH);
        Assertions.assertFalse(evaluator.handIsPair());
        evaluator.updateHand(tS);
        Assertions.assertFalse(evaluator.handIsPair());
        evaluator.updateHand(dC);
        Assertions.assertFalse(evaluator.handIsPair());
        // Checking Pair is now true happening.
        evaluator.updateHand(jD);
        Assertions.assertTrue(evaluator.handIsPair());
    }

    @Test
    public void testTwoPair(){
        HandEvaluator evaluator = new HandEvaluator();
        // We're creating cards in the order they'll be added to the board.
        Card jS = new Card(Rank.JACK, Suit.SPADES);
        Card kC = new Card(Rank.KING, Suit.CLUBS);
        Card aH = new Card(Rank.ACE, Suit.HEARTS);
        Card tS = new Card(Rank.TREY, Suit.SPADES);
        Card dC = new Card(Rank.DEUCE, Suit.CLUBS);
        Card jD = new Card(Rank.JACK, Suit.DIAMONDS);
        Card kD = new Card(Rank.KING, Suit.DIAMONDS);
        Card aD = new Card(Rank.ACE, Suit.DIAMONDS);

        evaluator.updateHand(jS);
        Assertions.assertFalse(evaluator.handIsTwoPairs());
        evaluator.updateHand(kC);
        Assertions.assertFalse(evaluator.handIsTwoPairs());
        evaluator.updateHand(aH);
        Assertions.assertFalse(evaluator.handIsTwoPairs());
        evaluator.updateHand(tS);
        Assertions.assertFalse(evaluator.handIsTwoPairs());
        evaluator.updateHand(dC);
        Assertions.assertFalse(evaluator.handIsTwoPairs());
        // Checking One Pair should be false.
        evaluator.updateHand(jD);
        Assertions.assertFalse(evaluator.handIsTwoPairs());
        // Checking Two Pairs are happening.
        evaluator.updateHand(kD);
        Assertions.assertTrue(evaluator.handIsTwoPairs());
        // Testing three pairs don't break the function.
        evaluator.updateHand(aD);
        Assertions.assertTrue(evaluator.handIsTwoPairs());
    }

    @Test
    public void testSet(){
        HandEvaluator evaluator = new HandEvaluator();
        // We're creating cards in the order they'll be added to the board.
        Card jS = new Card(Rank.JACK, Suit.SPADES);
        Card kC = new Card(Rank.KING, Suit.CLUBS);
        Card aH = new Card(Rank.ACE, Suit.HEARTS);
        Card tS = new Card(Rank.TREY, Suit.SPADES);
        Card dC = new Card(Rank.DEUCE, Suit.CLUBS);
        Card jD = new Card(Rank.JACK, Suit.DIAMONDS);
        Card aS = new Card(Rank.ACE, Suit.SPADES);
        Card aD = new Card(Rank.ACE, Suit.DIAMONDS);

        evaluator.updateHand(jS);
        Assertions.assertFalse(evaluator.handIsSet());
        evaluator.updateHand(kC);
        Assertions.assertFalse(evaluator.handIsSet());
        evaluator.updateHand(aH);
        Assertions.assertFalse(evaluator.handIsSet());
        evaluator.updateHand(tS);
        Assertions.assertFalse(evaluator.handIsSet());
        evaluator.updateHand(dC);
        Assertions.assertFalse(evaluator.handIsSet());
        evaluator.updateHand(jD);
        Assertions.assertFalse(evaluator.handIsSet());
        evaluator.updateHand(aS);
        Assertions.assertFalse(evaluator.handIsSet());
        // Testing Set of Aces works.
        evaluator.updateHand(aD);
        Assertions.assertTrue(evaluator.handIsSet());
    }

    @Test
    public void testStraight(){
        HandEvaluator evaluator = new HandEvaluator();
        // We're creating cards in the order they'll be added to the board.
        Card jS = new Card(Rank.JACK, Suit.SPADES);
        Card kC = new Card(Rank.KING, Suit.CLUBS);
        Card aH = new Card(Rank.ACE, Suit.HEARTS);
        Card tS = new Card(Rank.TREY, Suit.SPADES);
        Card dC = new Card(Rank.DEUCE, Suit.CLUBS);
        Card fD = new Card(Rank.FOUR, Suit.DIAMONDS);
        Card ffD = new Card(Rank.FIVE, Suit.DIAMONDS);
        Card sD = new Card(Rank.SIX, Suit.DIAMONDS);
        evaluator.updateHand(jS);
        Assertions.assertFalse(evaluator.handIsStraight());
        evaluator.updateHand(kC);
        Assertions.assertFalse(evaluator.handIsStraight());
        evaluator.updateHand(aH);
        Assertions.assertFalse(evaluator.handIsStraight());
        evaluator.updateHand(tS);
        Assertions.assertFalse(evaluator.handIsStraight());
        evaluator.updateHand(dC);
        Assertions.assertFalse(evaluator.handIsStraight());
        evaluator.updateHand(fD);
        Assertions.assertFalse(evaluator.handIsStraight());
        // Testing Bicycle straight works.
        evaluator.updateHand(ffD);
        Assertions.assertTrue(evaluator.handIsStraight());
        // Testing expanding the straight won't break it.
        evaluator.updateHand(sD);
        Assertions.assertTrue(evaluator.handIsStraight());
    }

    @Test
    public void testFlush(){
        HandEvaluator evaluator = new HandEvaluator();
        // We're creating cards in the order they'll be added to the board.
        Card jS = new Card(Rank.JACK, Suit.SPADES);
        Card kC = new Card(Rank.KING, Suit.CLUBS);
        Card aH = new Card(Rank.ACE, Suit.HEARTS);
        Card tS = new Card(Rank.TREY, Suit.SPADES);
        Card dC = new Card(Rank.DEUCE, Suit.CLUBS);
        Card fS = new Card(Rank.FOUR, Suit.SPADES);
        Card ffS = new Card(Rank.FIVE, Suit.SPADES);
        Card sS = new Card(Rank.SIX, Suit.SPADES);
        Card aS = new Card(Rank.ACE, Suit.SPADES);
        evaluator.updateHand(jS);
        Assertions.assertFalse(evaluator.handIsFlush());
        evaluator.updateHand(kC);
        Assertions.assertFalse(evaluator.handIsFlush());
        evaluator.updateHand(aH);
        Assertions.assertFalse(evaluator.handIsFlush());
        evaluator.updateHand(tS);
        Assertions.assertFalse(evaluator.handIsFlush());
        evaluator.updateHand(dC);
        Assertions.assertFalse(evaluator.handIsFlush());
        evaluator.updateHand(fS);
        Assertions.assertFalse(evaluator.handIsFlush());
        evaluator.updateHand(ffS);
        Assertions.assertFalse(evaluator.handIsFlush());
        // Testing five coloured cards make a flush.
        evaluator.updateHand(sS);
        Assertions.assertTrue(evaluator.handIsFlush());
        // Testing expanding the straight won't break it.
        evaluator.updateHand(aS);
        Assertions.assertTrue(evaluator.handIsFlush());
    }

    @Test
    public void testFullHouse(){
        HandEvaluator evaluator = new HandEvaluator();
        // We're creating cards in the order they'll be added to the board.
        Card jS = new Card(Rank.JACK, Suit.SPADES);
        Card kC = new Card(Rank.KING, Suit.CLUBS);
        Card jH = new Card(Rank.JACK, Suit.HEARTS);
        Card tS = new Card(Rank.TREY, Suit.SPADES);
        Card dC = new Card(Rank.DEUCE, Suit.CLUBS);
        Card fS = new Card(Rank.FOUR, Suit.SPADES);
        Card jD = new Card(Rank.JACK, Suit.DIAMONDS);
        Card aH = new Card(Rank.ACE, Suit.HEARTS);
        Card aS = new Card(Rank.ACE, Suit.SPADES);
        evaluator.updateHand(jS);
        Assertions.assertFalse(evaluator.handIsFullHouse());
        evaluator.updateHand(kC);
        Assertions.assertFalse(evaluator.handIsFullHouse());
        evaluator.updateHand(jH);
        Assertions.assertFalse(evaluator.handIsFullHouse());
        evaluator.updateHand(tS);
        Assertions.assertFalse(evaluator.handIsFullHouse());
        evaluator.updateHand(dC);
        Assertions.assertFalse(evaluator.handIsFullHouse());
        evaluator.updateHand(fS);
        Assertions.assertFalse(evaluator.handIsFullHouse());
        evaluator.updateHand(jD);
        Assertions.assertFalse(evaluator.handIsFullHouse());
        evaluator.updateHand(aH);
        Assertions.assertFalse(evaluator.handIsFullHouse());
        // Testing Full houses
        evaluator.updateHand(aS);
        Assertions.assertTrue(evaluator.handIsFullHouse());
        // Subsequently testing Set and Pair are detected.
        Assertions.assertTrue(evaluator.handIsPair());
        Assertions.assertTrue(evaluator.handIsSet());
    }

    @Test
    public void testQuads(){
        HandEvaluator evaluator = new HandEvaluator();
        // We're creating cards in the order they'll be added to the board.
        Card jS = new Card(Rank.JACK, Suit.SPADES);
        Card kC = new Card(Rank.KING, Suit.CLUBS);
        Card aH = new Card(Rank.ACE, Suit.HEARTS);
        Card tS = new Card(Rank.TREY, Suit.SPADES);
        Card dC = new Card(Rank.DEUCE, Suit.CLUBS);
        Card jD = new Card(Rank.JACK, Suit.DIAMONDS);
        Card aS = new Card(Rank.ACE, Suit.SPADES);
        Card aD = new Card(Rank.ACE, Suit.DIAMONDS);
        Card aC = new Card(Rank.ACE, Suit.CLUBS);

        evaluator.updateHand(jS);
        Assertions.assertFalse(evaluator.handIsQuads());
        evaluator.updateHand(kC);
        Assertions.assertFalse(evaluator.handIsQuads());
        evaluator.updateHand(aH);
        Assertions.assertFalse(evaluator.handIsQuads());
        evaluator.updateHand(tS);
        Assertions.assertFalse(evaluator.handIsQuads());
        evaluator.updateHand(dC);
        Assertions.assertFalse(evaluator.handIsQuads());
        evaluator.updateHand(jD);
        Assertions.assertFalse(evaluator.handIsQuads());
        evaluator.updateHand(aS);
        Assertions.assertFalse(evaluator.handIsQuads());
        evaluator.updateHand(aD);
        Assertions.assertFalse(evaluator.handIsQuads());
        // Testing Quad of Aces work.
        evaluator.updateHand(aC);
        Assertions.assertTrue(evaluator.handIsQuads());
    }

    @Test
    public void testStraightFlush(){
        HandEvaluator evaluator = new HandEvaluator();
        // We're creating cards in the order they'll be added to the board.
        Card jS = new Card(Rank.JACK, Suit.SPADES);
        Card kS = new Card(Rank.KING, Suit.SPADES);
        Card aS = new Card(Rank.ACE, Suit.SPADES);
        Card tS = new Card(Rank.TEN, Suit.SPADES);
        Card dC = new Card(Rank.DEUCE, Suit.CLUBS);
        Card fD = new Card(Rank.FOUR, Suit.DIAMONDS);
        Card qD = new Card(Rank.QUEEN, Suit.DIAMONDS);
        Card qS = new Card(Rank.QUEEN, Suit.SPADES);
        evaluator.updateHand(jS);
        Assertions.assertFalse(evaluator.handIsStraightFlush());
        evaluator.updateHand(kS);
        Assertions.assertFalse(evaluator.handIsStraightFlush());
        evaluator.updateHand(aS);
        Assertions.assertFalse(evaluator.handIsStraightFlush());
        evaluator.updateHand(tS);
        Assertions.assertFalse(evaluator.handIsStraightFlush());
        evaluator.updateHand(dC);
        Assertions.assertFalse(evaluator.handIsStraightFlush());
        evaluator.updateHand(fD);
        Assertions.assertFalse(evaluator.handIsStraightFlush());
        // Testing non-suited flushes don't trigger straight flush.
        evaluator.updateHand(qD);
        Assertions.assertFalse(evaluator.handIsStraightFlush());
        // Testing the royal flush works.
        evaluator.updateHand(qS);
        Assertions.assertTrue(evaluator.handIsStraightFlush());
    }









}
