package logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

import static logic.CellType.*;

public class GameMap {
    private final Map<CellType, Ansi.BColor> cellTypeBColorMap;
    private final ParseCommandLine parseCommandLine;

    private final CellType[][] gameMap;

    void changeValue(Consumer<CellType[][]> consumer) {
        consumer.accept(gameMap);
    }
    final ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false).build();
    public GameMap(ParseCommandLine parseCommandLine) {
        cellTypeBColorMap = new HashMap<>();

        cellTypeBColorMap.put(EMPTY, Ansi.BColor.YELLOW);
        cellTypeBColorMap.put(PLAYER, Ansi.BColor.GREEN);
        cellTypeBColorMap.put(ENEMY, Ansi.BColor.RED);
        cellTypeBColorMap.put(WALL, Ansi.BColor.MAGENTA);
        cellTypeBColorMap.put(GOAL, Ansi.BColor.BLUE);

        this.parseCommandLine = parseCommandLine;
        gameMap = new CellType[parseCommandLine.getSizeMap()][parseCommandLine.getSizeMap()];
        changeValue((gameMap) -> { setEmptyMap(); });
    }

    public void PrintGameMap() {
        setGameMapWall();
        char type;
        for(int i = 0; i < parseCommandLine.getSizeMap(); ++i) {
            for(int j = 0; j < parseCommandLine.getSizeMap(); ++j) {
                type = ' ';
                coloredPrinter.setBackgroundColor(Ansi.BColor.YELLOW);
                switch (gameMap[i][j]) {
                    case PLAYER:
                        coloredPrinter.setBackgroundColor(cellTypeBColorMap.get(PLAYER));
                        type = 'o';
                        break;
                    case ENEMY:
                        coloredPrinter.setBackgroundColor(cellTypeBColorMap.get(ENEMY));
                        type = 'X';
                        break;
                    case WALL:
                        coloredPrinter.setBackgroundColor(cellTypeBColorMap.get(WALL));
                        type = '#';
                        break;
                    case GOAL:
                        coloredPrinter.setBackgroundColor(cellTypeBColorMap.get(GOAL));
                        type = 'O';
                        break;
                    default:
                        break;
                }
                coloredPrinter.print(" " + type + " ");
            }
            System.out.println();
        }
    }

    private void setGameMapWall() {
        Random random = new Random();
        int countWall = parseCommandLine.getCountWalls();
        while(countWall > 0) {
            int randomRow = random.nextInt(parseCommandLine.getSizeMap());
            int randomCol = random.nextInt(parseCommandLine.getSizeMap());

            if(gameMap[randomRow][randomCol] != CellType.WALL) {
                gameMap[randomRow][randomCol] = CellType.WALL;
                countWall--;
            }
        }
    }

    private void setEmptyMap() {
        for (int i = 0; i < parseCommandLine.getSizeMap(); ++i) {
            for (int j = 0; j < parseCommandLine.getSizeMap(); ++j) {
                gameMap[i][j] = CellType.EMPTY;
            }
        }
    }
}

