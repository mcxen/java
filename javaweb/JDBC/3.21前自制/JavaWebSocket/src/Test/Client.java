package Test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost",8088);
            Scanner scanner = new Scanner(System.in);
        ) {
            System.out.println(socket);
            System.out.println("Connected Success");
            //发送数据
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            //writer.write("发送消息 LBWNB\n");
            writer.write(scanner.nextLine()+"\n");
            writer.flush();
            System.out.println("数据已经发送");

            //接受服务端发来的信息
            InputStream is = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader);
            System.out.println(bufferedReader.readLine());

        } catch (IOException e) {
            System.out.println("failure");
            e.printStackTrace();
        }


    }
}
