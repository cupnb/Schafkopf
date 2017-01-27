package de.emg_haar.schafkopfdeluxe.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Sebi on 25.11.2016.
 */

public class Deck {
    private Stack<Card> cards = new Stack<>();

    public Deck() {
        initialize();
    }

    public void initialize()
    {
        for (CardType type : CardType.values()) {
            for (CardRank rank : CardRank.values()) {
                Card card = new Card(type, rank);
                cards.push(card);
            }
        }
        Collections.shuffle(cards);
    }

    public Stack<Card> deal() {
        Stack<Card> deal = new Stack<>();
        deal.addAll(cards.subList(0, 4));
        cards.subList(0, 4).clear();
        return deal;
    }

    public boolean isDealt() {
        return (cards.size() == 0);
    }
}
