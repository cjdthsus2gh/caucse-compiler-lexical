import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseDFA {
    private JSONObject table;
    private JSONParser parser = new JSONParser();
    private int priority = 0;
    public void initJSON() {

        FileInput file = new FileInput();
        String dfa = file.parseFile("./dfa.txt");

        try {
            table = (JSONObject) parser.parse(dfa);
        } catch (ParseException e) {
            System.out.println("변환에 실패");
            e.printStackTrace();
        }
    }
    public String getT(JSONObject Token, String input) {
        JSONObject temp = (JSONObject)Token.get(input);
        return temp.toString();
    }

}