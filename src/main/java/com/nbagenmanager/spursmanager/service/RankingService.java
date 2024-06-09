package com.nbagenmanager.spursmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nbagenmanager.spursmanager.model.Player;

@Service
public class RankingService {
    private static final double GUARD_POINTS_WEIGHT = 0.35;
    private static final double GUARD_REBOUNDS_WEIGHT = 0.10;
    private static final double GUARD_ASSISTS_WEIGHT = 0.35;
    private static final double GUARD_BLOCKS_WEIGHT = 0.05;
    private static final double GUARD_STEALS_WEIGHT = 0.15;

    private static final double FORWARD_POINTS_WEIGHT = 0.30;
    private static final double FORWARD_REBOUNDS_WEIGHT = 0.30;
    private static final double FORWARD_ASSISTS_WEIGHT = 0.20;
    private static final double FORWARD_BLOCKS_WEIGHT = 0.10;
    private static final double FORWARD_STEALS_WEIGHT = 0.10;

    private static final double CENTER_POINTS_WEIGHT = 0.20;
    private static final double CENTER_REBOUNDS_WEIGHT = 0.35;
    private static final double CENTER_ASSISTS_WEIGHT = 0.05;
    private static final double CENTER_BLOCKS_WEIGHT = 0.35;
    private static final double CENTER_STEALS_WEIGHT = 0.05;

    private static final double THEORETICAL_MAX_GUARD_SCORE = 37 * GUARD_POINTS_WEIGHT + 16 * GUARD_REBOUNDS_WEIGHT + 12 * GUARD_ASSISTS_WEIGHT + 3 * GUARD_STEALS_WEIGHT + 4 * GUARD_BLOCKS_WEIGHT;
    private static final double THEORETICAL_MAX_FORWARD_SCORE = 37 * FORWARD_POINTS_WEIGHT + 16 * FORWARD_REBOUNDS_WEIGHT + 12 * FORWARD_ASSISTS_WEIGHT + 3 * FORWARD_STEALS_WEIGHT + 4 * FORWARD_BLOCKS_WEIGHT;
    private static final double THEORETICAL_MAX_CENTER_SCORE = 37 * CENTER_POINTS_WEIGHT + 16 * CENTER_REBOUNDS_WEIGHT + 12 * CENTER_ASSISTS_WEIGHT + 3 * CENTER_STEALS_WEIGHT + 4 * CENTER_BLOCKS_WEIGHT;

    public double calculateCompositeScore(Player player) {
        double compositeScore;
        String position = player.getPosition().charAt(0) + "";  // Extract the first character of the position string
    
        if (position.equals("G")) {
            compositeScore = GUARD_POINTS_WEIGHT * player.getPoints() + GUARD_REBOUNDS_WEIGHT * player.getRebound() + GUARD_ASSISTS_WEIGHT * player.getAssists() + GUARD_STEALS_WEIGHT * player.getSteals() + GUARD_BLOCKS_WEIGHT * player.getBlocks();
            compositeScore /= THEORETICAL_MAX_GUARD_SCORE;
        } else if (position.equals("F")) {
            compositeScore = FORWARD_POINTS_WEIGHT * player.getPoints() + FORWARD_REBOUNDS_WEIGHT * player.getRebound() + FORWARD_ASSISTS_WEIGHT * player.getAssists() + FORWARD_STEALS_WEIGHT * player.getSteals() + FORWARD_BLOCKS_WEIGHT * player.getBlocks();
            compositeScore /= THEORETICAL_MAX_FORWARD_SCORE;
        } else {
            compositeScore = CENTER_POINTS_WEIGHT * player.getPoints() + CENTER_REBOUNDS_WEIGHT * player.getRebound() + CENTER_ASSISTS_WEIGHT * player.getAssists() + CENTER_STEALS_WEIGHT * player.getSteals() + CENTER_BLOCKS_WEIGHT * player.getBlocks();
            compositeScore /= THEORETICAL_MAX_CENTER_SCORE;
        }
    
        return compositeScore;
    }

    public List<Player> rankPlayers(List<Player> players) {
        List<Player> rankedPlayers = new ArrayList<>(players);
        rankedPlayers.sort((p1, p2) -> Double.compare(calculateCompositeScore(p2), calculateCompositeScore(p1)));
        return rankedPlayers;
    }
}
