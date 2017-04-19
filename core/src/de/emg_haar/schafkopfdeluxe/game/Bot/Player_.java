//Wäre ganz cool Alex, wenn du hier die Kommentare reinmachen könntest
//Ulli

package de.emg_haar.schafkopfdeluxe.game.Bot;

import de.emg_haar.schafkopfdeluxe.game.Game;
import de.emg_haar.schafkopfdeluxe.game.card.Card;
import java.util.List;
import java.util.Stack;

public abstract class Player_ {


    public abstract String getName();
    public abstract void setGame( Game g );
    public abstract void setPlayer( boolean p );
    // Was ist das? - Alex D.

    //Damit wird festgelegt, wer mit wem in einem Team ist. Wenn du beispielsweise jetzt auf das Eichelass spielst, wird bei dir und bei der Person, die das Eichelass hat Player gesettet
    //Ulli
    public abstract boolean getPlayer();

    public abstract void yourTurn();

    public abstract void addCards(Stack<Card> c);
    //Habe jetzt einfach mal den Stack oben reingetan; denke mal, dass du den vergessen hast
    //Ulli
    public abstract List <Card> getHand();
    public abstract void showPlayableCards(List <Card> l);

}
