package deck;

public interface IHandEvaluator {
    boolean handIsStraightFlush();
    boolean handIsQuads();
    boolean handIsFullHouse();
    boolean handIsFlush();
    boolean handIsStraight();
    boolean handIsSet();
    boolean handIsTwoPairs();
    boolean handIsPair();
    boolean handHighCard();
    Hand evaluateBestHand();
    void updateHand(Card card);
    void clearHand();
}
