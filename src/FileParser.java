import java.io.*;
import java.util.Scanner;

public class FileParser {
    public String parseInput(String fileName) {
        /*StringBuilder result = new StringBuilder();
        try{
            //파일 객체 생성
            File file = new File(fileName);
            //입력 스트림 생성
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                result.append(scan.nextLine());
            }
            scan.close();
            result.append("\n");
        }catch (FileNotFoundException e) {
            e.getStackTrace();
        return result.toString();*/
        FileInputStream fs = null;
        try {
            // 바이트 단위로 파일읽기
             fs = new FileInputStream("./" + fileName);// 파일 스트림 생성
             //버퍼 선언
             byte[ ] readBuffer = new byte[fs.available()];
             while (fs.read( readBuffer ) != -1){
                //  System.out.println("Buffer is " + readBuffer);
             }
             fs.close(); //스트림 닫기
             return (new String(readBuffer)); //출력
         } catch (Exception e) {
             System.out.println("Error occured in reading file!");
            return "";
         }
    }

    public void parseOutput(String fileName, String output) {
        BufferedOutputStream bs = null;
        try {
            bs = new BufferedOutputStream(new FileOutputStream("./" + fileName + "_" + "output.txt"));
            bs.write(output.getBytes()); //Byte형으로만 넣을 수 있음
            bs.close();
        } catch (Exception e) {
            e.getStackTrace();
            // TODO: handle exception
        }
    }
}
