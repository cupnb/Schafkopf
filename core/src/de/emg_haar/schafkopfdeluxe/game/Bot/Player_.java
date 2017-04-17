package de.emg_haar.schafkopfdeluxe.game.Bot;

import de.emg_haar.schafkopfdeluxe.game.Game;
import de.emg_haar.schafkopfdeluxe.game.card.Card;
import java.util.List;

public abstract class Player_ {


    public abstract String getName();
    public abstract void setGame( Game g );
    public abstract void setPlayer( boolean p );
    // Was ist das? - Alex D.
    public abstract boolean getPlayer();
    public abstract boolean getContra();
    public abstract boolean getRe();
    public abstract void yourTurn();

    public abstract void addCards();
    public abstract List <Card> getHand();
    public abstract void showPlayableCards(List <Card> l);

}
