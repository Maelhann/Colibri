package deck;

import deck.comparators.CardComparator;
import java.util.*;
import java.util.stream.Collectors;

public class HandEvaluator {
    Map<Rank, Integer> rankFreq;
    Map<Suit, Integer> suitFreq;
    List<Card> cards;
    Map<RankType, Rank> ranksMap;
    static HashMap<List<Rank>, Rank> allStraights = new HashMap<>(){
        {
            put(new ArrayList<Rank>(){{
                add(Rank.ACE);
                add(Rank.DEUCE);
                add(Rank.TREY);
                add(Rank.FOUR);
                add(Rank.FIVE);
            }}, Rank.FIVE);
            put(new ArrayList<Rank>(){{
                add(Rank.DEUCE);
                add(Rank.TREY);
                add(Rank.FOUR);
                add(Rank.FIVE);
                add(Rank.SIX);
            }}, Rank.SIX);
            put(new ArrayList<Rank>(){{
                add(Rank.TREY);
                add(Rank.FOUR);
                add(Rank.FIVE);
                add(Rank.SIX);
                add(Rank.SEVEN);
            }}, Rank.SEVEN);
            put(new ArrayList<Rank>(){{
                add(Rank.FOUR);
                add(Rank.FIVE);
                add(Rank.SIX);
                add(Rank.SEVEN);
                add(Rank.EIGHT);
            }}, Rank.EIGHT);
            put(new ArrayList<Rank>(){{
                add(Rank.FIVE);
                add(Rank.SIX);
                add(Rank.SEVEN);
                add(Rank.EIGHT);
                add(Rank.NINE);
            }}, Rank.NINE);
            put(new ArrayList<Rank>(){{
                add(Rank.SIX);
                add(Rank.SEVEN);
                add(Rank.EIGHT);
                add(Rank.NINE);
                add(Rank.TEN);
            }}, Rank.TEN);
            put(new ArrayList<Rank>(){{
                add(Rank.SEVEN);
                add(Rank.EIGHT);
                add(Rank.NINE);
                add(Rank.TEN);
                add(Rank.JACK);
            }}, Rank.JACK);
            put(new ArrayList<Rank>(){{
                add(Rank.EIGHT);
                add(Rank.NINE);
                add(Rank.TEN);
                add(Rank.JACK);
                add(Rank.QUEEN);
            }}, Rank.QUEEN);
            put(new ArrayList<Rank>(){{
                add(Rank.NINE);
                add(Rank.TEN);
                add(Rank.JACK);
                add(Rank.QUEEN);
                add(Rank.KING);
            }}, Rank.KING);
            put(new ArrayList<Rank>(){{
                add(Rank.TEN);
                add(Rank.JACK);
                add(Rank.QUEEN);
                add(Rank.KING);
                add(Rank.ACE);
            }}, Rank.ACE);
        }
    };

    HandEvaluator(List<Card> cards){
        Map<Rank, Integer> rankFreq = new HashMap<>();
        Map<Suit, Integer> suitFreq = new HashMap<>();
        for(Card card : cards){
            rankFreq.put(card.getRank(), rankFreq.getOrDefault(card.getRank(),0)+1);
            suitFreq.put(card.getSuit(), suitFreq.getOrDefault(card.getSuit(), 0)+1);
        }
        this.rankFreq = rankFreq;
        this.suitFreq = suitFreq;
        this.cards = cards;
    }

    public boolean handIsStraightFlush(){
        if(!handIsStraight() || !handIsFlush()) return false;
        Suit flushColour = suitFreq.entrySet().stream().filter(entry -> entry.getValue() >= 5).map(Map.Entry::getKey).toList().get(0);
        List<Rank> colouredRanks = new ArrayList<>(cards.stream().filter(card -> card.getSuit() == flushColour).map(Card::getRank).toList());
        Collections.sort(colouredRanks);
        if(allStraights.containsKey(colouredRanks)){
            this.ranksMap.put(RankType.STRAIGHT_FLUSH_RANK, allStraights.get(colouredRanks));
            return true;
        }
        return false;
    }

    public boolean handIsQuads(){
        List<Rank> quadRanks = this.rankFreq.entrySet().stream().filter(entry -> entry.getValue() == 4).map(Map.Entry::getKey).toList();
        if(!quadRanks.isEmpty()) this.ranksMap.put(RankType.QUAD_RANK, Collections.max(quadRanks));
        return !quadRanks.isEmpty();
    }

