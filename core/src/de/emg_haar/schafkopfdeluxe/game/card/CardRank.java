package de.emg_haar.schafkopfdeluxe.game.card;

/**
 * Created by Sebi on 04.02.2017.
 */

public enum CardRank {
    ASS("ass", 11),
    ZEHN("zehn", 10),
    KOENIG("koenig", 4),
    OBER("ober", 3),
    UNTER("unter", 2),
    NEUN("neun", 0),
    ACHT("acht", 0),
    SIEBEN("sieben", 0);

    private String name;
    private int points;

    CardRank(String displayName, int points) {
        this.name = displayName;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
