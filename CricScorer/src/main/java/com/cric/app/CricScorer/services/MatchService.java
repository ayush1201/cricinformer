package com.cric.app.CricScorer.services;

import com.cric.app.CricScorer.entities.Match;

import java.util.List;

public interface MatchService{
    //    Defines the methods that the service must implement. This allows
//    You to define the contract (methods) without worrying about the implementation details
    List<Match> getAllMatches();

    List<Match>getLiveMatches();

    List<List<String>>getPointTable();
}
