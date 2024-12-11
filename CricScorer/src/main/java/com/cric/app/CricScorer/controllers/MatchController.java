package com.cric.app.CricScorer.controllers;

import com.cric.app.CricScorer.entities.Match;
import com.cric.app.CricScorer.services.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/match")
@CrossOrigin("*")
//The controller is responsible for handling HTTP requests from the
//client, processing them(often by calling a service layer),
//and then returning the appropriate HTTP response.
public class MatchController {
    private MatchService matchService;
    public MatchController(MatchService matchService){
        this.matchService=matchService;
    }
    @GetMapping("/live")
    //get live matches
    public ResponseEntity<List<Match>>getLiveMatches(){
        return new ResponseEntity<>(this.matchService.getLiveMatches(), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Match>>getAllMatches(){
        return new ResponseEntity<>(this.matchService.getAllMatches(), HttpStatus.OK);
    }
    //get point table
    @GetMapping("/point-table")
    public ResponseEntity<?>getPointTable(){
        return new ResponseEntity<>(this.matchService.getPointTable(), HttpStatus.OK);
    }
}