    public boolean handIsFullHouse(){
        return handIsSet() && handIsPair();
    }

    public boolean handIsFlush(){
        if(this.ranksMap.containsKey(RankType.FLUSH_RANK)) return true;
        for(Map.Entry<Suit, Integer> entry : this.suitFreq.entrySet()){
            if(entry.getValue() >= 5) {
                List<Rank> suitedRanks = this.cards.stream()
                        .filter(card -> card.getSuit() == entry.getKey()).map(Card::getRank)
                        .toList();
                this.ranksMap.put(RankType.FLUSH_RANK,Collections.max(suitedRanks));
                return true;
            }
        }
        return false;
    }

    public boolean handIsStraight(){
        if(this.ranksMap.containsKey(RankType.STRAIGHT_RANK)) return true;
        if(cards.size() < 5) return false;
        List<Rank> ranks = new ArrayList<>(rankFreq.keySet());
        Rank straightRank = null;
        for(List<Rank> straight : HandEvaluator.allStraights.keySet()){
            Rank bestRank = HandEvaluator.allStraights.get(straight);
            if(new HashSet<>(ranks).containsAll(straight))
                straightRank = (straightRank == null || straightRank.compareTo(bestRank) < 0) ? bestRank : straightRank;
        }
        if(straightRank == null) return false;
        this.ranksMap.put(RankType.STRAIGHT_RANK, straightRank);
        return true;
    }

    public boolean handIsSet(){
        if(this.ranksMap.containsKey(RankType.SET_RANK)) return true;
        List<Rank> setRanks = new ArrayList<>();
        for(Map.Entry<Rank,Integer> entry : this.rankFreq.entrySet()){
            if(entry.getValue() == 3) setRanks.add(entry.getKey());
        }
        if(setRanks.isEmpty()) return false;
        List<Rank> excludedRanks = new ArrayList<>();
        excludedRanks.add(Collections.max(setRanks));
        this.ranksMap.put(RankType.SET_RANK, Collections.max(setRanks));
        return handHighCard(excludedRanks);
    }

    public boolean handIsTwoPairs(){
        List<Rank> pairRanks = new ArrayList<>();
        for(Map.Entry<Rank,Integer> entry : this.rankFreq.entrySet()){
            if(entry.getValue() == 2) pairRanks.add(entry.getKey());
        }
        if(pairRanks.size() < 2) return false;
        Collections.sort(pairRanks);
        this.ranksMap.put(RankType.TOP_PAIR_RANK, pairRanks.get(0));
        this.ranksMap.put(RankType.SEC_PAIR_RANK,pairRanks.get(1));
        List<Rank> excludedRanks = new ArrayList<>(Arrays.asList(pairRanks.get(0), pairRanks.get(1)));
        return handHighCard(excludedRanks);
    }

    public boolean handIsPair(){
        // Avoid overhead from Full house computation.
        if(ranksMap.containsKey(RankType.TOP_PAIR_RANK)) return true;
        Rank setRank = this.ranksMap.getOrDefault(RankType.SET_RANK, null);
        List<Rank> pairRanks = new ArrayList<>();
        for(Map.Entry<Rank,Integer> entry : this.rankFreq.entrySet()){
            if(entry.getValue() == 2 && (setRank == null || setRank != entry.getKey())) pairRanks.add(entry.getKey());
        }
        if(pairRanks.isEmpty()) return false;
        Rank maxRank = Collections.max(pairRanks);
        this.ranksMap.put(RankType.TOP_PAIR_RANK, maxRank);
        handHighCard(new ArrayList<>(Collections.singletonList(maxRank)));
        return true;
    }

    public boolean handHighCard(){
        this.ranksMap.put(RankType.KICKER_RANK, Collections.max(this.cards, new CardComparator()).getRank());
        return true;
    }

    public boolean handHighCard(List<Rank> excludedRanks){
        Collections.sort(this.cards, new CardComparator());
        for(int i = 0; i < this.cards.size();i++){
            Rank curr = this.cards.get(i).getRank();
            if(!excludedRanks.contains(curr)) {
                this.ranksMap.put(RankType.KICKER_RANK, curr);
                return true;
            }
        }
        return false;
    }
}
