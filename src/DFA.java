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

    public JSONObject getC_sate() {
        return this.c_state;
    }

    public void initDFA() {
            this.c_state = (JSONObject)this.table.get("T0");
    }

    public boolean moveNextState(String ch_input) {
            String n_input = (String)this.c_state.get(ch_input);

            if ("true".equals(n_input)) {
                return true;
            }
            JSONObject n_state = (JSONObject)this.table.get(n_input);
            if(n_state == null) {
                this.stance = 2;
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

    public String getName() {
        return this.name;
    }
}
