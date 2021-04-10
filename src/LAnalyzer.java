import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Scanner;

public class LAnalyzer {
    public static void main(String[] args) {
        
        String dfa;

        dfa = parseFile("src/dfa.txt");
        JSONParser parser = new JSONParser();
        JSONObject obj = null;

       try {
           obj = (JSONObject)parser.parse(dfa);
       } catch (ParseException e) {
           System.out.println("변환에 실패");
           e.printStackTrace();
       }
      
            System.out.println(obj.get("COMP"));

            JSONObject obj2 = (JSONObject)obj.get("COMP");
            System.out.println(obj2.get("T1"));

        
        System.out.println("끝");

    }
    public String parseFile(String fileDir) {

        StringBuilder result = new StringBuilder();
        try{
            //파일 객체 생성
            File file = new File(fileDir);
            //입력 스트림 생성
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                result.append(scan.nextLine());
            }
        }catch (FileNotFoundException e) {
            e.getStackTrace();
        }
        return result.toString();
    }
}
    
