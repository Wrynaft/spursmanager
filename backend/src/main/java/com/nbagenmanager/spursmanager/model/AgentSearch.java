/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nbagenmanager.spursmanager.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ryan Chin
 */

//This class is just to consolidate the search parameters and list of free agents, so that they can be both passed in 1 request body by API
public class AgentSearch {
    public List<Player> agentList; //List of free agents
    public Map<String, String> searchParams; //Search parameters

    public AgentSearch() {
    }

    public AgentSearch(List<Player> agentList, Map<String, String> searchParams) {
        this.agentList = agentList;
        this.searchParams = searchParams;
    }

    public List<Player> getAgentList() {
        return agentList;
    }

    public Map<String, String> getSearchParams() {
        return searchParams;
    }

    public void setAgentList(List<Player> agentList) {
        this.agentList = agentList;
    }

    public void setSearchParams(Map<String, String> searchParams) {
        this.searchParams = searchParams;
    }
}
