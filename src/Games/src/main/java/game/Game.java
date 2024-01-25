package game;

import com.beust.jcommander.JCommander;
import com.school.team.Utils.Pair;
import logic.GameMap;
import logic.ParseCommandLine;

public class Game {
    public static void main(String[] args) {
        ParseCommandLine parseCommandLine = new ParseCommandLine();
        JCommander jCommander = JCommander.newBuilder().addObject(parseCommandLine).build();
        jCommander.parse(args);

        GameMap gameMap = new GameMap(parseCommandLine);
        gameMap.PrintGameMap();

        int[][] mama = gameMap.generateIntMap();

        for(int[] i  : mama) {
            for(int j : i) {
                System.out.print(j);
            }
            System.out.println();
        }

    }

}
