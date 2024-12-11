package com.cric.app.CricScorer.repositories;

import com.cric.app.CricScorer.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MatchRepo extends JpaRepository<Match,Integer> {
    //Match fetch on basis of team name
    Optional<Match> findByTeamHeading(String teamHeading);
}
