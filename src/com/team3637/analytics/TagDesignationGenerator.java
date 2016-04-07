package com.team3637.analytics;

import com.team3637.model.Tag;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagDesignationGenerator {
    private Map<String, Integer> counters;

    public TagDesignationGenerator() {
        counters = new HashMap<>();
    }

    public String generateDesignation(int teamNum, double avgScore, List<Tag> tags) {
        processTags(tags);

        String designation, temp, temp2, temp3;
        int tempNum;

        designation = "{ " + teamNum + " | " + String.format("%.2f", avgScore) + " - ";

        //Auton
        temp = "A(";
        tempNum = convertToTint(counters.get("auto carry"));
        if (tempNum > 0)
            temp += "B" + tempNum + " ";
        tempNum = convertToTint(counters.get("auto reach"));
        if (tempNum > 0)
            temp += "R" + tempNum + " ";

        temp2 = " C(";
        tempNum = convertToTint(counters.get("auto cross l"));
        if (tempNum > 0)
            temp2 += "l" + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross p"));
        if (tempNum > 0)
            temp2 += "p" + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross c"));
        if (tempNum > 0)
            temp2 += "c" + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross m"));
        if (tempNum > 0)
            temp2 += "m" + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross r"));
        if (tempNum > 0)
            temp2 += "r" + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross d"));
        if (tempNum > 0)
            temp2 += "d" + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross s"));
        if (tempNum > 0)
            temp2 += "s" + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross w"));
        if (tempNum > 0)
            temp2 += "w" + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross t"));
        if (tempNum > 0)
            temp2 += "t" + tempNum + " ";
        if (temp2.length() > 3)
            temp += temp2.trim() + ") ";

        temp2 = "H";
        tempNum = convertToTint(counters.get("auto high goal"));
        if (tempNum > 0)
            temp2 += tempNum + " ";
        tempNum = convertToTint(counters.get("auto high fail"));
        if (tempNum > 0)
            temp2 += "f" + tempNum + " ";
        if (temp2.length() > 1)
            temp += temp2.trim();
        temp2 = "L";
        tempNum = convertToTint(counters.get("auto low goal"));
        if (tempNum > 0)
            temp2 += tempNum + " ";
        tempNum = convertToTint(counters.get("auto low fail"));
        if (tempNum > 0)
            temp2 += "f" + tempNum + " ";
        if (temp2.length() > 1)
            temp += temp2.trim();
        if (temp.length() > 2)
            designation += temp.trim() + ") ";

        //Outerworks
        temp2 = " Fast";
        tempNum = convertToTint(counters.get("low bar fast"));
        if (tempNum > 0)
            temp2 += "L" + tempNum + " ";
        temp3 = "A";
        tempNum = convertToTint(counters.get("portcullis fast"));
        if (tempNum > 0)
            temp3 += "p" + tempNum + " ";
        tempNum = convertToTint(counters.get("cheval fast"));
        if (tempNum > 0)
            temp3 += "c" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "B";
        tempNum = convertToTint(counters.get("moat fast"));
        if (tempNum > 0)
            temp3 += "m" + tempNum + " ";
        tempNum = convertToTint(counters.get("ramparts fast"));
        if (tempNum > 0)
            temp3 += "r" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "C";
        tempNum = convertToTint(counters.get("drawbridge fast"));
        if (tempNum > 0)
            temp3 += "d" + tempNum + " ";
        tempNum = convertToTint(counters.get("sally fast"));
        if (tempNum > 0)
            temp3 += "s" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "D";
        tempNum = convertToTint(counters.get("rock wall fast"));
        if (tempNum > 0)
            temp3 += "w" + tempNum + " ";
        tempNum = convertToTint(counters.get("rough terrain fast"));
        if (tempNum > 0)
            temp3 += "t" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        if (temp2.length() > 5)
            temp += temp2.trim() + ">";

        temp2 = " Slow";
        tempNum = convertToTint(counters.get("low bar slow"));
        if (tempNum > 0)
            temp2 += "L" + tempNum + " ";
        temp3 = "A";
        tempNum = convertToTint(counters.get("portcullis slow"));
        if (tempNum > 0)
            temp3 += "p" + tempNum + " ";
        tempNum = convertToTint(counters.get("cheval slow"));
        if (tempNum > 0)
            temp3 += "c" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "B";
        tempNum = convertToTint(counters.get("moat slow"));
        if (tempNum > 0)
            temp3 += "m" + tempNum + " ";
        tempNum = convertToTint(counters.get("ramparts slow"));
        if (tempNum > 0)
            temp3 += "r" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "C";
        tempNum = convertToTint(counters.get("drawbridge slow"));
        if (tempNum > 0)
            temp3 += "d" + tempNum + " ";
        tempNum = convertToTint(counters.get("sally slow"));
        if (tempNum > 0)
            temp3 += "s" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "D";
        tempNum = convertToTint(counters.get("rock wall slow"));
        if (tempNum > 0)
            temp3 += "w" + tempNum + " ";
        tempNum = convertToTint(counters.get("rough terrain slow"));
        if (tempNum > 0)
            temp3 += "t" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        if (temp2.length() > 5)
            temp += temp2.trim() + ">";

        temp2 = " <Fail";
        tempNum = convertToTint(counters.get("low bar fail"));
        if (tempNum > 0)
            temp2 += "L" + tempNum + " ";
        temp3 = "A";
        tempNum = convertToTint(counters.get("portcullis fail"));
        if (tempNum > 0)
            temp3 += "p" + tempNum + " ";
        tempNum = convertToTint(counters.get("cheval fail"));
        if (tempNum > 0)
            temp3 += "c" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "B";
        tempNum = convertToTint(counters.get("moat fail"));
        if (tempNum > 0)
            temp3 += "m" + tempNum + " ";
        tempNum = convertToTint(counters.get("ramparts fail"));
        if (tempNum > 0)
            temp3 += "r" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "C";
        tempNum = convertToTint(counters.get("drawbridge fail"));
        if (tempNum > 0)
            temp3 += "d" + tempNum + " ";
        tempNum = convertToTint(counters.get("sally fail"));
        if (tempNum > 0)
            temp3 += "s" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "D";
        tempNum = convertToTint(counters.get("rock wall fail"));
        if (tempNum > 0)
            temp3 += "w" + tempNum + " ";
        tempNum = convertToTint(counters.get("rough terrain fail"));
        if (tempNum > 0)
            temp3 += "t" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        if (temp2.length() > 6)
            temp += temp2.trim() + ">";

        temp2 = " <Stuck";
        tempNum = convertToTint(counters.get("low bar stuck"));
        if (tempNum > 0)
            temp2 += "L" + tempNum + " ";
        temp3 = "A";
        tempNum = convertToTint(counters.get("portcullis stuck"));
        if (tempNum > 0)
            temp3 += "p" + tempNum + " ";
        tempNum = convertToTint(counters.get("cheval stuck"));
        if (tempNum > 0)
            temp3 += "c" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "B";
        tempNum = convertToTint(counters.get("moat stuck"));
        if (tempNum > 0)
            temp3 += "m" + tempNum + " ";
        tempNum = convertToTint(counters.get("ramparts stuck"));
        if (tempNum > 0)
            temp3 += "r" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "C";
        tempNum = convertToTint(counters.get("drawbridge stuck"));
        if (tempNum > 0)
            temp3 += "d" + tempNum + " ";
        tempNum = convertToTint(counters.get("sally stuck"));
        if (tempNum > 0)
            temp3 += "s" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        temp3 = "D";
        tempNum = convertToTint(counters.get("rock wall stuck"));
        if (tempNum > 0)
            temp3 += "w" + tempNum + " ";
        tempNum = convertToTint(counters.get("rough terrain stuck"));
        if (tempNum > 0)
            temp3 += "t" + tempNum + " ";
        if (temp3.length() > 1)
            temp2 += temp3.trim() + " ";
        if (temp2.length() > 7)
            temp += temp2.trim() + ">";

        if (temp.length() > 2)
            designation += temp.trim() + ") ";

        //High goal
        temp = "S(";
        if (convertToTint(counters.get("highg fast")) > 0)
            temp += "f" + convertToTint(counters.get("highg fast"));
        if (convertToTint(counters.get("highg slow")) > 0)
            temp += "s" + convertToTint(counters.get("highg slow"));
        temp2 = " Qual(";
        if (convertToTint(counters.get("highg great")) > 0)
            temp2 += "g" + convertToTint(counters.get("highg great"));
        if (convertToTint(counters.get("highg fair")) > 0)
            temp2 += "f" + convertToTint(counters.get("highg fair"));
        if (convertToTint(counters.get("highg bad")) > 0)
            temp2 += "b" + convertToTint(counters.get("highg bad"));
        if (temp2.length() > 6)
            temp += temp2.trim() + ")";
        temp2 = " From(";
        if (convertToTint(counters.get("highg batter")) > 0)
            temp2 += "b" + convertToTint(counters.get("highg batter"));
        if (convertToTint(counters.get("highg outerworks")) > 0)
            temp2 += "o" + convertToTint(counters.get("highg outerworks"));
        if (convertToTint(counters.get("highg corner")) > 0)
            temp2 += "c" + convertToTint(counters.get("highg corner"));
        if (temp2.length() > 6)
            temp += temp2.trim() + ")";
        if (temp.length() > 2)
            designation += temp.trim() + ") ";

        //Low goal
        temp = "L(";
        if (convertToTint(counters.get("lowg fast")) > 0)
            temp += "f" + convertToTint(counters.get("lowg fast"));
        if (convertToTint(counters.get("lowg slow")) > 0)
            temp += "s" + convertToTint(counters.get("lowg slow"));
        temp2 = " Qual(";
        if (convertToTint(counters.get("lowg great")) > 0)
            temp2 += "g" + convertToTint(counters.get("lowg great"));
        if (convertToTint(counters.get("lowg fair")) > 0)
            temp2 += "f" + convertToTint(counters.get("lowg fair"));
        if (convertToTint(counters.get("lowg bad")) > 0)
            temp2 += "b" + convertToTint(counters.get("lowg bad"));
        if (temp2.length() > 6)
            temp += temp2.trim() + ")";
        if (temp.length() > 2)
            designation += temp.trim() + ") ";

        //Defender
        temp = "D(";
        if (convertToTint(counters.get("defender ground")) > 0)
            temp += "g" + convertToTint(counters.get("defender ground"));
        if (convertToTint(counters.get("defender air")) > 0)
            temp += "h" + convertToTint(counters.get("defender air"));
        if (temp.length() > 2)
            designation += temp.trim() + ") ";

        //Drivers
        temp = "V(";
        if (convertToTint(counters.get("drivers smooth")) > 0)
            temp += "s" + convertToTint(counters.get("drivers smooth"));
        if (convertToTint(counters.get("drivers dassist")) > 0)
            temp += "ad" + convertToTint(counters.get("drivers dassist"));
        if (convertToTint(counters.get("drivers sassist")) > 0)
            temp += "as" + convertToTint(counters.get("drivers sassist"));
        temp2 = " <";
        if (convertToTint(counters.get("drivers foul")) > 0)
            temp2 += "f" + convertToTint(counters.get("drivers foul"));
        if (convertToTint(counters.get("drivers crash")) > 0)
            temp2 += "c" + convertToTint(counters.get("drivers crash"));
        if (convertToTint(counters.get("drivers jerky")) > 0)
            temp2 += "j" + convertToTint(counters.get("drivers jerky"));
        if (temp2.length() > 2)
            temp += temp2.trim() + ">";
        if (temp.length() > 2)
            designation += temp.trim() + ") ";

        //Toughness
        temp = "T(";
        if (convertToTint(counters.get("takes hits")) > 0)
            temp += "s" + convertToTint(counters.get("takes hits"));
        if (convertToTint(counters.get("tough")) > 0)
            temp += "t" + convertToTint(counters.get("tough"));
        temp2 = " <";
        if (convertToTint(counters.get("damaged")) > 0)
            temp2 += "d" + convertToTint(counters.get("damaged"));
        if (convertToTint(counters.get("falling apart")) > 0)
            temp2 += "f" + convertToTint(counters.get("falling apart"));
        if (temp2.length() > 2)
            temp += temp2.trim() + ">";
        if (temp.length() > 2)
            designation += temp.trim() + ") ";

        //Reliability
        temp = "R";
        if (convertToTint(counters.get("reliable")) > 0)
            temp += convertToTint(counters.get("reliable")) + " ";
        if (temp.length() > 1)
            designation += temp.trim();

        //Unreliability
        temp = "U(";
        if (convertToTint(counters.get("no show")) > 0)
            temp += "n" + convertToTint(counters.get("no show"));
        if (convertToTint(counters.get("dead on arrival")) > 0)
            temp += "d" + convertToTint(counters.get("dead on arrival"));
        if (convertToTint(counters.get("died first half")) > 0)
            temp += "f" + convertToTint(counters.get("died first half"));
        if (convertToTint(counters.get("died second half")) > 0)
            temp += "s" + convertToTint(counters.get("died second half"));
        if (convertToTint(counters.get("midgame reboot")) > 0)
            temp += "r" + convertToTint(counters.get("midgame reboot"));
        if (convertToTint(counters.get("malfunction")) > 0)
            temp += "m" + convertToTint(counters.get("malfunction"));
        if (convertToTint(counters.get("tips over")) > 0)
            temp += "t" + convertToTint(counters.get("tips over"));
        if (temp.length() > 2)
            designation += temp.trim() + ") ";

        //End game
        temp = "E(";
        if (convertToTint(counters.get("challenges")) > 0)
            temp += "Ch" + convertToTint(counters.get("challenges")) + " ";
        if (convertToTint(counters.get("climbs")) > 0)
            temp += "Cl" + convertToTint(counters.get("climbs")) + " ";
        if (convertToTint(counters.get("wins")) > 0)
            temp += "W" + convertToTint(counters.get("wins")) + " ";
        if (convertToTint(counters.get("loses")) > 0)
            temp += "L" + convertToTint(counters.get("loses")) + " ";
        if (temp.length() > 2)
            designation += temp.trim() + ") ";

        return designation.trim() + " }";
    }

    private void processTags(List<Tag>  tags) {
        //Added an array contains function because Java's script engine doesn't contain ones
        String containsFunction = "function contains(arr, obj) {\n" +
                "   for (var i = 0; i < arr.length; i++) {\n" +
                "       if (arr[i] == obj) {\n" +
                "           return true;\n" +
                "       }\n" +
                "   }\n" +
                "   return false;\n" +
                "}\n";
        counters.clear();
        //Create new ScriptEngine that will process the javascript expression
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
        for (Tag tag : tags) {
            if (tag != null &&
                    tag.getExpression() != null &&
                    !tag.getExpression().equals("") &&
                    tag.getCategory() != null &&
                    !tag.getCategory().equals("")) {
                if (!counters.containsKey(tag.getCategory()))
                    counters.put(tag.getCategory(), 0);
                if(tag.requiesEval()) {
                    //Set the javascript variable arr equal to the an array of strings of each tag
                    String[] stingTags = new String[tags.size()];
                    for (int i = 0; i < tags.size(); i++)
                        if (tags.get(i) != null)
                            stingTags[i] = tags.get(i).toString();
                    //Set the javascript variable x equal to the value of highGoal
                    engine.put("x", counters.get(tag.getCategory()));
                    engine.put("arr", stingTags);
                    //Evaluated to javascript expression
                    try {
                        engine.eval(containsFunction + tag.getExpression());
                    } catch (ScriptException e) {
                        System.err.println("Tag error: " + tag.getTag());
                    }
                    //Convert the java Object x into an int set highGoal equal to that value
                    counters.put(tag.getCategory(), convertToTint(engine.get("x")));
                } else {
                    counters.put(tag.getCategory(), convertToTint(counters.get(tag.getCategory())) +
                            Integer.parseInt(tag.getExpression()));
                }
            }
        }
    }

    public String generateLongDesignation(int teamNum, double avgScore, int numMatches, List<Tag> tags) {
        processTags(tags);

        String longdesignation, temp, temp2, temp3;
        int tempNum;

        longdesignation = "Team " + teamNum + "  Avg Score " + String.format("%.2f", avgScore) + "  Played " + numMatches + "\n";

        //Auton
        temp = "Auton ( ";
        tempNum = convertToTint(counters.get("auto carry"));
        if (tempNum > 0)
            temp += "Boulder " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto reach"));
        if (tempNum > 0)
            temp += "Reach " + tempNum + " ";
        if (temp.length() > 8)
            longdesignation += temp.trim() + ")\n";

        temp = "Auton Cross ( ";
        tempNum = convertToTint(counters.get("auto cross l"));
        if (tempNum > 0)
            temp += "Low " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross p"));
        if (tempNum > 0)
            temp += "Port " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross c"));
        if (tempNum > 0)
            temp += "Cheval " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross m"));
        if (tempNum > 0)
            temp += "Moat " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross r"));
        if (tempNum > 0)
            temp += "Ramp " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross d"));
        if (tempNum > 0)
            temp += "Draw " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross s"));
        if (tempNum > 0)
            temp += "Sally " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross w"));
        if (tempNum > 0)
            temp += "Wall " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto cross t"));
        if (tempNum > 0)
            temp += "Terr " + tempNum + " ";
        if (temp.length() > 14)
            longdesignation += temp.trim() + ")\n";

        temp = "Auton Goals ( ";
        tempNum = convertToTint(counters.get("auto high goal"));
        if (tempNum > 0)
            temp += "High " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto low goal"));
        if (tempNum > 0)
            temp += "Low " + tempNum + " ";
        temp2 = "Failed(";
        tempNum = convertToTint(counters.get("auto high fail"));
        if (tempNum > 0)
            temp2 += "High " + tempNum + " ";
        tempNum = convertToTint(counters.get("auto low fail"));
        if (tempNum > 0)
            temp2 += "Low" + tempNum;
        if (temp2.length() > 7 )
            temp += temp2.trim() + ") ";
        if (temp.length() > 14)
            longdesignation += temp.trim() + ")\n";

        //Outerworks
        temp = "OW Fast ( ";
        tempNum = convertToTint(counters.get("low bar fast"));
        if (tempNum > 0)
            temp += "Low " + tempNum + " ";
        tempNum = convertToTint(counters.get("portcullis fast"));
        if (tempNum > 0)
            temp += "Port " + tempNum + " ";
        tempNum = convertToTint(counters.get("cheval fast"));
        if (tempNum > 0)
            temp += "Cheval " + tempNum + " ";
        tempNum = convertToTint(counters.get("moat fast"));
        if (tempNum > 0)
            temp += "Moat " + tempNum + " ";
        tempNum = convertToTint(counters.get("ramparts fast"));
        if (tempNum > 0)
            temp += "Ramp " + tempNum + " ";
        tempNum = convertToTint(counters.get("drawbridge fast"));
        if (tempNum > 0)
            temp += "Draw " + tempNum + " ";
        tempNum = convertToTint(counters.get("sally fast"));
        if (tempNum > 0)
            temp += "Sally " + tempNum + " ";
        tempNum = convertToTint(counters.get("rock wall fast"));
        if (tempNum > 0)
            temp += "Wall " + tempNum + " ";
        tempNum = convertToTint(counters.get("rough terrain fast"));
        if (tempNum > 0)
            temp += "Terr " + tempNum + " ";
        if (temp.length() > 10)
            longdesignation += temp.trim() + ")\n";

        temp = "OW Slow ( ";
        tempNum = convertToTint(counters.get("low bar slow"));
        if (tempNum > 0)
            temp += "Low " + tempNum + " ";
        tempNum = convertToTint(counters.get("portcullis slow"));
        if (tempNum > 0)
            temp += "Port " + tempNum + " ";
        tempNum = convertToTint(counters.get("cheval slow"));
        if (tempNum > 0)
            temp += "Cheval " + tempNum + " ";
        tempNum = convertToTint(counters.get("moat slow"));
        if (tempNum > 0)
            temp += "Moat " + tempNum + " ";
        tempNum = convertToTint(counters.get("ramparts slow"));
        if (tempNum > 0)
            temp += "Ramp " + tempNum + " ";
        tempNum = convertToTint(counters.get("drawbridge slow"));
        if (tempNum > 0)
            temp += "Draw " + tempNum + " ";
        tempNum = convertToTint(counters.get("sally slow"));
        if (tempNum > 0)
            temp += "Sally " + tempNum + " ";
        tempNum = convertToTint(counters.get("rock wall slow"));
        if (tempNum > 0)
            temp += "Wall " + tempNum + " ";
        tempNum = convertToTint(counters.get("rough terrain fast"));
        if (tempNum > 0)
            temp += "Terr " + tempNum + " ";
        if (temp.length() > 10)
            longdesignation += temp.trim() + ")\n";

        temp = "OW Fail ( ";
        tempNum = convertToTint(counters.get("low bar fail"));
        if (tempNum > 0)
            temp += "Low " + tempNum + " ";
        tempNum = convertToTint(counters.get("portcullis fail"));
        if (tempNum > 0)
            temp += "Port " + tempNum + " ";
        tempNum = convertToTint(counters.get("cheval fail"));
        if (tempNum > 0)
            temp += "Cheval " + tempNum + " ";
        tempNum = convertToTint(counters.get("moat fail"));
        if (tempNum > 0)
            temp += "Moat " + tempNum + " ";
        tempNum = convertToTint(counters.get("ramparts fail"));
        if (tempNum > 0)
            temp += "Ramp " + tempNum + " ";
        tempNum = convertToTint(counters.get("drawbridge fail"));
        if (tempNum > 0)
            temp += "Draw " + tempNum + " ";
        tempNum = convertToTint(counters.get("sally fail"));
        if (tempNum > 0)
            temp += "Sally " + tempNum + " ";
        tempNum = convertToTint(counters.get("rock wall fail"));
        if (tempNum > 0)
            temp += "Wall " + tempNum + " ";
        tempNum = convertToTint(counters.get("rough terrain fast"));
        if (tempNum > 0)
            temp += "Terr " + tempNum + " ";
        if (temp.length() > 10)
            longdesignation += temp.trim() + ")\n";


        temp = "OW Stuck ( ";
        tempNum = convertToTint(counters.get("low bar stuck"));
        if (tempNum > 0)
            temp += "Low " + tempNum + " ";
        tempNum = convertToTint(counters.get("portcullis stuck"));
        if (tempNum > 0)
            temp += "Port " + tempNum + " ";
        tempNum = convertToTint(counters.get("cheval stuck"));
        if (tempNum > 0)
            temp += "Cheval " + tempNum + " ";
        tempNum = convertToTint(counters.get("moat stuck"));
        if (tempNum > 0)
            temp += "Moat " + tempNum + " ";
        tempNum = convertToTint(counters.get("ramparts stuck"));
        if (tempNum > 0)
            temp += "Ramp " + tempNum + " ";
        tempNum = convertToTint(counters.get("drawbridge stuck"));
        if (tempNum > 0)
            temp += "Draw " + tempNum + " ";
        tempNum = convertToTint(counters.get("sally stuck"));
        if (tempNum > 0)
            temp += "Sally " + tempNum + " ";
        tempNum = convertToTint(counters.get("rock wall stuck"));
        if (tempNum > 0)
            temp += "Wall " + tempNum + " ";
        tempNum = convertToTint(counters.get("rough terrain fast"));
        if (tempNum > 0)
            temp += "Terr " + tempNum + " ";
        if (temp.length() > 11)
            longdesignation += temp.trim() + ")\n";

        //High goal
        temp = "High Shooter ( ";
        if (convertToTint(counters.get("highg fast")) > 0)
            temp += "Fast " + convertToTint(counters.get("highg fast") + " ");
        if (convertToTint(counters.get("highg slow")) > 0)
            temp += "Slow " + convertToTint(counters.get("highg slow") + " ");
        temp2 = " Qual(";
        if (convertToTint(counters.get("highg great")) > 0)
            temp2 += "Good " + convertToTint(counters.get("highg great") + " ");
        if (convertToTint(counters.get("highg fair")) > 0)
            temp2 += "Fair " + convertToTint(counters.get("highg fair") + " ");
        if (convertToTint(counters.get("highg bad")) > 0)
            temp2 += "Bad " + convertToTint(counters.get("highg bad") + " ");
        if (temp2.length() > 6)
            temp += temp2.trim() + ")";
        temp2 = " From(";
        if (convertToTint(counters.get("highg batter")) > 0)
            temp2 += "Batter " + convertToTint(counters.get("highg batter") + " ");
        if (convertToTint(counters.get("highg outerworks")) > 0)
            temp2 += "Outerworks" + convertToTint(counters.get("highg batter") + " ");
        if (convertToTint(counters.get("highg corner")) > 0)
            temp2 += "Corner" + convertToTint(counters.get("highg corner") + " ");
        if (temp2.length() > 6)
            temp += temp2.trim() + ")";
        if (temp.length() > 15)
            longdesignation += temp.trim() + " )\n";

        //Low goal
        temp = "Low Shooter ( ";
        if (convertToTint(counters.get("lowg fast")) > 0)
            temp += "Fast " + convertToTint(counters.get("lowg fast") + " ");
        if (convertToTint(counters.get("lowg slow")) > 0)
            temp += "Slow " + convertToTint(counters.get("lowg slow") + " ");
        temp2 = " Qual(";
        if (convertToTint(counters.get("lowg great")) > 0)
            temp2 += "Good " + convertToTint(counters.get("lowg great") + " ");
        if (convertToTint(counters.get("lowg fair")) > 0)
            temp2 += "Fair " + convertToTint(counters.get("lowg fair") + " ");
        if (convertToTint(counters.get("lowg bad")) > 0)
            temp2 += "Bad " + convertToTint(counters.get("lowg bad") + " ");
        if (temp2.length() > 6)
            temp += temp2.trim() + ")";
        if (temp.length() > 14)
            longdesignation += temp.trim() + " )\n";

        //Defender
        temp = "Defender ( ";
        if (convertToTint(counters.get("defender ground")) > 0)
            temp += "Ground " + convertToTint(counters.get("defender ground") + " ");
        if (convertToTint(counters.get("defender air")) > 0)
            temp += "High " + convertToTint(counters.get("defender air") + " ");
        if (temp.length() > 11)
            longdesignation += temp.trim() + ")\n";

        //Drivers
        temp = "Driving ( ";
        if (convertToTint(counters.get("drivers smooth")) > 0)
            temp += "Smmooth " + convertToTint(counters.get("drivers smooth") + " ");
        if (convertToTint(counters.get("drivers dassist")) > 0)
            temp += "Assists DB " + convertToTint(counters.get("drivers dassist") + " ");
        if (convertToTint(counters.get("drivers sassist")) > 0)
            temp += "Assists SP " + convertToTint(counters.get("drivers sassist") + " ");
        if (convertToTint(counters.get("drivers foul")) > 0)
            temp += "Fouls " + convertToTint(counters.get("drivers foul") + " ");
        if (convertToTint(counters.get("drivers crash")) > 0)
            temp += "Crashes " + convertToTint(counters.get("drivers crash") + " ");
        if (convertToTint(counters.get("drivers jerky")) > 0)
            temp += "Jerky " + convertToTint(counters.get("drivers jerky") + " ");
        if (temp.length() > 10)
            longdesignation += temp.trim() + ")\n";

        //Toughness
        temp = "Toughness ( ";
        if (convertToTint(counters.get("takes hits")) > 0)
            temp += "Survives Collisions " + convertToTint(counters.get("takes hits") + " ");
        if (convertToTint(counters.get("tough")) > 0)
            temp += "Tough " + convertToTint(counters.get("tough") + " ");
        if (convertToTint(counters.get("damaged")) > 0)
            temp += "Damaged " + convertToTint(counters.get("damaged") + " ");
        if (convertToTint(counters.get("falling apart")) > 0)
            temp += "Falling Apart " + convertToTint(counters.get("falling apart") + " ");
        if (temp.length() > 12)
            longdesignation += temp.trim() + ")\n";


        //Reliability
        if (convertToTint(counters.get("reliable")) > 0)
            temp = "Reliable " + convertToTint(counters.get("reliable")) + "\n";
        if (temp.length() > 1)
            longdesignation += temp.trim();

        //Unreliability
        temp = "Unreliable ( ";
        if (convertToTint(counters.get("no show")) > 0)
            temp += "No Show " + convertToTint(counters.get("no show") + " ");
        if (convertToTint(counters.get("dead on arrival")) > 0)
            temp += "DOA " + convertToTint(counters.get("dead on arrival") + " ");
        if (convertToTint(counters.get("died first half")) > 0)
            temp += "Died 1st Half " + convertToTint(counters.get("died first half") + " ");
        if (convertToTint(counters.get("died second half")) > 0)
            temp += "Died 2nd Half " + convertToTint(counters.get("died second half") + " ");
        if (convertToTint(counters.get("midgame reboot")) > 0)
            temp += "Midgame Reboot " + convertToTint(counters.get("midgame reboot") + " ");
        if (convertToTint(counters.get("malfunction")) > 0)
            temp += "Malfunction" + convertToTint(counters.get("malfunction") + " ");
        if (convertToTint(counters.get("tips over")) > 0)
            temp += "Tips Over " + convertToTint(counters.get("tips over") + " ");
        if (temp.length() > 13)
            longdesignation += temp.trim() + ")\n";

        //End game
        temp = "Endgame ( ";
        if (convertToTint(counters.get("challenges")) > 0)
            temp += "Challenge " + convertToTint(counters.get("challenges")) + " ";
        if (convertToTint(counters.get("climbs")) > 0)
            temp += "Climb " + convertToTint(counters.get("climbs")) + " ";
        if (convertToTint(counters.get("wins")) > 0)
            temp += "Win " + convertToTint(counters.get("wins")) + " ";
        if (convertToTint(counters.get("loses")) > 0)
            temp += "Lose " + convertToTint(counters.get("loses")) + " ";
        if (temp.length() > 10)
            longdesignation += temp.trim() + ")\n";

        return longdesignation.trim();
    }


    private static int convertToTint(Object x) {
        if (x == null)
            return -1;
        return (x.getClass() == Double.class) ? (int) Math.round((Double) x) : (int) x;
    }

    public String toString() {
        String string = "";
        for (Map.Entry<String, Integer> entry : counters.entrySet()) {
            string += entry.getKey() + " : " + entry.getValue();
        }
        return string;
    }
}
