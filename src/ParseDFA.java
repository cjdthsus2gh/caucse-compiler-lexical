
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParseDFA {
    private JSONObject table;
    private JSONParser parser = new JSONParser();
    private String Letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String Digit_Exp_Z = "123456789";
    private String Digit = "0123456789";

    JSONObject kindOfToken, currentState;
    String afterState = null;

    public void initJSON() {
        FileInput file = new FileInput();
        String dfa = file.parseFile("src/transTable.json");
        try {
            table = (JSONObject) parser.parse(dfa);
        } catch (ParseException e) {
            System.out.println("변환에 실패");
            e.printStackTrace();
        }
    }
    public String getState(int Torder,String state,String inputS) {

            kindOfToken = (JSONObject)table.get(Integer.toString(Torder));
        try {
            currentState = (JSONObject)kindOfToken.get(state);

            if(Torder>18 && inputS.length() == 1)//identifier,string,integer,char는 해당 symbol의 범주로 검색할 수 있도록 한다.
                inputS = this.preDefine(inputS);

            afterState = currentState.get(inputS).toString();

        } catch (NullPointerException e) {
            return null;
        }
        return afterState;
    }
    public String getName(int Torder) {        //Torder번째에  해당하는 DFA를 반환한다.
        kindOfToken = (JSONObject)table.get(Integer.toString(Torder));
        String temp = (String)kindOfToken.get("name");
        return temp;
    }
    public String preDefine(String s) {        //특정 symbol의 범주를 반환한다. (ex. a-> letter)
        char c = s.charAt(0);
        if(Letter.indexOf(c) != -1)
            return "Letter";
        else if(Digit_Exp_Z.indexOf(c) != -1)
            return "Digit_Exp_Z";
        else if(Digit.indexOf(c) != -1)
            return "Digit";
        else
            return Character.toString(c);
    }
}
