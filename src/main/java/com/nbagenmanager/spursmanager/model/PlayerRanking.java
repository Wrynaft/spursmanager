package com.nbagenmanager.spursmanager.model;

public class PlayerRanking {
    private Player player;
    private double compositeScore;

    public PlayerRanking(Player player, double compositeScore) {
        this.player = player;
        this.compositeScore = compositeScore;
    }

    // Getters and setters
    public Player getPlayer() {
        return player;
    }

    public double getCompositeScore() {
        return compositeScore;
    }

    // Other methods if needed
}
