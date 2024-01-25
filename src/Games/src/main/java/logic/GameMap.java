package logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.school.team.Enemy.Enemy;
import com.school.team.Utils.Pair;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static logic.CellType.*;

@FunctionalInterface
interface CellPrecessing {
    int obtainingNeighborhood(int rows, int cols);
}

public class GameMap {
    private List<Enemy> enemy = new ArrayList<>();
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
        changeValue((gameMap) -> setEmptyMap());
        setGameMapWall();
        for(int i = 0; i < parseCommandLine.getCountEnemies(); ++i) {
            getRandomValue();
        }
    }

    public int[][] generateIntMap() {
//        int[][] arr = new int[gameMap.length][gameMap.length];
//        for(int i = 0; i < gameMap.length; ++i) {
//            for(int j = 0; j < gameMap.length; ++j) {
//                arr[i][j] = gameMap[i][j].getValue();
//            }
//        }
//        return arr;
        return Arrays.stream(gameMap)
                .map(row -> Arrays.stream(row)
                        .mapToInt(CellType::getValue)
                        .toArray())
                .toArray(int[][]::new);
    }

    public void PrintGameMap() {
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
                coloredPrinter.print(" " + gameMap[i][j].getValue() + " ");
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

    void getRandomValue() {
        while(true) {
            int xRandom = ThreadLocalRandom.current().nextInt(0, gameMap.length);
            int yRandom = ThreadLocalRandom.current().nextInt(0, gameMap.length);

            if(gameMap[xRandom][yRandom] == EMPTY) {
                gameMap[xRandom][yRandom] = ENEMY;
                enemy.add(new Enemy(new Pair<>(xRandom, yRandom)));
                break;
            }
        }
    }

    private int countingNeighborhood(int rowCurrent, int colsCurrent) {
        CellPrecessing cellPrecessing = (rows, cols) -> {
            int count = 0;
            if(gameMap[rows][cols - 1] != EMPTY) count++;
            if(gameMap[rows][cols + 1] != EMPTY) count++;
            if(gameMap[rows - 1][cols] != EMPTY) count++;
            if(gameMap[rows + 1][cols] != EMPTY) count++;
            if(gameMap[rows - 1][cols - 1] != EMPTY) count++;
            if(gameMap[rows - 1][cols + 1] != EMPTY) count++;
            if(gameMap[rows + 1][cols - 1] != EMPTY) count++;
            if(gameMap[rows + 1][cols + 1] != EMPTY) count++;
            return count;
        };
        return cellPrecessing.obtainingNeighborhood(rowCurrent, colsCurrent);
    }

}

