package Colors;

import com.diogonunes.jcdp.color.api.Ansi;
import logic.CellType;

import java.util.HashMap;
import java.util.Map;

import static logic.CellType.*;

public class Colors {

    public Map<String, Ansi.BColor> colorMap = new HashMap<>() {
        {
            put("YELLOW", Ansi.BColor.YELLOW);
            put("GREEN", Ansi.BColor.GREEN);
            put("RED", Ansi.BColor.RED);
            put("MAGENTA", Ansi.BColor.MAGENTA);
            put("BLUE", Ansi.BColor.BLUE);
        }
    };

    public Map<CellType, Ansi.BColor> cellTypeBColorMap = new HashMap<>();

}
