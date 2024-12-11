package com.cric.app.CricScorer.services.impl;

import com.cric.app.CricScorer.entities.Match;
import com.cric.app.CricScorer.repositories.MatchRepo;
import com.cric.app.CricScorer.services.MatchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    private MatchRepo matchRepo;
//    This class implements the MatchService interface and provides the
//        business logic for your application, such as fetching live
//    matches, all matches, and the points table.

    //    This is an instance of your repository interface that provides
//            methods to interact with the database.
    public MatchServiceImpl(MatchRepo matchRepo) {
        this.matchRepo = matchRepo;
    }
    //match history all matches in our DB
    @Override
    public List<Match> getAllMatches() {
//        Purpose: Retrieves all matches stored in the database.
//                matchRepo.findAll(): Uses the MatchRepo repository
//        to fetch all records from the crick_matches table.
        return this.matchRepo.findAll();
    }

    @Override
    public List<Match> getLiveMatches() {
//        Purpose: Scrapes live match data from Cricbuzz and returns a list of
//        Match objects.
//        Jsoup: A library used to parse the HTML content of the live
//        scores page.
//        Document, Elements, and Element: Used to navigate the
//        HTML DOM and extract specific elements.
        List<Match> matches = new ArrayList<>();
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";

            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                //getting match link
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                Match match1 = new Match();
                match1.setTeamHeading(teamsHeading);
                match1.setMatchNumberVenue(matchNumberVenue);
                match1.setBattingTeam(battingTeam);
                match1.setBattingTeamScore(score);
                match1.setBowlTeam(bowlTeam);
                match1.setBowlTeamScore(bowlTeamScore);
                match1.setLiveText(textLive);
                match1.setMatchLink(matchLink);
                match1.setTextComplete(textComplete);
                match1.setMatchStatus();


                matches.add(match1);

//                update the match in database


                updateMatch(match1);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }
    private void updateMatch(Match match1) {
//        Checks if the match already exists in the database based on the
//        teamHeading. If it does, it updates the existing match;
//        otherwise, it inserts a new record.
        Match match = this.matchRepo.findByTeamHeading(match1.getTeamHeading()).orElse(null);
        if (match == null) {
            this.matchRepo.save(match1);
        } else {

            match1.setMatchId(match.getMatchId());
            this.matchRepo.save(match1);
        }

    }

    @Override
//    Scrapes and returns the points table from Cricbuzz.
//    Flow:
//    Connects to the points table page.
//    Extracts headers and body rows.
//    Builds a list of lists containing the points table data.
    public List<List<String>> getPointTable() {
        List<List<String>> pointTable = new ArrayList<>();
        String tableURL = "https://www.cricbuzz.com/cricket-series/7607/indian-premier-league-2024/points-table";
        try {
            Document document = Jsoup.connect(tableURL).get();
            Elements table = document.select("table.cb-srs-pnts");
            Elements tableHeads = table.select("thead>tr>*");
            List<String> headers = new ArrayList<>();
            tableHeads.forEach(element -> {
                headers.add(element.text());
            });
            pointTable.add(headers);
            Elements bodyTrs = table.select("tbody>*");
            bodyTrs.forEach(tr -> {
                List<String> points = new ArrayList<>();
                if (tr.hasAttr("class")) {
                    Elements tds = tr.select("td");
                    String team = tds.get(0).select("div.cb-col-84").text();
                    points.add(team);
                    tds.forEach(td -> {
                        if (!td.hasClass("cb-srs-pnts-name")) {
                            points.add(td.text());
                        }
                    });
//                    System.out.println(points);
                    pointTable.add(points);
                }


            });

            System.out.println(pointTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pointTable;
    }
}
