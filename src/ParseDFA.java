
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseDFA {
    private JSONObject table;
    private JSONParser parser = new JSONParser();
    private int priority = 0;
    JSONObject kindOfToken, currentState;
    String inputSymbol = null;

    public void initJSON() {
        FileInput file = new FileInput();
        String dfa = file.parseFile("src\\dfa.txt");
        try {
            table = (JSONObject) parser.parse(dfa);
        } catch (ParseException e) {
            System.out.println("변환에 실패");
            e.printStackTrace();
        }
    }
    public String getState(String Token,String state,String input) {
            kindOfToken = (JSONObject)table.get(Token);
          //  System.out.println(kindOfToken);
        try {
            currentState = (JSONObject)kindOfToken.get(state);
         //   System.out.println(currentState);
            inputSymbol = currentState.get(input).toString();
         //   System.out.println(inputSymbol);

        } catch (NullPointerException e) {
            return null;
        }
        return inputSymbol;
    }
    public String getToken(int i) {
        String temp;
        switch(i) {
            case 1:
                temp = "COMPARISON";
                break;
            case 2:
                temp = "IDENTIFIER";
                break;
            case 3:
                temp = "DIGIT";
                break;
            default:
                temp = null;
        }
        return temp;
    }
}
