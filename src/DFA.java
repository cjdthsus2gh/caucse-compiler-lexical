import org.json.simple.JSONObject;
import java.io.*;

public class DFA {
    private int stance;
    private String name;
    private JSONObject table = null;
    private JSONObject c_state = null;

    DFA() {
        this.stance = 1;
    }

    DFA(JSONObject table) {
        this.stance = 1;
        this.table = table;
        this.name = (String)this.table.get("name");
    }

    public void setStance(int stance) {
        this.stance = stance;
    }

    public int getStance() {
        return this.stance;
    }

    public JSONObject getTable() {
        return this.table;
    }

    public void initDFA() {
            this.c_state = (JSONObject)this.table.get("T0");
    }

    public boolean moveNextState(String ch_input) {
        try {
            JSONObject n_state = (JSONObject)this.c_state.get(ch_input);
            this.c_state = n_state;
            return true;
        } catch (Exception e) {
            this.stance = 2;
            return false;
        }
    }

    public String getName() {
        return this.name;
    }
}
