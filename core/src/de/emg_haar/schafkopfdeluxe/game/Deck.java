package de.emg_haar.schafkopfdeluxe.game;

import java.util.Collections;
import java.util.Stack;


public class Deck {

    private Stack<Card> cards = new Stack<>();

    public Deck() {
        //Karten werden erzeugt
        for (CardType type : CardType.values()) {
            for (CardRank rank : CardRank.values()) {
                Card card = new Card(type, rank);
                cards.push(card);
            }
        }
        Collections.shuffle(cards);
    }

    public void initialize(Stack<Card> prevDumpedCards)
    {
        //Hier kommt die Logik zum nicht perfekten Mischen rein
    }

    //Packt 4 Karten vom Stack in ein neues Stack und gibt dieses zurueck
    public Stack<Card> deal() {
        Stack<Card> deal = new Stack<>();
        deal.addAll(cards.subList(0, 4));
        cards.subList(0, 4).clear();
        return deal;
    }

}
