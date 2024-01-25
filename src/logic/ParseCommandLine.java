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
    private String countEnemies;

    @Parameter(
            names = "--wallsCount",
            required = true,
            arity = 1
    )
    private String countWalls;

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

    public String getCountEnemies() {
        return countEnemies;
    }

    public String getCountWalls() {
        return countWalls;
    }

    public int getSizeMap() {
        return sizeMap;
    }

    public String getMode() {
        return mode;
    }
}
