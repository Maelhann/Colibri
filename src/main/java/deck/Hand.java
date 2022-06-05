package deck;
import java.util.Map;

public class Hand {
    private final HandType type;
    private final Map<RankType,Rank> rankData;

    Hand(HandType type, Map<RankType,Rank> rankData){
        this.type = type;
        this.rankData= rankData;
    }

    public HandType getType() {
        return type;
    }

    public Map<RankType, Rank> getRankData() {
        return rankData;
    }
}
