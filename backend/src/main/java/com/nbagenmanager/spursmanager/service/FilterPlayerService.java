package com.nbagenmanager.spursmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nbagenmanager.spursmanager.model.Player;

@Service
public class FilterPlayerService {

    // Filter methods based on attributes below
    public List<Player> filterByHeight(List<Player> players, String comparator, double height) {
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
                case "==":
                    if (player.getHeight() == height) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }

    public List<Player> filterByWeight(List<Player> players, String comparator, double weight) {
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
                case "==":
                    if (player.getWeight() == weight) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    public List<Player> filterBySalary(List<Player> players, String comparator, double salary) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case ">=":
                    if (player.getSalary() >= salary) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "<=":
                    if (player.getPoints() <= salary) {
                        filteredPlayers.add(player);
                    }
                    break;
                case "==":
                    if (player.getPoints() == salary) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    public List<Player> filterByPoints(List<Player> players, String comparator, double points) {
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
                case "==":
                    if (player.getPoints() == points) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    public List<Player> filterByRebound(List<Player> players, String comparator, double rebound) {
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
                case "==":
                    if (player.getRebound() == rebound) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    public List<Player> filterByAssists(List<Player> players, String comparator, double assists) {
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
                case "==":
                    if (player.getAssists() == assists) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    public List<Player> filterBySteals(List<Player> players, String comparator, double steals) {
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
                case "==":
                    if (player.getSteals() == steals) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }
    
    public List<Player> filterByBlocks(List<Player> players, String comparator, double blocks) {
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
                case "==":
                    if (player.getBlocks() == blocks) {
                        filteredPlayers.add(player);
                    }
                    break;
                default:
                    break;
            }
        }
    
        return filteredPlayers;
    }

    public List<Player> filterByAge(List<Player> players, String comparator, int age) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            switch (comparator) {
                case "==":
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
    
    //Return result as long as the attribute contains the key regardless of case
    public List<Player> filterByPosition(List<Player> players, String position) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            if (player.getPosition().contains(position)){
                filteredPlayers.add(player);
            }
        }
    
        return filteredPlayers;
    }
    
    public List<Player> filterByName(List<Player> players, String name) {
        List<Player> filteredPlayers = new ArrayList<>();
    
        for (Player player : players) {
            if (player.getName().toUpperCase().contains(name.toUpperCase())){
                filteredPlayers.add(player);
            }
        }
    
        return filteredPlayers;
    }
}
