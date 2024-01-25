package game;

import com.beust.jcommander.JCommander;
import logic.ParseCommandLine;

public class Game {
    public static void main(String[] args) {
        ParseCommandLine parseCommandLine = new ParseCommandLine();
        JCommander jCommander = JCommander.newBuilder().addObject(parseCommandLine).build();
        jCommander.parse(args);
        System.out.println(parseCommandLine.getCountEnemies());
        System.out.println(parseCommandLine.getCountWalls());
        System.out.println(parseCommandLine.getMode());
        System.out.println(parseCommandLine.getSizeMap());
    }
}
