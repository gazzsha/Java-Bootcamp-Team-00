package logic;

import Colors.Colors;
import Symbol.Symbol;
import com.diogonunes.jcdp.color.api.Ansi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class FileReader {
    private static final String file = "/application-production.properties";
    private static final String devFile = "application-dev.properties";
    public Colors parceColors() throws IOException {
        Colors colors = new Colors();
        try (InputStream inputStream = getClass().getResourceAsStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stringsInLine = line.split(" = ");
                switch (stringsInLine[0]) {
                    case "enemy.color": {
                        colors.cellTypeBColorMap.put(CellType.ENEMY, colors.colorMap.get(stringsInLine[1].toUpperCase(Locale.ROOT)));
                        break;
                    }
                    case "player.color": {
                        colors.cellTypeBColorMap.put(CellType.PLAYER, colors.colorMap.get(stringsInLine[1].toUpperCase(Locale.ROOT)));
                        break;
                    }
                    case "wall.color": {
                        colors.cellTypeBColorMap.put(CellType.WALL, colors.colorMap.get(stringsInLine[1].toUpperCase(Locale.ROOT)));
                        break;
                    }
                    case "goal.color": {
                        colors.cellTypeBColorMap.put(CellType.GOAL, colors.colorMap.get(stringsInLine[1].toUpperCase(Locale.ROOT)));
                        break;
                    }
                    case "empty.color": {
                        colors.cellTypeBColorMap.put(CellType.EMPTY, colors.colorMap.get(stringsInLine[1].toUpperCase(Locale.ROOT)));
                        break;
                    }
                }
            }
        }
        return colors;
    }


    public Symbol parceSymbols() throws IOException {
        Symbol symbol = new Symbol();
        try (InputStream inputStream = getClass().getResourceAsStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] stringsInLine = line.split(" =");
                switch (stringsInLine[0]) {

                    case "enemy.char": {
                        symbol.symbolRole.put(CellType.ENEMY,  stringsInLine.length == 1 ? ' ' : stringsInLine[1].toCharArray()[1]);
                        break;
                    }
                    case "player.char": {
                        symbol.symbolRole.put(CellType.PLAYER, stringsInLine.length == 1 ? ' ' : stringsInLine[1].toCharArray()[1]);
                        break;
                    }
                    case "wall.char": {
                        symbol.symbolRole.put(CellType.WALL,  stringsInLine.length == 1 ? ' ' : stringsInLine[1].toCharArray()[1]);
                        break;
                    }
                    case "goal.char": {
                        symbol.symbolRole.put(CellType.GOAL,  stringsInLine.length == 1?' ' : stringsInLine[1].toCharArray()[1]);
                        break;
                    }
                    case "empty.char": {
                        symbol.symbolRole.put(CellType.EMPTY, stringsInLine.length == 1 ? ' ' : stringsInLine[1].toCharArray()[1]);
                        break;
                    }
                }
            }
        }
        return symbol;
    }
}
