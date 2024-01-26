package logic;

import Colors.Colors;
import Symbol.Symbol;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.school.team.Enemy.Enemy;
import com.school.team.Utils.Pair;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

import static logic.CellType.*;

@FunctionalInterface
interface CellPrecessing {
    int obtainingNeighborhood(int rows, int cols);
}

public class GameMap {
    private List<Enemy> enemy = new ArrayList<>();

    private final ParseCommandLine parseCommandLine;

    private CellType[][] gameMap;

    Colors colors;

    public FileReader fileReader = new FileReader();

    Symbol symbol = new Symbol();


    private Pair<Integer> targetPosition;

    void changeValue(Consumer<CellType[][]> consumer) {
        consumer.accept(gameMap);
    }

    final ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false).build();

    public GameMap(ParseCommandLine parseCommandLine) throws IOException {
        colors = fileReader.parceColors();
        symbol = fileReader.parceSymbols();
        this.parseCommandLine = parseCommandLine;
        gameMap = new CellType[parseCommandLine.getSizeMap()][parseCommandLine.getSizeMap()];
        targetPosition = new Pair<>(1,1);

    }

    public void generateMap() {
        changeValue((gameMap) -> setEmptyMap());
        setGameMapWall();
        for(int i = 0; i < parseCommandLine.getCountEnemies(); ++i) {
            setPositionEnemies();
        }
        setPositionTarget();
        setPositionPlayer();
    }

    public void updateMap() {


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
                type = symbol.symbolRole.get(EMPTY);
                coloredPrinter.setBackgroundColor(Ansi.BColor.YELLOW);
                switch (gameMap[i][j]) {
                    case PLAYER:
                        coloredPrinter.setBackgroundColor(colors.cellTypeBColorMap.get(PLAYER));
                        type = symbol.symbolRole.get(PLAYER);
                        break;
                    case ENEMY:
                        coloredPrinter.setBackgroundColor(colors.cellTypeBColorMap.get(ENEMY));
                        type = symbol.symbolRole.get(ENEMY);
                        break;
                    case WALL:
                        coloredPrinter.setBackgroundColor(colors.cellTypeBColorMap.get(WALL));
                        type = symbol.symbolRole.get(WALL);
                        break;
                    case GOAL:
                        coloredPrinter.setBackgroundColor(colors.cellTypeBColorMap.get(GOAL));
                        type = symbol.symbolRole.get(GOAL);
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

    void setPositionEnemies() {
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


    void setPositionTarget() {
        while(true) {
            int xRandom = ThreadLocalRandom.current().nextInt(0, gameMap.length);
            int yRandom = ThreadLocalRandom.current().nextInt(0, gameMap.length);

            if(gameMap[xRandom][yRandom] == EMPTY) {
                gameMap[xRandom][yRandom] = GOAL;
                targetPosition = new Pair<>(xRandom, yRandom);
                break;
            }
        }
    }


    void setPositionPlayer() {
        while(true) {
            int xRandom = ThreadLocalRandom.current().nextInt(0, gameMap.length);
            int yRandom = ThreadLocalRandom.current().nextInt(0, gameMap.length);

            if(gameMap[xRandom][yRandom] == EMPTY && xRandom + 1 < gameMap.length && yRandom + 1 < gameMap.length && xRandom - 1 >= 0 && yRandom - 1 >= 0) {
                if (countingNeighborhood(xRandom,yRandom) == 0) {
                    gameMap[xRandom][yRandom] = PLAYER;
                    targetPosition = new Pair<>(xRandom, yRandom);
                    break;
                }
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

