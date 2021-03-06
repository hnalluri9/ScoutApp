/*Team 3637 Scouting App - An application for data collection/analytics at FIRST competitions
 Copyright (C) 2016  Team 3637

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.team3637.controller.teams;

import com.team3637.model.Team;
import com.team3637.service.MatchService;
import com.team3637.service.TagService;
import com.team3637.service.TeamService;
import com.team3637.wrapper.TeamWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

@Controller
public class TeamsController {

    @Autowired
    private MatchService matchService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private TagService tagService;
    @Autowired
    private ServletContext context;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String listTeams(Model model) {
        List<Team> teams = teamService.getTeams();
        model.addAttribute("teamWrapper", new TeamWrapper(teams, new boolean[teams.size()]));
        return "team-list";
    }

    @RequestMapping(value = "/view/", method = RequestMethod.POST)
    public String listTeams(@ModelAttribute("teamWrapper") TeamWrapper wrapper) {
        if (wrapper.getTeams() != null && wrapper.getTeams().size() > 0) {
            for (int i = 0; i < wrapper.getTeams().size(); i++) {
                if (wrapper.getDeleted()[i]) {
                    teamService.delete(wrapper.getTeams().get(i));
                }
            }
        }
        return "redirect:" + context.getContextPath() + "/t/";
    }

    @RequestMapping(value = "/view/{teamNum}", method = RequestMethod.GET)
    public String getTeam(@PathVariable("teamNum") Integer teamNum, Model model) {
        if(teamService.checkForTeam(teamNum)) {
            Team team = teamService.getTeam(teamNum);
            List<String> matchTags = tagService.getMatchUniqueTagStringsForTeam(teamNum);
            List<String> teamTags = teamService.getTags();
            model.addAttribute("team", team);
            model.addAttribute("matchTags", matchTags);
            model.addAttribute("teamTags", teamTags);
            return "team";
        } else {
            Team team = new Team();
            team.setTeam(teamNum);
            List<String> teamTags = teamService.getTags();
            model.addAttribute("team", team);
            model.addAttribute("teamTags", teamTags);
            return "team";
        }
    }

    @RequestMapping(value = "/view/{teamNum}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> getTeam(@PathVariable("teamNum") Integer teamNum,
                                     @RequestParam("teamTags") String teamTags,
                                     @RequestParam("score") Double score,
                                     @RequestParam("matches") Integer matches) {
        Team team = new Team();
        team.setTeam(teamNum);
        team.setAvgscore(score);
        team.setMatches(matches);
        if (!teamTags.equals(""))
            team.setTags(new ArrayList<>(new LinkedHashSet<>(Arrays.asList(teamTags.split(", ")))));

        List<String> teamTagList = teamService.getTags();
        for (int i = 0; i < team.getTags().size(); i++) {
            if (!teamTagList.contains(team.getTags().get(i))) {
                team.getTags().remove(i);
            }
        }

        if (team.getTags().size() < 0 || team.getTags().size() > 50)
            return new ResponseEntity<>("400 - Bad Request", HttpStatus.BAD_REQUEST);
        else {
            if (teamService.checkForTeam(team.getTeam()))
                teamService.update(team);
            else
                teamService.create(team);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", context.getContextPath() + "/t/");
        return new ResponseEntity<byte[]>(null, headers, HttpStatus.FOUND);
    }


    @RequestMapping(value = "/search/", method = RequestMethod.GET)
    public String search(Model model) {
        List<String> matchTags = matchService.getTags();
        List<String> teamTags = teamService.getTags();
        Double[] scoreRange = teamService.getScoreRange();
        model.addAttribute("matchTags", matchTags);
        model.addAttribute("teamTags", teamTags);
        model.addAttribute("minScore", scoreRange[0]);
        model.addAttribute("maxScore", scoreRange[1]);
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("matchTags") String matchTagsString,
                         @RequestParam("teamTags") String teamTagsString,
                         @RequestParam("score") String score,
                         Model model) {
        String[] matchTags = !matchTagsString.equals("") ? matchTagsString.split(",") : new String[]{};
        String[] teamTags = !teamTagsString.equals("") ? teamTagsString.split(",") : new String[]{};
        String[] temp = score.split(",");
        double minScore = Double.parseDouble(temp[0]);
        double maxScore = Double.parseDouble(temp[1]);
        List<Team> teams = tagService.search(minScore, maxScore, matchTags, teamTags);
        model.addAttribute("teams", teams);
        return "search-results";
    }
}
