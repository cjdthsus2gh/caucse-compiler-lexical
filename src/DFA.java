import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;

public class DFA {
    private JSONObject transTable = null;
    ParseTable pt = new ParseTable();

    pt.initTable();
    t_table = pt.getTable();

    public String getNextState(JSONObject currentState, String inputChar) {
        JSONObject nextState = (JSONObject)currentState.get(inputChar);
        return nextState.toString();
    }

    public void dfa(String tok_name) {
        JSONObject tok_states = (JSONObject)transTable.get(tok_name);
        JSONObject s_state = (JSONObject)tok_states.get("T0");
        
    }
}