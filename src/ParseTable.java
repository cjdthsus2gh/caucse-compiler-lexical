import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;

public class ParseTable {
    private JSONObject table;
    private JSONParser parser = new JSONParser();
    
    ParseTable() {
        try {
            table = (JSONObject) parser.parse(new FileReader("src/transTable.json"));
        } catch (Exception e) {
            System.out.println("변환에 실패");
            e.printStackTrace();
        }
    }
    
    public JSONObject getTable() {
        return table;
    }
    public JSONObject splitTable(int id) {
        String tok_id = Integer.toString(id);
        JSONObject tempTable = (JSONObject)table.get(tok_id);
        return tempTable;
    }
}