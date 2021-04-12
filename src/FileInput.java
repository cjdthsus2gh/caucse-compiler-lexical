import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileInput {
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