package de.emg_haar.schafkopfdeluxe.game.card;

/**
 * Created by Sebi on 04.02.2017.
 */
public enum CardColor {
    EICHEL("eichel"),
    LAUB("laub"),
    HERZ("herz"),
    SCHELLEN("schellen");

    private String name;

    CardColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
