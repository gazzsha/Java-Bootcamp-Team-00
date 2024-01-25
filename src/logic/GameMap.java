package logic;

import com.diogonunes.jcdp.color.api.Ansi;

import java.util.HashMap;
import java.util.Map;

public class GameMap {
    Map<CellType, Ansi.BColor> cellTypeBColorMap;
    GameMap(ParseCommandLine parseCommandLine) {
        cellTypeBColorMap = new HashMap<>();
        cellTypeBColorMap.put(CellType.EMPTY, Ansi.BColor.YELLOW);
        cellTypeBColorMap.put(CellType.PLAYER, Ansi.BColor.GREEN);
        cellTypeBColorMap.put(CellType.ENEMY, Ansi.BColor.RED);
        cellTypeBColorMap.put(CellType.WALL, Ansi.BColor.MAGENTA);
        cellTypeBColorMap.put(CellType.GOAL, Ansi.BColor.BLUE);
    }
}

