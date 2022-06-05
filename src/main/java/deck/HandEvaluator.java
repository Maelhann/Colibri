package deck;

import deck.comparators.CardComparator;

import java.util.*;

public class HandEvaluator {
    // TODO : write tests.
    Map<Rank, Integer> rankFreq;
    Map<Suit, Integer> suitFreq;
    List<Card> cards;

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

    public Rank handIsStraightFlush(){
        Rank straightRank = handIsStraight();
        Rank flushRank = handIsFlush();
        boolean isBicyleStraightFlush = (flushRank == Rank.ACE && straightRank == Rank.FIVE);
        if(isBicyleStraightFlush) return Rank.FIVE;
        boolean isStraightFlush = flushRank != null && straightRank == flushRank;
        return isStraightFlush ? straightRank : null;
    }

    public Rank handIsQuads(){
        List<Rank> quadRanks = new ArrayList<>();
        for(Map.Entry<Rank,Integer> entry : this.rankFreq.entrySet()){
            if(entry.getValue() == 4) quadRanks.add(entry.getKey());
        }
        return quadRanks.isEmpty() ? null : Collections.max(quadRanks);
    }

    public Rank handIsFullHouse(){
        // TODO : Problem here is the exact same as two pairs, might need to compare on set or pair
        Rank pairRank = handIsPair();
        Rank setRank = handIsSet();
        if(pairRank != null && setRank != null && pairRank != setRank) return setRank;
        return null;
    }

    public Rank handIsFlush(){
        for(Map.Entry<Suit, Integer> entry : this.suitFreq.entrySet()){
            if(entry.getValue() >= 5) {
                List<Rank> suitedRanks = new ArrayList<>();
                for(Card card : this.cards){
                    if(card.getSuit() == entry.getKey()) suitedRanks.add(card.getRank());
                }
                return Collections.max(suitedRanks);
            }
        }
        return null;
    }

    public Rank handIsStraight(){
        if(cards.size() < 5) return null;
        List<Rank> ranks = new ArrayList<>(rankFreq.keySet());
        Rank straightRank = null;
        for(List<Rank> straight : HandEvaluator.allStraights.keySet()){
            Rank bestRank = HandEvaluator.allStraights.get(straight);
            if(new HashSet<>(ranks).containsAll(straight))
                straightRank = (straightRank == null || straightRank.compareTo(bestRank) < 0) ? bestRank : straightRank;
        }
        return straightRank;
    }

    public Rank handIsSet(){
        List<Rank> setRanks = new ArrayList<>();
        for(Map.Entry<Rank,Integer> entry : this.rankFreq.entrySet()){
            if(entry.getValue() == 3) setRanks.add(entry.getKey());
        }
        return setRanks.isEmpty() ? null : Collections.max(setRanks);
    }

    public Rank handIsTwoPairs(){
        // TODO : Note that you may need to compare two players with two pairs, add an exception field.
        List<Rank> pairRanks = new ArrayList<>();
        for(Map.Entry<Rank,Integer> entry : this.rankFreq.entrySet()){
            if(entry.getValue() == 2) pairRanks.add(entry.getKey());
        }
        if(pairRanks.size() < 2) return null;
        Collections.sort(pairRanks);
        return Collections.max(pairRanks);
    }

    public Rank handIsPair(){
        // Returns the highest pair rank if there's a pair, null otherwise
        List<Rank> pairRanks = new ArrayList<>();
        for(Map.Entry<Rank,Integer> entry : this.rankFreq.entrySet()){
            if(entry.getValue() == 2) pairRanks.add(entry.getKey());
        }
        return pairRanks.isEmpty() ? null : Collections.max(pairRanks);
    }

    public Rank handHighCard(){
        // Returns the highest rank in hand
        return Collections.max(this.cards, new CardComparator()).getRank();
    }
}
