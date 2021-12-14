import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class aries {
    public static void main(String[] args) {
        output();
    }
    public static void output() {
        File myAries=new File("aries.txt");
        try (Scanner myReader = new Scanner(myAries)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        }catch (FileNotFoundException e) {
            System.out.println(myAries.getName()+" 未找到");
            e.printStackTrace();
        }
    }
}
