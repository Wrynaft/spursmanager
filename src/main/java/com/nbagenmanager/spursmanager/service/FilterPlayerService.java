package com.nbagenmanager.spursmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nbagenmanager.spursmanager.model.Player;

@Service
public class FilterPlayerService {
    
    public List<Player> filterPlayers(List<Player> players, String attribute, String comparator, Object value) {
        List<Player> filteredPlayers = new ArrayList<>();

        for (Player player : players) {
            switch (attribute) {
                case "height":
                    filteredPlayers = filterByHeight(players, comparator, (double) value);
                    break;
                case "weight":
                    filteredPlayers = filterByWeight(players, comparator, (double) value);
                    break;
                case "age":
                    filteredPlayers = filterByAge(players, comparator, (int) value);
                    break;
                case "position":
                    filteredPlayers = filterByPosition(players, comparator, (String) value);
                    break;
                case "points":
                    filteredPlayers = filterByPoints(players, comparator, (double) value);
                    break;
                case "rebounds":
                    filteredPlayers = filterByRebound(players, comparator, (double) value);
                    break;
                case "assists":
                    filteredPlayers = filterByAssists(players, comparator, (double) value);
                    break;
                case "steals":
                    filteredPlayers = filterBySteals(players, comparator, (double) value);
                    break;
                case "blocks":
                    filteredPlayers = filterByBlocks(players, comparator, (double) value);
                    break;
                // Add more cases for other attributes as needed
                default:
                    break;
            }
        }

        return filteredPlayers;
    }

    // Filter methods based on attributes below
    private List<Player> filterByHeight(List<Player> players, String comparator, double height) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case ">=":
                    if (player.getHeight() >= height) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "<=":
                    if (player.getHeight() <= height) {
                        filteredPlayers.add(player);
                    }
                    break;
                // Add more cases for other comparators as needed
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }

    private List<Player> filterByWeight(List<Player> players, String comparator, double weight) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case ">=":
                    if (player.getWeight() >= weight) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "<=":
                    if (player.getWeight() <= weight) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    private List<Player> filterByPoints(List<Player> players, String comparator, double points) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case ">=":
                    if (player.getPoints() >= points) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "<=":
                    if (player.getPoints() <= points) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    private List<Player> filterByRebound(List<Player> players, String comparator, double rebound) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case ">=":
                    if (player.getRebound() >= rebound) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "<=":
                    if (player.getRebound() <= rebound) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    private List<Player> filterByAssists(List<Player> players, String comparator, double assists) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case ">=":
                    if (player.getAssists() >= assists) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "<=":
                    if (player.getAssists() <= assists) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    private List<Player> filterBySteals(List<Player> players, String comparator, double steals) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case ">=":
                    if (player.getSteals() >= steals) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "<=":
                    if (player.getSteals() <= steals) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    private List<Player> filterByBlocks(List<Player> players, String comparator, double blocks) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case ">=":
                    if (player.getBlocks() >= blocks) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "<=":
                    if (player.getBlocks() <= blocks) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }

    private List<Player> filterByAge(List<Player> players, String comparator, int age) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case "=":
                    if (player.getAge() == age) {
                        filteredPlayers.add(player);
                    }
                    break;
                case ">=":
                    if (player.getAge() >= age) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "<=":
                    if (player.getAge() <= age) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }

    private List<Player> filterByPosition(List<Player> players, String comparator, String position) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case "=":
                    if (player.getPosition().equals(position)) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
}
