package de.emg_haar.schafkopfdeluxe.game.Bots;
import de.emg_haar.schafkopfdeluxe.game.Player;

import de.emg_haar.schafkopfdeluxe.game.Player;
import de.emg_haar.schafkopfdeluxe.game.card.Card;

public class Bot extends Player {

    protected Card[][] matrixBot;
    public Bot()
    {
        super("Nathanael");
        matrixBot = new Card[4][8];
    }

}