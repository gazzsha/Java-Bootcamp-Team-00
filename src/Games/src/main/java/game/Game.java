package game;

import Colors.Colors;
import com.beust.jcommander.JCommander;
import com.diogonunes.jcdp.color.api.Ansi;
import com.school.team.Utils.Pair;
import logic.GameMap;
import logic.ParseCommandLine;

import java.io.IOException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws IOException {
        ParseCommandLine parseCommandLine = new ParseCommandLine();
        JCommander jCommander = JCommander.newBuilder().addObject(parseCommandLine).build();
        jCommander.parse(args);
        try (Scanner scanner = new Scanner(System.in)) {
            GameMap gameMap = new GameMap(parseCommandLine);
            gameMap.generateMap();
            gameMap.PrintGameMap();
            System.out.println("Play in game? (9 - NO)");
            char end = scanner.next().toCharArray()[0];
            if (end == '9') {
                System.out.println("You are lost!");
            } else {
                gameMap.PrintGameMap();
                gameMap.updateMap(scanner);
            }
        }


    }

}
