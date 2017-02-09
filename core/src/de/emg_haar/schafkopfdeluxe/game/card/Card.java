package de.emg_haar.schafkopfdeluxe.game.card;

/**
 * Created by NoahB on 26.01.17.
 */

public class Card {
    private CardColor color;
    private CardRank rank;

    public Card(CardColor c, CardRank r) {
        color = c;
        rank = r;
    }

    public CardRank getRank()
    {
        return rank;
    }

    public CardColor getColor()
    {
        return color;
    }

    public int getPoints() {
        return rank.getPoints();
    }
}
