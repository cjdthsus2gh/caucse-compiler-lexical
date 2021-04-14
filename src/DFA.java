import org.json.simple.JSONObject;
import java.io.*;

public class DFA {
    private String name;
    private JSONObject table = null;
    private JSONObject c_state = null;

    DFA(JSONObject table) {
        this.table = table;
        this.name = (String)this.table.get("name");
    }
    public void resetDFA() {
        this.c_state = (JSONObject)this.table.get("T0");
    }
    public String getName() {
        return this.name;
    }
    public JSONObject getC_state() {
        return this.c_state;
    }
    public boolean getNextState(String ch_input) {
        String n_input = "";
        if ("fin".equals(ch_input)) {
            n_input = (String)this.c_state.get(ch_input);
            return ("true".equals(n_input) ? true : false);
        }
        for (Object k : this.c_state.keySet()) {
            String key = k.toString();
            if (key.indexOf(ch_input) > -1) {
                n_input = (String)this.c_state.get(key);
                break;
            }
        }
        JSONObject n_state = (JSONObject)this.table.get(n_input);
        if(n_state == null) {
            /*System.out.println("Don't have matching key");
            System.out.println("stance moves to "+stance);*/
            return false;
        }
        else {
            this.c_state = n_state;
            /*System.out.println("Matching key exists!!");
            System.out.println(this.c_state);*/
            return true;
        }
    }
}