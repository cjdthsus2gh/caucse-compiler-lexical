import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseTable {
    private JSONObject table;
    private JSONParser parser = new JSONParser();
    private int priority = 0;
    
    public void initTable() {
        try {
            table = (JSONObject) parser.parse(new FileReader("./transTable.json"));
        } catch (ParseException e) {
            System.out.println("변환에 실패");
            e.printStackTrace();
        }
    }
    
    public JSONObject getTable() {
        return table;
    }
}