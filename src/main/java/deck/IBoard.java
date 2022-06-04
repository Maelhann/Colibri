package deck;

public interface IBoard {
    void addCard(Card card);
    void clearHand();
    Hand evaluateBestHand();
}
