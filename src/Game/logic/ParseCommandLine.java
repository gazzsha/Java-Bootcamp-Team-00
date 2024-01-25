package logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters (separators = "=")
public class ParseCommandLine {
    @Parameter(
            names = "--enemiesCount",
            required = true,
            arity = 1
    )
    private int countEnemies;

    @Parameter(
            names = "--wallsCount",
            required = true,
            arity = 1
    )
    private int countWalls;

    @Parameter(
            names = "--size",
            required = true,
            arity = 1
    )
    private int sizeMap;

    @Parameter(
            names = "--profile",
            required = true,
            arity = 1
    )
    private String mode;

    public ParseCommandLine() {}

    public int getCountEnemies() {
        return countEnemies;
    }

    public int getCountWalls() {
        return countWalls;
    }

    public int getSizeMap() {
        return sizeMap;
    }

    public String getMode() {
        return mode;
    }
}
