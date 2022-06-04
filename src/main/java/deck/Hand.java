package deck;

public class Hand {
    private final HandType type;
    private final Rank handRank;

    Hand(HandType type, Rank handRank){
        this.type = type;
        this.handRank = handRank;
    }

    public HandType getType() {
        return type;
    }

    public Rank getHandRank() {
        return handRank;
    }
}
